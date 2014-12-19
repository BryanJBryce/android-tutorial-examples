package com.example.listviewdemo2;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PrettyAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private String[] items;
    private Drawable icon;
    private int viewResourceId;

    public PrettyAdapter(Context context,
                         int viewResourceId, String[] items, Drawable icon) {
        super(context, viewResourceId, items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.icon = icon;
        this.viewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public String getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);

        ImageView imageView = (ImageView)
                convertView.findViewById(R.id.icon);
        imageView.setImageDrawable(icon);

        TextView textView = (TextView)
                convertView.findViewById(R.id.label);
        textView.setText(items[position]);
        return convertView;
    }
}