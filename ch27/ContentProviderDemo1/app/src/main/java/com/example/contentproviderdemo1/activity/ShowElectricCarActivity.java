package com.example.contentproviderdemo1.activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.contentproviderdemo1.R;
import com.example.contentproviderdemo1.Util;

public class ShowElectricCarActivity extends Activity {
    long electricCarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_electric_car);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            electricCarId = extras.getLong("id");
            Cursor cursor = getContentResolver().query(
                    Util.CONTENT_URI,
                    /*projection=*/ new String[] {
                            Util.ID_FIELD, Util.MAKE_FIELD,
                            Util.MODEL_FIELD},
                    /*selection=*/ "_id=?",
                    /*selectionArgs*/ new String[] {
                            Long.toString(electricCarId)},
                    /*sortOrder*/ null);
            if (cursor != null && cursor.moveToFirst()) {
                String make = cursor.getString(1);
                String model = cursor.getString(2);
                ((TextView) findViewById(R.id.make))
                        .setText(make);
                ((TextView) findViewById(R.id.model))
                        .setText(model);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_electric_car, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteElectricCar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteElectricCar() {
        new AlertDialog.Builder(this)
            .setTitle("Please confirm")
            .setMessage(
                    "Are you sure you want to delete " +
                            "this electric car?")
            .setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {
                        Uri uri = ContentUris.withAppendedId(
                                Util.CONTENT_URI, electricCarId);
                        getContentResolver().delete(
                                uri, null, null);
                        dialog.dismiss();
                        finish();
                    }
                })
            .setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(
                                DialogInterface dialog,
                                int which) {
                            dialog.dismiss();
                        }
                    })
            .create()
            .show();
    }

    public void updateElectricCar(View view) {
        Uri uri = ContentUris.withAppendedId(Util.CONTENT_URI,
                electricCarId);
        ContentValues values = new ContentValues();
        values.put(Util.MAKE_FIELD,
                ((EditText)findViewById(R.id.make)).getText()
                        .toString());
        values.put(Util.MODEL_FIELD,
                ((EditText)findViewById(R.id.model)).getText()
                        .toString());
        getContentResolver().update(uri, values, null, null);
        finish();
    }
}