package com.example.admincoaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.admincoaching.authentication.LoginEmailActivity;
import com.example.admincoaching.authentication.PendingActivity;
import com.example.admincoaching.authentication.UserActivity;
import com.example.admincoaching.faculty.UpdateFaculty;
import com.example.admincoaching.key.UploadAnsKey;
import com.example.admincoaching.notice.DeleteNoticeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CardView UploadNotice , addGalleryImage , addEbook , faculty ,deleteNotice,addPapers , logOut, notification ,ansKey;

    /*private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;*/

    private FirebaseAuth auth;

    private DatabaseReference usersRef;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UploadNotice = findViewById(R.id.addNotice);
        UploadNotice.setOnClickListener(this);

        addGalleryImage = findViewById(R.id.addGalleryImage);
        addGalleryImage.setOnClickListener(this);

        addEbook=findViewById(R.id.addEbook);
        addEbook.setOnClickListener(this);
        faculty=findViewById(R.id.faculty);
        faculty.setOnClickListener(this);
        deleteNotice=findViewById(R.id.deleteNotice);
        deleteNotice.setOnClickListener(this);
        addPapers=findViewById(R.id.addPapers);
        addPapers.setOnClickListener(this);
        logOut=findViewById(R.id.logOut);
        logOut.setOnClickListener(this);
        ansKey=findViewById(R.id.Answer);
        ansKey.setOnClickListener(this);
        /*notification=findViewById(R.id.notification);*/
        /*notification.setOnClickListener(this);*/

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
        currentUser = auth.getCurrentUser();

        /*sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor  = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("false")){
            openLogin();
        }*/

        /*yesNO();*/

    }

    private void yesNO() {


        if (currentUser == null) {
            // User is not signed in, redirect to login screen
            startActivity(new Intent(MainActivity.this, LoginEmailActivity.class));
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
                        // Status is null, display an error message and log the user out
                        Toast.makeText(MainActivity.this, "Your account status is not defined. Please contact an admin.", Toast.LENGTH_SHORT).show();
                        openLogin();
                    } else if (status.equals("yes")) {
                        // Allow the user to access the app
                        Toast.makeText(MainActivity.this, "Welcome Admin ", Toast.LENGTH_SHORT).show();

                    } else if (status.equals("no")) {
                        // Redirect the user to a "pending approval" screen
                        Toast.makeText(MainActivity.this, "you are Not a verified user", Toast.LENGTH_SHORT).show();
                        /*openLogin();*/
                        auth.signOut();
                        startActivity(new Intent(MainActivity.this, PendingActivity.class));
                        finish();

                    } else {
                        // Display an error message and log the user out
                        Toast.makeText(MainActivity.this, "Your account has been disabled. Please contact an super admin.", Toast.LENGTH_SHORT).show();
                        openLogin();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database errors
                }
            });
        }
    }

    private void openLogin() {
        auth.signOut();
        startActivity(new Intent(MainActivity.this,LoginEmailActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.addNotice:
                intent=new Intent(MainActivity.this, com.example.admincoaching.notice.UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent=new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;

            case R.id.addEbook:
                intent=new Intent(MainActivity.this,UploadPdfActivity.class);
                startActivity(intent);
                break;

            case R.id.faculty:
                intent=new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;

            case R.id.deleteNotice:
                intent=new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;

            case R.id.addPapers:
                intent=new Intent(MainActivity.this, UploadPapers.class);
                startActivity(intent);
                break;

            case R.id.Answer:
                intent=new Intent(MainActivity.this, UploadAnsKey.class);
                startActivity(intent);
                break;


            case R.id.logOut:

                /*openLogin();*/
                startActivity(new Intent(MainActivity.this, UserActivity.class));


                break;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() == null){
            openLogin();
        }else {
            yesNO();
        }
    }

}