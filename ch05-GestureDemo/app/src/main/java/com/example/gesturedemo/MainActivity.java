package com.example.gesturedemo;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    int rowCount = 7;
    int cellCount = 7;
    ImageView imageView1;
    ImageView imageView2;
    CellView[][] cellViews;
    int downX;
    int downY;
    boolean swapping = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        LinearLayout.LayoutParams matchParent =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_VERTICAL);

        addContentView(root, matchParent);


        // create row
        cellViews = new CellView[rowCount][cellCount];
        LinearLayout.LayoutParams rowLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        ViewGroup.LayoutParams cellLayoutParams =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        int count = 0;
        for (int i = 0; i < rowCount; i++) {
            CellView[] cellRow = new CellView[cellCount];
            cellViews[i] = cellRow;

            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(rowLayoutParams);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_HORIZONTAL);
            root.addView(row);
            // create cells
            for (int j = 0; j < cellCount; j++) {
                CellView cellView = new CellView(this, j, i);
                cellRow[j] = cellView;
                if (count == 0) {
                    cellView.setImageDrawable(
                            getResources().getDrawable(
                                    R.drawable.image1));
                } else if (count == 1) {
                    cellView.setImageDrawable(
                            getResources().getDrawable(
                                    R.drawable.image2));
                } else {
                    cellView.setImageDrawable(
                            getResources().getDrawable(
                                    R.drawable.image3));
                }
                count++;
                if (count == 3) {
                    count = 0;
                }
                cellView.setLayoutParams(cellLayoutParams);
                cellView.setOnTouchListener(touchListener);
                row.addView(cellView);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void swapImages(CellView v1, CellView v2) {
        Drawable drawable1 = v1.getDrawable();
        Drawable drawable2 = v2.getDrawable();
        v1.setImageDrawable(drawable2);
        v2.setImageDrawable(drawable1);
    }

    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            CellView cellView = (CellView) v;

            int action = event.getAction();
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    downX = cellView.x;
                    downY = cellView.y;
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    if (swapping) {
                        return true;
                    }
                    float x = event.getX();
                    float y = event.getY();
                    int w = cellView.getWidth();
                    int h = cellView.getHeight();
                    if (downX < cellCount - 1
                            && x > w && y >= 0 && y <= h) {
                        // swap with right cell
                        swapping = true;
                        swapImages(cellView,
                                cellViews[downY][downX + 1]);
                    } else if (downX > 0 && x < 0
                            && y >=0 && y <= h) {
                        // swap with left cell
                        swapping = true;
                        swapImages(cellView,
                                cellViews[downY][downX - 1]);
                    } else if (downY < rowCount - 1
                            && y > h && x >= 0 && x <= w) {
                        // swap with cell below
                        swapping = true;
                        swapImages(cellView,
                                cellViews[downY + 1][downX]);
                    } else if (downY > 0 && y < 0
                            && x >= 0 && x <= w) {
                        // swap with cell above
                        swapping = true;
                        swapImages(cellView,
                                cellViews[downY - 1][downX]);
                    }
                    return true;
                case (MotionEvent.ACTION_UP):
                    swapping = false;
                    return true;
                default:
                    return true;
            }
        }
    };
}