package com.example.preferencedemo1;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.
                getDefaultSharedPreferences(this);
        boolean allowMultipleUsers = sharedPref.getBoolean(
                SettingsActivity.ALLOW_MULTIPLE_USERS, false);
        String envId = sharedPref.getString(
                SettingsActivity.ENVIRONMENT_ID, "");
        String account = sharedPref.getString(
                SettingsActivity.ACCOUNT, "");
        TextView textView = (TextView) findViewById(R.id.info);
        textView.setText("Allow multiple users: " +
                allowMultipleUsers + "\nEnvironment Id: " + envId
                + "\nAccount: " + account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this,
                        SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
