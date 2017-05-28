package com.animator_abhi.copyclipper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("Msg", "Message received ["+remoteMessage+"]");

        // Create Notification
      //  Intent intent = new Intent(this, LoginActivity.class);
     /*   Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Your text");
        startActivity(Intent.createChooser(intent, "Share using"));*/
      /* intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410,
                intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_google)
                .setContentTitle("Message")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);*/

   /*     NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1410, notificationBuilder.build());*/Notification.Builder mBuilder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_google)
                        .setContentTitle("clipboad")
                        .setContentText(remoteMessage.getNotification().getBody());

     /*   Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "hello");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       // startActivity(Intent.createChooser(intent, "Share using"));

      /*  Intent resultIntent = new Intent(this, AndroidBuildingMusicPlayerActivity.class);
        resultIntent.setAction(Intent.ACTION_MAIN);
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
*/
       /* PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
            intent, 0);*/


        mBuilder.setContentIntent(getShareIntent(this,remoteMessage.getData().get("customData").toString()));
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }private static PendingIntent getShareIntent( Context context,  String uri)
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, uri.toString());
        return PendingIntent
                .getActivity(context, (int) System.currentTimeMillis(),
                        shareIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}