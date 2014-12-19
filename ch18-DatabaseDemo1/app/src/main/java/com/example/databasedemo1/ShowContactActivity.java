package com.example.databasedemo1;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowContactActivity extends Activity {
    long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            contactId = extras.getLong("id");
            DatabaseManager dbMgr = new DatabaseManager(this);
            Contact contact = dbMgr.getContact(contactId);
            if (contact != null) {
                ((TextView) findViewById(R.id.firstName))
                        .setText(contact.getFirstName());
                ((TextView) findViewById(R.id.lastName))
                        .setText(contact.getLastName());
                ((TextView) findViewById(R.id.phone))
                        .setText(contact.getPhone());
                ((TextView) findViewById(R.id.email))
                        .setText(contact.getEmail());
            } else {
                Log.d("db", "contact null");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteContact() {
        new AlertDialog.Builder(this)
                .setTitle("Please confirm")
                .setMessage(
                        "Are you sure you want to delete " +
                                "this contact?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog,
                                    int whichButton) {
                                DatabaseManager dbMgr =
                                        new DatabaseManager(
                                                getApplicationContext());
                                dbMgr.deleteContact(contactId);
                                dialog.dismiss();
                                finish();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog,
                                    int which) {
                                dialog.dismiss();
                            }
                        })
                .create()
                .show();
    }
}