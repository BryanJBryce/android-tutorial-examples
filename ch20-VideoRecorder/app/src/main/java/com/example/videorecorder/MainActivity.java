package com.example.videorecorder;
import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private MediaRecorder mediaRecorder;
    private File outputDir;
    private boolean recording = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File moviesDir = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_MOVIES);
        outputDir = new File(moviesDir,
                "VideoRecorder");
        outputDir.mkdirs();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaRecorder = new MediaRecorder();
        initAndConfigureMediaRecorder();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (recording) {
            try {
                mediaRecorder.stop();
            } catch (IllegalStateException e) {
            }
        }
        releaseMediaRecorder();
        Button button = (Button) findViewById(R.id.button1);
        button.setText("Start");
        recording = false;
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void initAndConfigureMediaRecorder() {
        mediaRecorder.setAudioSource(
                MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder
                .setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(
                MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setVideoFrameRate(10);// make it very low 
        mediaRecorder.setVideoEncoder(
                MediaRecorder.VideoEncoder.MPEG_4_SP);
        mediaRecorder.setAudioEncoder(
                MediaRecorder.AudioEncoder.AMR_NB);
        String outputFile = new File(outputDir,
                System.currentTimeMillis() + ".mp4")
                .getAbsolutePath();

        mediaRecorder.setOutputFile(outputFile);
        SurfaceView surfaceView = (SurfaceView)
                findViewById(R.id.surfaceView);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        mediaRecorder.setPreviewDisplay(surfaceHolder
                .getSurface());
    }

    public void startStopRecording(View view) {
        Button button = (Button) findViewById(R.id.button1);
        if (recording) {
            button.setText("Start");
            try {
                mediaRecorder.stop();
            } catch (IllegalStateException e) {

            }
            releaseMediaRecorder();
        } else {
            button.setText("Stop");
            if (mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();
                initAndConfigureMediaRecorder();
            }
            // prepare MediaRecorder 
            try {
                mediaRecorder.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaRecorder.start();
        }
        recording = !recording;
    }
}