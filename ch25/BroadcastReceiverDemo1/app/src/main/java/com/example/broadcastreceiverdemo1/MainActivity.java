package com.example.broadcastreceiverdemo1;

import java.util.Calendar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTime();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                setTime();
            }
        };
        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_TIME_TICK);
        this.registerReceiver(receiver, intentFilter);
    }

    public void onPause() {
        this.unregisterReceiver(receiver);
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        CharSequence newTime = DateFormat.format(
                "kk:mm", calendar);
        TextView textView = (TextView) findViewById(
                R.id.textView1);
        textView.setText(newTime);
    }
}