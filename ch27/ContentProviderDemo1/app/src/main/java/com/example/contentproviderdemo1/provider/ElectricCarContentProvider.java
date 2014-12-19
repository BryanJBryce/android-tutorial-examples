package com.example.contentproviderdemo1.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class ElectricCarContentProvider extends ContentProvider {

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.contentproviderdemo1"
                    + "/electric_cars");

    public ElectricCarContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection,
                      String[] selectionArgs) {
        String id = uri.getPathSegments().get(1);
        return dbMgr.deleteElectricCar(id);
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = getDatabaseManager().addElectricCar(values);
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    @Override
    public boolean onCreate() {
        // initialize content provider on startup
        // for this example, nothing to do
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        if (uri.equals(CONTENT_URI)) {
            return getDatabaseManager()
                    .getElectricCarsCursor(projection, selection,
                            selectionArgs, sortOrder);
        } else {
            return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection,
                      String[] selectionArgs) {
        String id = uri.getPathSegments().get(1);
        Log.d("provider", "update in CP. uri:"  + uri);
        DatabaseManager databaseManager = getDatabaseManager();
        String make = values.getAsString("make");
        String model = values.getAsString("model");
        return databaseManager.updateElectricCar(id, make, model);
    }

    private DatabaseManager dbMgr;
    private DatabaseManager getDatabaseManager() {
        if (dbMgr == null) {
            dbMgr = new DatabaseManager(getContext());
        }
        return dbMgr;
    }
}