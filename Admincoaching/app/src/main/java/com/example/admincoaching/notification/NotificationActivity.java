package com.example.admincoaching.notification;

import static com.example.admincoaching.notification.Constants.TOPIC;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admincoaching.R;
import com.example.admincoaching.notification.api.ApiUtilities;
import com.example.admincoaching.notification.model.NotificationData;
import com.example.admincoaching.notification.model.PushNotification;

import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    private EditText title ,message;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        button =findViewById(R.id.button3);
        title = findViewById(R.id.titleNoti);
        message =findViewById(R.id.messageNoti);

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTxt = title.getText().toString();
                String messageTxt = message.getText().toString();

                if(!titleTxt.isEmpty() && !messageTxt.isEmpty()){
                    PushNotification notification = new PushNotification(new NotificationData(titleTxt,messageTxt), TOPIC);
                    sendNotification(notification);

                }

            }
        });

    }

    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if(response.isSuccessful()){
                    Toast.makeText(NotificationActivity.this, "success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(NotificationActivity.this, "error ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {

                Toast.makeText(NotificationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }


}