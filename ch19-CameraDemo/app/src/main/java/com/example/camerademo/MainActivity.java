package com.example.camerademo;
import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    File pictureDir = new File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES), "CameraDemo");
    private static final String FILE_NAME = "image01.jpg";

    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!pictureDir.exists()) {
            pictureDir.mkdirs();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                showCamera();
                return true;
            case R.id.action_email:
                emailPicture();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showCamera() {
        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File image = new File(pictureDir, FILE_NAME);
        fileUri = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // check if the device has a camera: 
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,
                    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (requestCode ==
                CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ImageView imageView = (ImageView)
                        findViewById(R.id.imageView);
                File image = new File(pictureDir, FILE_NAME);
                fileUri = Uri.fromFile(image);
                imageView.setImageURI(fileUri);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this,  "Action cancelled",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,  "Error",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void emailPicture() {
        Intent emailIntent = new Intent(
                android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[]{"me@example.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "New photo");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                "From My App");
        emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        startActivity(Intent.createChooser(emailIntent,
                "Send mail..."));
    }
}