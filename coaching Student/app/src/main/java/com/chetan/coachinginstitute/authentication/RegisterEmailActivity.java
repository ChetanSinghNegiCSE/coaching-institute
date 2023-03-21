package com.chetan.coachinginstitute.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chetan.coachinginstitute.MainActivity;
import com.chetan.coachinginstitute.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterEmailActivity extends AppCompatActivity {
    private EditText regName ,regEmail ,regPassword;
    private Button register;
    private String name,email,password;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference dbRef;
//    private TextView openLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);
        regName =findViewById(R.id.registerName);
        regEmail = findViewById(R.id.registerEmail);
        regPassword = findViewById(R.id.registerPass);
        register = findViewById(R.id.register);
//        openLog = findViewById(R.id.openLog);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

      /*  openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });*/

    }

    /*private void openLogin() {
        startActivity(new Intent(RegisterEmailActivity.this,LoginEmailActivity.class));
        finish();
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() != null){
            openMain();
        }
    }

    private void openMain() {
        startActivity(new Intent( this, MainActivity.class));
        finish();
    }

    private void validateData() {
        name= regName.getText().toString();
        email= regEmail.getText().toString();
        password= regPassword.getText().toString();

        if(name.isEmpty()){
            regName.setError("Fill name");
            regName.requestFocus();
        }else if(email.isEmpty()){
            regEmail.setError("Fill Email");
            regEmail.requestFocus();
        } else if(password.isEmpty()){
            regPassword.setError("Fill Password");
            regPassword.requestFocus();
        } else {
            createUser();
        }
    }

    private void createUser() {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            uploadData();

                        }else {
                            Toast.makeText(RegisterEmailActivity.this, "Error : "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterEmailActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void uploadData() {
        dbRef = reference.child("users");
        String key = dbRef.push().getKey();

        HashMap<String,String> user = new HashMap<>();
        user.put("key",key);
        user.put("name", name);
        user.put("email",email);
        /* user.put("status","no");*/

        dbRef.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterEmailActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            openMain();
                        }else {
                            Toast.makeText(RegisterEmailActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterEmailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}