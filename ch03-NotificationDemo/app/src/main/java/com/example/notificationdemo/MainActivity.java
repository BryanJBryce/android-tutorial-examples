package com.example.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
    int notificationId = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setNotification(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification  = new Notification.Builder(this)
                .setContentTitle("New notification")
                .setContentText("You've got a notification!")
                .setSmallIcon(android.R.drawable.star_on)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(android.R.drawable.ic_menu_gallery,
                        "Open", pendingIntent)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    public void clearNotification(View view) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }
}