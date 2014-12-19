package com.example.soundmeter;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Handler handler = new Handler();
    SoundMeter soundMeter = new SoundMeter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it 
        // is present. 
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        soundMeter.start();
        handler.postDelayed(pollTask, 150);
    }

    @Override
    public void onPause() {
        soundMeter.stop();
        super.onPause();
    }

    private Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            double amplitude = soundMeter.getAmplitude();
            TextView textView = (TextView) findViewById(R.id.level);
            textView.setText("amp:" + amplitude);
            Button button = (Button) findViewById(R.id.button1);
            button.setWidth((int) amplitude * 10);
            handler.postDelayed(pollTask, 150);
        }
    };
}