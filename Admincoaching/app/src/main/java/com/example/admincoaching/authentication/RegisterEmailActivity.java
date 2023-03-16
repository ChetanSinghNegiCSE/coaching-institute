package com.example.admincoaching.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admincoaching.MainActivity;
import com.example.admincoaching.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterEmailActivity extends AppCompatActivity {
    private EditText regName ,regEmail ,regPassword;
    private Button register;
    private String name,email,password;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference dbRef;
    private TextView openLog;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        regName =findViewById(R.id.registerName);
        regEmail = findViewById(R.id.registerEmail);
        regPassword = findViewById(R.id.registerPass);
        register = findViewById(R.id.register);
        openLog = findViewById(R.id.openLog);

        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

    }

    private void openLogin() {
        startActivity(new Intent(RegisterEmailActivity.this,LoginEmailActivity.class));
        finish();
    }

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
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            uploadData();

                        }else {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(RegisterEmailActivity.this, "Error : "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(RegisterEmailActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void uploadData() {
        /*dbRef = reference.child("users");
        String key = dbRef.push().getKey();*/

       /* HashMap<String,String> user = new HashMap<>();
        user.put("key",key);
        user.put("name", name);
        user.put("email",email);
        user.put("status","no");*/





        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String userId = user.getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        dbRef.child("email").setValue(email);
        dbRef.child("status").setValue("no");
        String key = dbRef.push().getKey();


        assert key != null;
        dbRef.child(key).setValue(userId)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            /*Toast.makeText(RegisterEmailActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            openMain();*/
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterEmailActivity.this, "Your account is pending approval by an admin.", Toast.LENGTH_SHORT).show();
//                            openLogin();
                            startActivity(new Intent(RegisterEmailActivity.this,PendingActivity.class));
                            finish();

                        }else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterEmailActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterEmailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}