package com.example.actionbardemo;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items 
        switch (item.getItemId()) {
            case R.id.action_profile:
                showAlertDialog("Profile", "You selected Profile");
                return true;
            case R.id.action_capture:
                showAlertDialog("Settings",
                        "You selected Settings");
                return true;
            case R.id.action_about:
                showAlertDialog("About", "You selected About");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog alertDialog = new
                AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }
}