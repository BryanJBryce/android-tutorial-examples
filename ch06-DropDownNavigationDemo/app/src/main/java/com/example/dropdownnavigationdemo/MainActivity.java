package com.example.dropdownnavigationdemo;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpinnerAdapter spinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.colors,
                        android.R.layout.simple_spinner_dropdown_item);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(
                ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(spinnerAdapter,
                onNavigationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    OnNavigationListener onNavigationListener = new
            OnNavigationListener() {
                @Override
                public boolean onNavigationItemSelected(
                        int position, long itemId) {
                    String[] colors = getResources().
                            getStringArray(R.array.colors);
                    String selectedColor = colors[position];

                    getWindow().getDecorView().setBackgroundColor(
                            Color.parseColor(selectedColor));
                    return true;
                }
            };
}