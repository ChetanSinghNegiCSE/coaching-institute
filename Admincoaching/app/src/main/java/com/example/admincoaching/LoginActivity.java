package com.example.admincoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail ,userPassword;
    private TextView tvShow;
    private RelativeLayout loginBtn;
    private String email,password;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        tvShow = findViewById(R.id.txt_show);
        loginBtn = findViewById(R.id.login_btn);

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor  = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("yes")){
            openDash();
        }

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userPassword.getInputType() == 144) //hide mode
                {
                    userPassword.setInputType(129); //show mode
                    tvShow.setText("hide");
                }else {
                    userPassword.setInputType(144);
                    tvShow.setText("Show");
                }

                userPassword.setSelection(userPassword.getText().length());
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateData();
            }
        });
    }

    private void validateData() {
        email = userEmail.getText().toString();
        password = userPassword.getText().toString();

        if(email.isEmpty()){
            userEmail.setError("Fill Email");
            userEmail.requestFocus();
        }else  if(password.isEmpty()){
            userPassword.setError("Fill Password");
            userPassword.requestFocus();
        }else if(email.equals("admin@gmail.com") && password.equals("12345")){
            editor.putString("isLogin","yes");
            editor.commit();
            openDash();

        }else {
            Toast.makeText(this, "please check Email & password", Toast.LENGTH_LONG).show();
        }
    }

    private void openDash() {


        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}