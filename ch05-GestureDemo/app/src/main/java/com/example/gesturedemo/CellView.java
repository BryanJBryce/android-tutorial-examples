package com.example.gesturedemo;
import android.content.Context;
import android.widget.ImageView;

public class CellView extends ImageView {
    int x;
    int y;

    public CellView(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
    }
}