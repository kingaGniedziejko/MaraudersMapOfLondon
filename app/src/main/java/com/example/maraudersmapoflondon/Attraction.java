package com.example.maraudersmapoflondon;

import android.media.Image;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class Attraction {
    private String name;
    private LatLng position;
    private String description;
    private String movieLink;
    private ArrayList<Image> images;


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

    public void setDescription(String description) {
        this.description = description;
    }
}
