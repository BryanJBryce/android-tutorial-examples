package com.example.listviewdemo1;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] values = getResources().getStringArray(
                R.array.players);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.list_item, values);

        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        final View view, int position, long id) {
                    String item = (String)
                            parent.getItemAtPosition(position);
                    AlertDialog.Builder builder = new
                            AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Selected item: "
                            + item).setTitle("ListView");
                    builder.create().show();
                    Log.d("ListView", "Selected item : " + item);
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}