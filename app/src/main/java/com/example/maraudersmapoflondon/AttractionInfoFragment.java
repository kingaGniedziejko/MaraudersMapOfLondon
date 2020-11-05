package com.example.maraudersmapoflondon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AttractionInfoFragment extends Fragment {

    private Attraction attraction;

    private TextView title;
    private TextView description;

    public AttractionInfoFragment(Attraction attraction) {
        this.attraction = attraction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attraction_info, container, false);

    }
}