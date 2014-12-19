package com.example.multicolorclock;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AnalogClock;

public class MainActivity extends Activity {

    int counter = 0;
    int[] colors = { Color.BLACK, Color.BLUE, Color.CYAN,
            Color.DKGRAY, Color.GRAY, Color.GREEN, Color.LTGRAY,
            Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW };

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

    public void changeColor(View view) {
        if (counter == colors.length) {
            counter = 0;
        }
        view.setBackgroundColor(colors[counter++]);
    }
}