package com.example.videodemo;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showCamera() {
        // cannot set the video file 
        Intent intent = new Intent(
                MediaStore.ACTION_VIDEO_CAPTURE);
        // check if the device has a camera: 
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            Toast.makeText(this, "Opening camera failed",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri uri = data.getData();
                    VideoView videoView = (VideoView)
                            findViewById(R.id.videoView);

                    videoView.setVideoURI(uri);
                    videoView.setMediaController(
                            new MediaController(this));
                    videoView.requestFocus();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action cancelled",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}