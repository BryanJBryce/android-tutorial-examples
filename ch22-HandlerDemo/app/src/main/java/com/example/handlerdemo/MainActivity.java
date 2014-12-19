package com.example.handlerdemo;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    int counter = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserAttention();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it 
        // is present. 
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonClicked(View view) {
        counter = 0;
        getUserAttention();
    }

    private void getUserAttention() {
        handler.post(task);
    }

    Runnable task = new Runnable() {
        @Override
        public void run() {
            ImageView imageView = (ImageView)
                    findViewById(R.id.imageView1);
            if (counter % 2 == 0) {
                imageView.setVisibility(View.INVISIBLE);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }
            counter++;
            if (counter < 8) {
                handler.postDelayed(this, 400);
            }
        }
    };
}