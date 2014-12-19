package com.example.filedemo1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(
                R.id.listView1);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int position, long id) {
                        readNote(position);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
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
            case R.id.action_add:
                startActivity(new Intent(this,
                        AddNoteActivity.class));
                return true;
            case R.id.action_delete:
                deleteNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshList() {
        ListView listView = (ListView) findViewById(
                R.id.listView1);
        String[] titles = fileList();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_activated_1,
                        titles);
        listView.setAdapter(arrayAdapter);
    }

    private void readNote(int position) {
        String[] titles = fileList();
        if (titles.length > position) {
            selectedItem = titles[position];
            File dir = getFilesDir();
            File file = new File(dir, selectedItem);
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                StringBuilder sb = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = bufferedReader.readLine();
                }
                ((TextView) findViewById(R.id.textView1)).
                        setText(sb.toString());
            } catch (IOException e) {

            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    private void deleteNote() {
        if (selectedItem != null) {
            deleteFile(selectedItem);
            selectedItem = null;
            ((TextView) findViewById(R.id.textView1)).setText("");
            refreshList();
        }
    }
}