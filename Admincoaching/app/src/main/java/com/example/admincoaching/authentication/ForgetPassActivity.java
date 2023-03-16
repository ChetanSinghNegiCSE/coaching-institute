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

import com.example.admincoaching.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassActivity extends AppCompatActivity {
    private EditText ForgotEmail;
    private Button forgetBtn;
    private String email;
    private FirebaseAuth auth;
    private TextView openLogin;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        ForgotEmail = findViewById(R.id.ForgotEmail);
        forgetBtn = findViewById(R.id.forgetBtn);
        openLogin = findViewById(R.id.backLogin);

        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassActivity.this,LoginEmailActivity.class));
                finish();
            }
        });

    }

    private void validateData() {
        email = ForgotEmail.getText().toString();
        if(email.isEmpty()){
            ForgotEmail.setError("Required");
        }else {
            progressBar.setVisibility(View.VISIBLE);
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ForgetPassActivity.this, "Reset Link send to your Email ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetPassActivity.this,LoginEmailActivity.class));
                            finish();
                        }else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ForgetPassActivity.this, "Error : "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}