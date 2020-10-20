package com.example.maraudersmapoflondon;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
//import 'dart:ui' as ui;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    ArrayList<Attraction> attractions = new ArrayList<>();
    Attraction harryPotterShop = new Attraction("The Harry Potter Shop at Platform 9 3/4", new LatLng(51.53218, -0.12392));
    Attraction leadenhallMarket = new Attraction("18 Leadenhall Market", new LatLng(51.51281, -0.08387));
    Attraction highCommissionOfAustralia = new Attraction("High Commission of Australia, London", new LatLng(51.51282, -0.11528), "Gringotts Wizarding Bank");
    Attraction millenniumBridge = new Attraction("Millennium Bridge", new LatLng(51.50953, -0.09852));
    Attraction piccadillyCircus = new Attraction("10 Piccadilly Circus", new LatLng(51.5101, -0.13466));
    Attraction stPancrasInternational = new Attraction("St Pancras International", new LatLng(51.53169, -0.1267));
    Attraction reptileHouse = new Attraction("Reptile House", new LatLng(51.5352, -0.15565));
    Attraction scotlandPlace = new Attraction("Scotland Place", new LatLng(51.50612, -0.12561), "Entrance to the Ministry of Magic");
    Attraction claremontSquare = new Attraction("Claremont Square", new LatLng(51.53122, -0.11034), "12 Grimmauld Place");
    Attraction kingsCrossStationPlatform = new Attraction("King's Cross Station Platform 9 3/4", new LatLng(51.53273, -0.12398));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

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

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(51.5074, -0.1278)));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

//        BitmapDescriptor markerBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.footprints_marker);

        for (Attraction attraction : attractions){
//            googleMap.addMarker(new MarkerOptions().position(attraction.getPosition()).title(attraction.getName()).icon(markerBitmapDescriptor));
            googleMap.addMarker(new MarkerOptions().position(attraction.getPosition()).title(attraction.getName()).icon(bitmapDescriptor(getApplicationContext(), R.drawable.footprints_marker)));
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