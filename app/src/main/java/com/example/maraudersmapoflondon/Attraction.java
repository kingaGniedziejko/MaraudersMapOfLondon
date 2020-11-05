package com.example.maraudersmapoflondon;

//import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class Attraction implements Parcelable {
    private String name;
    private LatLng position;
    private String description = "";
    private ArrayList gallery = new ArrayList<>();
    private String movieLink = "";


    public Attraction(){
        this.name = "";
        this.position = null;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
//        parcel.writeStringArray((String[]) gallery.toArray());
        parcel.writeString(movieLink);
    }

    public static final Parcelable.Creator<Attraction> CREATOR = new Parcelable.Creator<Attraction>() {
        public Attraction createFromParcel(Parcel in) {
            return new Attraction(in);
        }

        public Attraction[] newArray(int size) {
            return new Attraction[size];
        }
    };

    private Attraction(Parcel in) {
        name = in.readString();
        description = in.readString();
//        gallery = in.readArray();
        movieLink = in.readString();
    }
}
