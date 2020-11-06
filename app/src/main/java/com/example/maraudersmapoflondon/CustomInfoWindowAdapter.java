package com.example.maraudersmapoflondon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View window;

    public CustomInfoWindowAdapter(Context context) {
        this.window = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void renderWindow(Marker marker, View view){
        String title = marker.getTitle();
        TextView titleTextView = view.findViewById(R.id.title);

        if (!title.equals("")){
            titleTextView.setText(title);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindow(marker, window);
        return window;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindow(marker, window);
        return window;
    }
}