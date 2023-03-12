package com.example.coachinginstitute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        // Load data or perform other operations here

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Main = new Intent(splash.this, MainActivity.class);
                startActivity(Main);
                finish();
            }
        }, 3000); // 3 seconds delay for demo purposes
    }
}