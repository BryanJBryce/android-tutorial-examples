package com.example.contentresolverdemo1;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(
                R.id.listView);
        Cursor cursor = getContentResolver().query(
                Util.CONTENT_URI,
                /*projection=*/ new String[] {
                        "_ID", "make", "model"},
                /*selection=*/ null,
                /*selectionArgs=*/ null,
                /*sortOrder=*/ "make");
//        Cursor cursor = managedQuery(Util.CONTENT_URI,
//                null, null, null, null);
        startManagingCursor(cursor);
        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursor,
                new String[] {Util.MAKE_FIELD,
                        Util.MODEL_FIELD},
                new int[] {android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> adapterView,
                            View view, int position, long id) {
//                        Intent intent = new Intent(
//                                getApplicationContext(),
//                                ShowElectricCarActivity.class);
//                        intent.putExtra("id", id);
//                        startActivity(intent);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_add:
//                startActivity(new Intent(this,
//                        AddElectricCarActivity.class));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}