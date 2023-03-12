package com.example.coachinginstitute.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coachinginstitute.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    private TextView name , email;
    private Button logout ,update;
    private ImageView userImg;


    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("User"); //SetTitle in Action bar

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
}