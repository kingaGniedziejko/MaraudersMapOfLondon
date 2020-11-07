package com.example.maraudersmapoflondon;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    ArrayList<Attraction> attractions = new ArrayList<>();
    Attraction harryPotterShop = new Attraction(
            "The Harry Potter Shop at Platform 9 3/4",
            new LatLng(51.53218, -0.12392),
            "",
            new int[] {R.drawable.harry_potter_shop_1, R.drawable.harry_potter_shop_2, R.drawable.harry_potter_shop_3, R.drawable.harry_potter_shop_4, R.drawable.harry_potter_shop_5, R.drawable.harry_potter_shop_6});

    Attraction leadenhallMarket = new Attraction(
            "18 Leadenhall Market",
            new LatLng(51.51281, -0.08387),
            "",
            new int[] {R.drawable.leadenhall_market_1, R.drawable.leadenhall_market_2, R.drawable.leadenhall_market_3, R.drawable.leadenhall_market_4, R.drawable.leadenhall_market_5, R.drawable.leadenhall_market_6});

    Attraction highCommissionOfAustralia = new Attraction(
            "High Commission of Australia, London",
            new LatLng(51.51282, -0.11528),
            "Gringotts Wizarding Bank",
            new int[] {R.drawable.high_commission_of_australia_1, R.drawable.high_commission_of_australia_2, R.drawable.high_commission_of_australia_3});

    Attraction millenniumBridge = new Attraction(
            "Millennium Bridge",
            new LatLng(51.50953, -0.09852),
            "",
            new int[] {R.drawable.millennium_bridge_1, R.drawable.millennium_bridge_2, R.drawable.millennium_bridge_3});

    Attraction piccadillyCircus = new Attraction(
            "10 Piccadilly Circus",
            new LatLng(51.5101, -0.13466),
            "",
            new int[] {R.drawable.piccadilly_circus_1, R.drawable.piccadilly_circus_2, R.drawable.piccadilly_circus_3});

    Attraction stPancrasInternational = new Attraction(
            "St Pancras International",
            new LatLng(51.53169, -0.1267),
            "",
            new int[] {R.drawable.st_pancras_international_1, R.drawable.st_pancras_international_2, R.drawable.st_pancras_international_3});

    Attraction reptileHouse = new Attraction(
            "Reptile House",
            new LatLng(51.5352, -0.15565),
            "",
            new int[] {R.drawable.reptile_house_1, R.drawable.reptile_house_2, R.drawable.reptile_house_3});

    Attraction scotlandPlace = new Attraction(
            "Scotland Place",
            new LatLng(51.50612, -0.12561),
            "Entrance to the Ministry of Magic",
            new int[] {R.drawable.scotland_place_1, R.drawable.scotland_place_2, R.drawable.scotland_place_3});

    Attraction claremontSquare = new Attraction(
            "Claremont Square",
            new LatLng(51.53122, -0.11034),
            "12 Grimmauld Place",
            new int[] {R.drawable.claremont_square_1, R.drawable.claremont_square_2, R.drawable.claremont_square_3});

    Attraction kingsCrossStationPlatform = new Attraction(
            "King's Cross Station Platform 9 3/4",
            new LatLng(51.53273, -0.12398),
            "",
            new int[] {R.drawable.kings_cross_station_platform_1, R.drawable.kings_cross_station_platform_2, R.drawable.kings_cross_station_platform_3});


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);


        supportMapFragment.getMapAsync(this);

        attractions.add(harryPotterShop);
        attractions.add(leadenhallMarket);
        attractions.add(highCommissionOfAustralia);
        attractions.add(millenniumBridge);
        attractions.add(piccadillyCircus);
        attractions.add(stPancrasInternational);
        attractions.add(reptileHouse);
        attractions.add(scotlandPlace);
        attractions.add(claremontSquare);
        attractions.add(kingsCrossStationPlatform);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.retro_map_style));
        } catch (Resources.NotFoundException e) {}

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MainActivity.this));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(51.5074, -0.1278)));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        for (Attraction attraction : attractions){
            googleMap.addMarker(new MarkerOptions()
                    .position(attraction.getPosition())
                    .title(attraction.getName())
                    .icon(bitmapDescriptor(getApplicationContext(), R.drawable.footprints_marker)));
        }

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                try {
                    if (marker.isInfoWindowShown()){
                        marker.hideInfoWindow();
                    } else {
                        marker.showInfoWindow();
                    }
                } catch (NullPointerException e){
                    System.out.println(e.getMessage());
                }

                return false;
            }
        });


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String markerTitle = marker.getTitle();
                Attraction currentAttraction = null;

                for (Attraction attraction : attractions){
                    if (attraction.getName().equals(markerTitle)) currentAttraction = attraction;
                }

                if (currentAttraction != null) {
                    Intent intent = new Intent(MainActivity.this, AttractionInfoActivity.class);
                    intent.putExtra("title", currentAttraction.getName());
                    intent.putExtra("description", currentAttraction.getDescription());
                    intent.putExtra("gallery", currentAttraction.getGallery());

                    startActivity(intent);

//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    AttractionInfoFragment fragment = new AttractionInfoFragment(currentAttraction);
//                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
                }
            }
        });


//        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public boolean onMarkerClick(Marker marker) {
////                String markerTitle = marker.getTitle();
////                Attraction currentAttraction = null;
////
////                for (Attraction attraction : attractions){
////                    if (attraction.getName().equals(markerTitle)) currentAttraction = attraction;
////                }
////
////                if (currentAttraction != null) {
////                    Intent intent = new Intent(MainActivity.this, AttractionInfoActivity.class);
////                    intent.putExtra("title", currentAttraction.getName());
////                    intent.putExtra("description", currentAttraction.getDescription());
////
////                    startActivity(intent);
////
//////                    FragmentManager fragmentManager = getSupportFragmentManager();
//////                    AttractionInfoFragment fragment = new AttractionInfoFragment(currentAttraction);
//////                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
////                }
////
//                return false;
//            }
//        });
    }

    private void getCurrentLocation() {
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            googleMap.setMyLocationEnabled(true);

//                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            MarkerOptions options = new MarkerOptions()
//                                    .position(latLng)
//                                    .icon(BitmapDescriptorFactory
//                                            .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }

    private BitmapDescriptor bitmapDescriptor(Context context, int resourceId) {
        Drawable drawable = ContextCompat.getDrawable(context, resourceId);
        drawable.setBounds(0, 0, 80, 80);
        Bitmap bitmap = Bitmap.createBitmap(80, 80, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}