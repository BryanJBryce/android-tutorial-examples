package com.example.activitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifecycle", "onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy");
    }
}