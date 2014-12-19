package com.example.gridviewdemo1;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {
    private Context context;

    public GridViewAdapter(Context context) {
        this.context = context;
    }
    private int[] icons = {
            android.R.drawable.btn_star_big_off,
            android.R.drawable.btn_star_big_on,
            android.R.drawable.alert_light_frame,
            android.R.drawable.alert_dark_frame,
            android.R.drawable.arrow_down_float,
            android.R.drawable.gallery_thumb,
            android.R.drawable.ic_dialog_map,
            android.R.drawable.ic_popup_disk_full,
            android.R.drawable.star_big_on,
            android.R.drawable.star_big_off,
            android.R.drawable.star_big_on
    };

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(10, 10, 10, 10);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(icons[position]);
        return imageView;
    }
}