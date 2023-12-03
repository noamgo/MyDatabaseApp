package com.example.mymenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "students.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "students_table";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_SURNAME = "SURNAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PHONE = "PHONE";


    public DatabaseHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
      //  SQLiteDatabase db = this.getWritableDatabase();
    }
       @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " TEXT, " + COL_NAME + " TEXT, " + COL_SURNAME + " TEXT, " + COL_EMAIL + " TEXT, " + COL_PHONE + " TEXT);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String id, String name, String surname, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SURNAME, surname);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PHONE, phone);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public Cursor getDataByName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME + " LIKE '%" + name + "%'", null);
        return res;
    }

    public boolean updateData(String id, String name, String surname, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if data with the given ID exists
        if (!dataExists(db, id)) {
            return false; // No data to update
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SURNAME, surname);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PHONE, phone);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    // Helper method to check if data with a given ID exists
    private boolean dataExists(SQLiteDatabase db, String id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?", new String[]{id});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean dataExists(SQLiteDatabase db, String id, String email) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? AND " + COL_EMAIL + " = ?", new String[]{id, email});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
