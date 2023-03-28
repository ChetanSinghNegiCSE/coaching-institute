package com.chetan.coachinginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.chetan.coachinginstitute.about.AboutActivity;
import com.chetan.coachinginstitute.authentication.LoginEmailActivity;
import com.chetan.coachinginstitute.authentication.PendingActivity;
import com.chetan.coachinginstitute.authentication.UserActivity;
import com.chetan.coachinginstitute.faculty.FacultyActivity;
import com.chetan.coachinginstitute.notification.NotificationActivity;
import com.chetan.coachinginstitute.papers.PaperActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;
    private final String CHECKEDITEM = "checked_item";
    private FirebaseAuth auth;

    private DatabaseReference devicesRef;

    private String userID;

    private DatabaseReference usersRef;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,R.id.frame_layout);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Students");




        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        auth = FirebaseAuth.getInstance();

        sharedPreferences =this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navigation_developer:
//                Toast.makeText(this, "dev", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.navigation_user:
//                Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, UserActivity.class));

                break;
            case R.id.navigation_ans:
                startActivity(new Intent(this, PaperActivity.class));
                break;


            /*case R.id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;*/


            case R.id.navigation_color:
//                Toast.makeText(this, "Theme", Toast.LENGTH_SHORT).show();
                showDialog();
                break;

            case R.id.navigation_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;

            case R.id.navigation_faculty:
                startActivity(new Intent(this, FacultyActivity.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;

    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theam);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theam, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selected = themes[which];
                checkedItem =which;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(selected == null){
                    selected = themes[which];
                    checkedItem=which;
                }
                switch (selected){
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM,0);
    }

    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() == null){
            openLogin();
        }else {
            checkUser();
        }
        }

    private void checkUser() {
        currentUser = auth.getCurrentUser();
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
                    } else if (status.equals("1")) {
                        // Allow the user to access the app
                        Toast.makeText(MainActivity.this, "Welcome  ", Toast.LENGTH_SHORT).show();

                    } else if (status.equals("0")) {
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

        startActivity(new Intent(MainActivity.this, LoginEmailActivity.class));
        finish();

    }

}