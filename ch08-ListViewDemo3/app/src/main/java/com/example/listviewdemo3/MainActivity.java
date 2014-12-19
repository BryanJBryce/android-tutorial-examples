package com.example.listviewdemo3;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities = {"Rome", "Venice", "Basel"};
        ArrayAdapter<String> adapter1 = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,
                cities);
        ListView listView1 = (ListView)
                findViewById(R.id.listView1);
        listView1.setAdapter(adapter1);
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adapter2 = new
                ArrayAdapter<String>(this,
                R.layout.list_item, cities);
        ListView listView2 = (ListView)
                findViewById(R.id.listView2);
        listView2.setAdapter(adapter2);
        listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}