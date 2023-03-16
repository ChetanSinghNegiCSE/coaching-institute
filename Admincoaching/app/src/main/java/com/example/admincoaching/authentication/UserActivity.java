package com.example.admincoaching.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admincoaching.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

         private TextView name , email;
        private Button logout ;
        private ImageView userImg;

        private DatabaseReference devicesRef;


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
            getSupportActionBar().setTitle("Admin"); //SetTitle in Action bar

            name = findViewById(R.id.userName);
            email = findViewById(R.id.userEmail);
            logout = findViewById(R.id.userlogout);
            userImg = findViewById(R.id.userImg);

            String userID = auth.getCurrentUser().getUid();
            devicesRef = FirebaseDatabase.getInstance().getReference().child("CurrentAdminUser").child(userID).child("devices");

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


        }

        @Override
        protected void onStart() {
            super.onStart();
            if(user == null){
                startActivity(new Intent(this, LoginEmailActivity.class));
                finish();
            }
        }


}