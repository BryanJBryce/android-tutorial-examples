package com.example.filedemo1;
import java.io.File;
import java.io.PrintWriter;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void cancel(View view) {
        finish();
    }

    public void addNote(View view) {
        String fileName = ((EditText)
                findViewById(R.id.noteTitle))
                .getText().toString();
        String body = ((EditText) findViewById(R.id.noteBody))
                .getText().toString();
        File parent = getFilesDir();
        File file = new File(parent, fileName);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.write(body);
            finish();
        } catch (Exception e) {
            showAlertDialog("Error adding note", e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {

                }
            }
        }
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog alertDialog = new
                AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }
}