package com.example.alarmmanagerdemo1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class WakeUpActivity extends Activity {
    private final int NOTIFICATION_ID = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        Log.d("wakeup", "called. oncreate");
        window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_wake_up);
        addNotification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wake_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dismiss(View view) {
        NotificationManager notificationMgr = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationMgr.cancel(NOTIFICATION_ID);
        this.finish();
    }

    private void addNotification() {
        NotificationManager notificationMgr = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Notification notification  = new Notification.Builder(this)
                .setContentTitle("Wake up")
                .setSmallIcon(android.R.drawable.star_on)
                .setAutoCancel(false)
                .build();
        notification.defaults|= Notification.DEFAULT_SOUND;
        notification.defaults|= Notification.DEFAULT_LIGHTS;
        notification.defaults|= Notification.DEFAULT_VIBRATE;
        notification.flags |= Notification.FLAG_INSISTENT;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationMgr.notify(NOTIFICATION_ID, notification);
    }
}