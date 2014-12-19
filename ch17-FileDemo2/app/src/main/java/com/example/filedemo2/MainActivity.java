package com.example.filedemo2;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    class KeyValue {
        public String key;
        public String value;
        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return key;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<KeyValue> keyValues = Arrays.asList(
                new KeyValue("Alarms", Environment.DIRECTORY_ALARMS),
                new KeyValue("DCIM", Environment.DIRECTORY_DCIM),
                new KeyValue("Downloads",
                        Environment.DIRECTORY_DOWNLOADS),
                new KeyValue("Movies", Environment.DIRECTORY_MOVIES),
                new KeyValue("Music", Environment.DIRECTORY_MUSIC),
                new KeyValue("Notifications",
                        Environment.DIRECTORY_NOTIFICATIONS),
                new KeyValue("Pictures",
                        Environment.DIRECTORY_PICTURES),
                new KeyValue("Podcasts",
                        Environment.DIRECTORY_PODCASTS),
                new KeyValue("Ringtones",
                        Environment.DIRECTORY_RINGTONES)
        );
        ArrayAdapter<KeyValue> arrayAdapter = new
                ArrayAdapter<KeyValue>(this,
                android.R.layout.simple_list_item_activated_1,
                keyValues);
        ListView listView1 = (ListView)
                findViewById(R.id.listView1);
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new
             OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView,
                                         View view, int position, long id) {
                     KeyValue keyValue = keyValues.get(position);
                     listDir(keyValue.value);
                 }
             });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void listDir(String dir) {
        File parent = Environment
                .getExternalStoragePublicDirectory(dir);
        String[] files = null;
        if (parent == null || parent.list() == null) {
            files = new String[0];
        } else {
            files = parent.list();
        }
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,
                files);
        ListView listView2 = (ListView)
                findViewById(R.id.listView2);
        listView2.setAdapter(arrayAdapter);
    }
}