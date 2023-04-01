package com.chetan.coachinginstitute.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chetan.coachinginstitute.MainActivity;
import com.chetan.coachinginstitute.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PendingActivity extends AppCompatActivity {
    private DatabaseReference usersRef;
    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    private ProgressBar progressBar;

    private ImageView imageView;

    private Button button;

    private DatabaseReference devicesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.gifView);
        button = findViewById(R.id.materialButton);
        progressBar=findViewById(R.id.progressBar);


        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Students");
        currentUser = auth.getCurrentUser();


        Glide.with(this)
                .asGif()
                .load(R.raw.coding)
                .into(imageView);


        if (currentUser == null) {
            // User is not signed in, redirect to login screen
            startActivity(new Intent(PendingActivity.this, LoginEmailActivity.class));
            finish();

        } else {

// Get the current user's ID
            String userId = auth.getCurrentUser().getUid();

// Get a reference to the current user's status in the Realtime Database
            DatabaseReference statusRef = usersRef.child(userId).child("status");

// Check if the user's status is "Approved"
            statusRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String status = dataSnapshot.getValue(String.class);
                    if (status == null) {
                        progressBar.setVisibility(View.GONE);
                        // Status is null, display an error message and log the user out
                        Toast.makeText(PendingActivity.this, "Your account status is not defined. Please contact an admin.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PendingActivity.this, LoginEmailActivity.class));
                        finish();

                    } else if (status.equals("1")) {

                        // Allow the user to access the app
                        Toast.makeText(PendingActivity.this, "Welcome  ", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(PendingActivity.this, MainActivity.class));
                        finish();

                    } else if (status.equals("0")) {

                        // Redirect the user to a "pending approval" screen
                        Toast.makeText(PendingActivity.this, "you are Not a verified user ", Toast.LENGTH_SHORT).show();


                    } else {
                        // Display an error message and log the user out
                        Toast.makeText(PendingActivity.this, "Your account has been disabled. Please contact an super admin.", Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database errors
                    Toast.makeText(PendingActivity.this, "Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = auth.getCurrentUser().getUid();
                devicesRef = FirebaseDatabase.getInstance().getReference().child("CurrentUser").child(userID).child("devices");

                String deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                devicesRef.child(deviceID).removeValue();
                auth.signOut();
                startActivity(new Intent(PendingActivity.this, LoginEmailActivity.class));
                finish();
            }
        });
    }



}