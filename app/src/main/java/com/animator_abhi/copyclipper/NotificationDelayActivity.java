package com.animator_abhi.copyclipper;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotificationDelayActivity extends AppCompatActivity {
  EditText inputField;
    Button launchNotif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_delay);
        inputField= (EditText) findViewById(R.id.editText);
        launchNotif= (Button) findViewById(R.id.notify);
        launchNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationDelayActivity.this);
                        mBuilder.setSmallIcon(R.drawable.ic_google);
                        mBuilder.setContentTitle("Cloudadic notification");
                        mBuilder.setContentText(inputField.getText().toString());
                        Notification notifcation = mBuilder.build();

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        nm.notify(2, notifcation);

                    }
                }, 10000);
            }
        });


    }


}
