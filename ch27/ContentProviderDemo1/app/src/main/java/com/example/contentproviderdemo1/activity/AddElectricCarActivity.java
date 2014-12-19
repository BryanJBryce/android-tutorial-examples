package com.example.contentproviderdemo1.activity;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.contentproviderdemo1.provider.ElectricCarContentProvider;
import com.example.contentproviderdemo1.R;

public class AddElectricCarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_electric_car);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_electric_car, menu);
        return true;
    }

    public void cancel(View view) {
        finish();
    }

    public void addElectricCar(View view) {
        String make = ((EditText) findViewById(
                R.id.make)).getText().toString();
        String model = ((EditText) findViewById(
                R.id.model)).getText().toString();
        ContentValues values = new ContentValues();
        values.put("make", make);
        values.put("model", model);
        getContentResolver().insert(
                ElectricCarContentProvider.CONTENT_URI, values);
        finish();
    }
}