package com.example.cameraapidemo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
        implements SurfaceHolder.Callback {

    private Camera camera;
    SoundPool soundPool;
    int beepId;
    File pictureDir = new File(Environment
            .getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES),
            "CameraAPIDemo");
    private static final String TAG = "camera";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pictureDir.mkdirs();

        soundPool = new SoundPool(1,
                AudioManager.STREAM_NOTIFICATION, 0);
        Uri uri = Settings.System.DEFAULT_RINGTONE_URI;
        beepId = soundPool.load(uri.getPath(), 1);
        SurfaceView surfaceView = (SurfaceView)
                findViewById(R.id.surfaceview);
        surfaceView.getHolder().addCallback(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.GINGERBREAD) {
                camera = Camera.open(0);
            } else {
                camera = Camera.open();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (camera != null) {
            try {
                camera.release();
                camera = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void enableButton(boolean enabled) {
        Button button = (Button) findViewById(R.id.button1);
        button.setEnabled(enabled);
    }

    public void takePicture(View view) {
        enableButton(false);
        camera.takePicture(shutterCallback, null,
                pictureCallback);
    }

    private ShutterCallback shutterCallback =
            new ShutterCallback() {
                @Override
                public void onShutter() {
                    // play sound
                    soundPool.play(beepId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            };

    private PictureCallback pictureCallback =
            new PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data,
                                           final Camera camera) {
                    Toast.makeText(MainActivity.this, "Saving image",
                            Toast.LENGTH_LONG)
                            .show();
                    File pictureFile = new File(pictureDir,
                            System.currentTimeMillis() + ".jpg");

                    try {
                        FileOutputStream fos = new FileOutputStream(
                                pictureFile);
                        fos.write(data);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        Log.d(TAG, e.getMessage());
                    } catch (IOException e) {
                        Log.d(TAG, e.getMessage());
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                enableButton(true);
                                camera.startPreview();
                            } catch (Exception e) {
                                Log.d("camera",
                                        "Error starting camera preview: "
                                                + e.getMessage());
                            }
                        }
                    }, 2000);
                }
            };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e){
            Log.d("camera", e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,
                               int format, int w, int h3) {
        if (holder.getSurface() == null){
            Log.d(TAG, "surface does not exist, return");
            return;
        }

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e){
            Log.d("camera", e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
    }
}