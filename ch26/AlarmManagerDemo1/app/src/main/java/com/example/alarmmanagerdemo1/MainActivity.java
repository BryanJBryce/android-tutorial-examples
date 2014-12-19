package com.example.alarmmanagerdemo1;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setAlarm(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        Date fiveMinutesLater = calendar.getTime();
        Toast.makeText(this, "The alarm will set off at " +
                fiveMinutesLater, Toast.LENGTH_LONG).show();
        AlarmManager alarmMgr = (AlarmManager) getSystemService(
                Context.ALARM_SERVICE);
        Intent intent = new Intent(this, WakeUpActivity.class);
        PendingIntent sender = PendingIntent.getActivity(
                this, 0, intent, 0);
        alarmMgr.set(AlarmManager.RTC_WAKEUP,
                fiveMinutesLater.getTime(), sender);
    }
}
