package com.example.admincoaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.admincoaching.faculty.UpdateFaculty;
import com.example.admincoaching.notice.DeleteNoticeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CardView UploadNotice , addGalleryImage , addEbook , faculty ,deleteNotice,addPapers , logOut, notification;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


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
        notification=findViewById(R.id.notification);
        notification.setOnClickListener(this);


      /*  sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor  = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("false")){
            openLogin();
        }*/
    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
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

            /*case R.id.notification:
                intent=new Intent(MainActivity.this, AdminNotificationActivity.class);
                startActivity(intent);
                break;*/

          /*  case R.id.logOut:
                editor.putString("isLogin","false");
                editor.commit();
                openLogin();
                break;*/
        }

    }
}