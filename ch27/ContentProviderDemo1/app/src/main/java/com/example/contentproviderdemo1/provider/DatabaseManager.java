package com.example.contentproviderdemo1.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "electric_cars";
    public static final String ID_FIELD = "_id";
    public static final String MAKE_FIELD = "make";
    public static final String MODEL_FIELD = "model";
    public DatabaseManager(Context context) {
        super(context,
                /*db name=*/ "vehicles_db",
                /*cursorFactory=*/ null,
                /*db version=*/1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " (" + ID_FIELD + " INTEGER, "
                + MAKE_FIELD + " TEXT,"
                + MODEL_FIELD + " TEXT,"
                + " PRIMARY KEY (" + ID_FIELD + "));";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1,
                int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // re-create the table
        onCreate(db);
    }

    public long addElectricCar(ContentValues values) {
        Log.d("db", "addElectricCar");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, values);
    }

    // Obtains single ElectricCar
    ContentValues getElectricCar(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {
                        ID_FIELD, MAKE_FIELD, MODEL_FIELD},
                        ID_FIELD + "=?",
                new String[] { String.valueOf(id) }, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put("id", cursor.getLong(0));
            values.put("make", cursor.getString(1));
            values.put("model", cursor.getString(2));
            return values;
        }
        return null;
    }

    public Cursor getElectricCarsCursor(String[] projection,
            String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("provider:" , "projection:" +  projection);
        Log.d("provider:" , "selection:" +  selection);
        Log.d("provider:" , "selArgs:" +  selectionArgs);
        return db.query(TABLE_NAME, projection,
                selection,
                selectionArgs,
                sortOrder,
                null, null, null);
    }

    public int updateElectricCar(String id, String make,
            String model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAKE_FIELD, make);
        values.put(MODEL_FIELD, model);
        return db.update(TABLE_NAME, values, ID_FIELD + " = ?",
                new String[] { id });
    }

    public int deleteElectricCar(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID_FIELD + " = ?",
                new String[] { id });
    }
}