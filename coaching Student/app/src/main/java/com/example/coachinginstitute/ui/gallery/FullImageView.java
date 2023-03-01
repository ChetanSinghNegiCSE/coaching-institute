package com.example.coachinginstitute.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.coachinginstitute.R;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImageView extends AppCompatActivity {
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        photoView =(PhotoView) findViewById(R.id.photo_view);


        String image = getIntent().getStringExtra("image");

        Glide.with(this).load(image).into(photoView);
    }
}