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
    private int[] gallery = {};
    private String movieLink = "";

    public Attraction(String name, LatLng position){
        this.name = name;
        this.position = position;
    }

    public Attraction(String name, LatLng position, String description){
        this(name, position);
        this.description = description;
    }

    public Attraction(String name, LatLng position, String description, int[] gallery){
        this(name, position, description);
        this.gallery = gallery;
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

    public int[] getGallery() {
        return gallery;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
