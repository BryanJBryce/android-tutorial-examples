package com.example.listviewdemo2;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Since we're extending ListActivity, we do 
        // not need to call setContentView(); 

        Context context = getApplicationContext();
        Resources resources = context.getResources();

        String[] items = resources.getStringArray(
                R.array.players);
        Drawable drawable = resources.getDrawable(
                R.drawable.pretty);

        setListAdapter(new PrettyAdapter(context,
                R.layout.pretty_adapter, items, drawable));
    }

    @Override
    public void onListItemClick(ListView listView,
                                View view, int position, long id) {
        Log.d("listView2", "listView:" + listView +
                ", view:" + view.getClass() +
                ", position:" + position );
    }
}