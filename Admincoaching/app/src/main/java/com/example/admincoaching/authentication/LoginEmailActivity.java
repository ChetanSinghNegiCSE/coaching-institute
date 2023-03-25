package com.example.admincoaching.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admincoaching.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginEmailActivity extends AppCompatActivity {

    private TextView openReg ,openForget;
    private EditText loginEmail ,loginPass;
    private Button loginBtn;
    private String email, password;
    private FirebaseAuth auth;

    private DatabaseReference reference;
    private static final int MAX_DEVICES = 1;
    private String mDeviceId;

    private ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        openReg =findViewById(R.id.openReg);
        loginEmail =findViewById(R.id.loginEmail);
        loginPass =findViewById(R.id.loginPass);
        loginBtn =findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();
        openForget =findViewById(R.id.openForget);
        progressBar = findViewById(R.id.progressBar);


        openReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgetPassword();
            }
        });




    }

    private void openForgetPassword() {
        startActivity(new Intent(LoginEmailActivity.this,ForgetPassActivity.class));

    }

    private void validateData() {
        email = loginEmail.getText().toString();
        password = loginPass.getText().toString();

        if(email.isEmpty()){
            loginEmail.setError("Fill Email");
            loginEmail.requestFocus();
        } else if(password.isEmpty()){
            loginPass.setError("Fill Password");
            loginPass.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @SuppressLint("HardwareIds")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                /*FirebaseUser user = auth.getCurrentUser();
                checkIfUserIsLoggedIn(user);*/
                    /* user.put("status","no");*/
                    reference = FirebaseDatabase.getInstance().getReference().child("CurrentAdminUser").child(auth.getCurrentUser().getUid()).child("devices");
                    mDeviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                    reference.push().setValue(mDeviceId);
                    /*checkDeviceLimit();*/


                    openMain();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginEmailActivity.this, "Error"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginEmailActivity.this, "Error : "+ e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkDeviceLimit() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int numDevices = (int) snapshot.getChildrenCount();


                if (numDevices > MAX_DEVICES) {
                    // Limit exceeded, sign out the user and display an error message
                    startActivity(new Intent(LoginEmailActivity.this,LoginEmailActivity.class));
                    finish();
                    auth.signOut();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginEmailActivity.this, "Maximum number of devices reached Login again.", Toast.LENGTH_LONG).show();
                }else  {
                    openMain();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                // Handle database read failure
                Toast.makeText(LoginEmailActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        auth.addAuthStateListener(firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // User signed out, remove device identifier from list
                reference.orderByValue().equalTo(mDeviceId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot deviceSnapshot : snapshot.getChildren()) {
                            deviceSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LoginEmailActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }





    private void openMain() {
        startActivity(new Intent(LoginEmailActivity.this,PendingActivity.class));
        finish();


    }



    private void openRegister() {
        startActivity(new Intent(LoginEmailActivity.this,RegisterEmailActivity.class));
        finish();
    }


}