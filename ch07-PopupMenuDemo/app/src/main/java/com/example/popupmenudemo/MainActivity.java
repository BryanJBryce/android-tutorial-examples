package com.example.popupmenudemo;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends Activity {

    PopupMenu popupMenu;
    PopupMenu.OnMenuItemClickListener menuItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuItemClickListener =
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_delete:
                                Log.d("menu", "Delete clicked");
                                return true;
                            case R.id.action_copy:
                                Log.d("menu", "Copy clicked");
                                return true;
                            default:
                                return false;
                        }
                    }
                };
        Button button = (Button) findViewById(R.id.button1);
        popupMenu = new PopupMenu(this, button);
        popupMenu.setOnMenuItemClickListener(menuItemClickListener);
        popupMenu.inflate(R.menu.popup_menu);
    }

    public void showPopupMenu(View view) {
        popupMenu.show();
    }
}