package com.example.cancelnotificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {
    private static final String CANCEL_NOTIFICATION_ACTION
            = "cancel_notification";
    int notificationId = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(
                                NOTIFICATION_SERVICE);
                notificationManager.cancel(notificationId);
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(CANCEL_NOTIFICATION_ACTION);
        this.registerReceiver(receiver, filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void setNotification(View view) {
        Intent cancelIntent = new Intent("cancel_notification");
        PendingIntent cancelPendingIntent =
                PendingIntent.getBroadcast(this, 100,
                        cancelIntent, 0);

        Notification notification  = new Notification.Builder(this)
                .setContentTitle("Stop Press")
                .setContentText(
                        "Everyone gets extra vacation week!")
                .setSmallIcon(android.R.drawable.star_on)
                .setAutoCancel(true)
                .addAction(android.R.drawable.btn_dialog,
                        "Dismiss", cancelPendingIntent)
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