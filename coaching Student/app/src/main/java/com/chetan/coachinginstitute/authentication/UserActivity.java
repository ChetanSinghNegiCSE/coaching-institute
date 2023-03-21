package com.chetan.coachinginstitute.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetan.coachinginstitute.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    private TextView name , email;
    private Button logout ,update;
    private ImageView userImg;

    private DatabaseReference devicesRef;


    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("User"); //SetTitle in Action bar

        String userID = auth.getCurrentUser().getUid();
        devicesRef = FirebaseDatabase.getInstance().getReference().child("CurrentUser").child(userID).child("devices");


        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        logout = findViewById(R.id.userlogout);
        userImg = findViewById(R.id.userImg);
        /*update = findViewById(R.id.updateUser);*/

        if(user != null){
            name.setText(user.getDisplayName());
            email.setText(user.getEmail());
            if(user.getPhotoUrl() != null)
            {
                Glide.with(this).load(user.getPhotoUrl()).into(userImg);
            }
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                devicesRef.child(deviceID).removeValue();
                auth.signOut();
                startActivity(new Intent(UserActivity.this, LoginEmailActivity.class));
                finish();
            }
        });

        /*update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, UpdateProfile.class));

            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user == null){
            startActivity(new Intent(this, LoginEmailActivity.class));
            finish();
        }
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        String deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        devicesRef.child(deviceID).removeValue();
        auth.signOut();
    }*/
}