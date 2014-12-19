package com.example.fragmentdemo1;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
        implements NamesFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onItemSelected(String value) {
        DetailsFragment details = (DetailsFragment)
                getFragmentManager().findFragmentById(
                        R.id.detailsFragment);
        details.showDetails(value);
    }
} 