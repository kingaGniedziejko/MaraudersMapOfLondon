package com.example.maraudersmapoflondon;

//import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class Attraction{
    private String name;
    private LatLng position;
    private String description = "";
    private ArrayList gallery = new ArrayList<>();
    private String movieLink = "";

    public Attraction(String name, LatLng position){
        this.name = name;
        this.position = position;
    }

    public Attraction(String name, LatLng position, String description){
        this(name, position);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
