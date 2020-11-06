package com.example.maraudersmapoflondon;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class AttractionInfoActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView galleryTitleTextView;
    private ExpandableHeightGridView galleryGridView;
//    private GridView galleryGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_info);

        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        galleryTitleTextView = findViewById(R.id.gallery_title);
        galleryGridView = findViewById(R.id.gallery_grid_view);

        galleryGridView.setExpanded(true);
//        galleryGridView = findViewById(R.id.gallery_grid_view);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        int[] gallery = getIntent().getIntArrayExtra("gallery");

        titleTextView.setText(title);

        if (!description.equals("")){
            descriptionTextView.setText(description);
        } else {
            descriptionTextView.setVisibility(View.GONE);
        }

        if (gallery.length != 0) {

            ImageAdapter imageAdapter = new ImageAdapter(this, gallery);
            galleryGridView.setAdapter(imageAdapter);

            galleryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), FullScreenActivity.class);
                    intent.putExtra("image", (Integer)imageAdapter.getItem(i));
                    startActivity(intent);
                }
            });
        } else {
            galleryTitleTextView.setVisibility(View.GONE);
        }


    }
}