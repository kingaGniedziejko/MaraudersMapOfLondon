package com.example.maraudersmapoflondon;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class AttractionInfoActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_info);

        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        titleTextView.setText(title);
        descriptionTextView.setText(description);

    }
}