package com.chetan.coachinginstitute.notification;

import static com.chetan.coachinginstitute.notification.Constants.TOPIC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chetan.coachinginstitute.R;
import com.chetan.coachinginstitute.notification.api.ApiUtilities;
import com.chetan.coachinginstitute.notification.model.NotificationData;
import com.chetan.coachinginstitute.notification.model.PushNotification;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("Developer"); //SetTitle in Action bar

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