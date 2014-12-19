package com.example.bitmapdemo;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    int sampleSize = 2;
    int imageId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshImage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void scaleDown(View view) {
        if (sampleSize < 8) {
            sampleSize++;
            refreshImage();
        }
    }

    public void scaleUp(View view) {
        if (sampleSize > 2) {
            sampleSize--;
            refreshImage();
        }
    }
    private void refreshImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),
                R.drawable.image1, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;

        StringBuilder imageInfo = new StringBuilder();

        int id = R.drawable.image1;
        if (imageId == 2) {
            id = R.drawable.image2;
            imageInfo.append("Image 2.");
        } else if (imageId == 3) {
            id = R.drawable.image3;
            imageInfo.append("Image 3.");
        } else if (imageId == 4) {
            id = R.drawable.image4;
            imageInfo.append("Image 4.");
        } else {
            imageInfo.append("Image 1.");
        }
        imageInfo.append(" Original Dimension: " + imageWidth
                + " x " + imageHeight);
        imageInfo.append(". MIME type: " + imageType);
        options.inSampleSize = sampleSize;
        options.inJustDecodeBounds = false;
        Bitmap bitmap1 = BitmapFactory.decodeResource(
                getResources(), id, options);
        ImageView imageView1 = (ImageView)
                findViewById(R.id.image_view1);
        imageView1.setImageBitmap(bitmap1);

        TextView sampleSizeText = (TextView)
                findViewById(R.id.sample_size);
        sampleSizeText.setText("" + sampleSize);
        TextView infoText = (TextView)
                findViewById(R.id.image_info);
        infoText.setText(imageInfo.toString());

    }

    public void changeImage(View view) {
        if (imageId < 4) {
            imageId++;
        } else {
            imageId = 1;
        }
        refreshImage();
    }
}