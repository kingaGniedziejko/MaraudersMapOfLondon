package com.example.maraudersmapoflondon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        getSupportActionBar().hide();

        imageView = findViewById(R.id.image_view);
        int image = getIntent().getIntExtra("image", 0);
        imageView.setImageResource(image);
    }
}