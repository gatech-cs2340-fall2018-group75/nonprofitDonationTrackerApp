package com.example.asus.donationtracker.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocationDatabaseAdapter {
    static final String DATABASE_NAME = "database.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getPassword="";
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.

    // SQL query string
    static final String DATABASE_CREATE = "create table LOCATION(" +
            "KEY integer primary key autoincrement," +
            "NAME text," +
            "LATITUDE float," +
            "LONGITUDE float," +
            "STREET_ADDRESS text," +
            "CITY text," +
            "STATE char(2)," +
            "ZIP varchar," +
            "TYPE text," +
            "PHONE varchar," +
            "WEBSITE varchar); ";

    public static SQLiteDatabase db;
    private final Context context;
    private static DatabaseHelper dbHelper;

    public LocationDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LocationDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public static SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public String insertEntry(String name) {
        try {
            ContentValues vals = new ContentValues();
            vals.put("NAME", name);
            vals.put("LATITUDE", 0.0);
            vals.put("LONGITUDE", 0.0);
            vals.put("STREET_ADDRESS", "");
            vals.put("CITY", "");
            vals.put("STATE", "GA");
            vals.put("ZIP", "");
            vals.put("TYPE", "");
            vals.put("PHONE", "");
            vals.put("WEBSITE", "");

            db = dbHelper.getWritableDatabase();
            long result = db.insert("LOCATION", null, vals);
            Log.d("DB", "Row added to location table successfully");
        } catch (Exception e) {
            System.out.println(e);
            Log.e("DB", "Unable to add row to location table");
        }
        return ok;
    }

    public Cursor getEntries() {
        return db.query("LOCATION", null, null, null, null, null, null);
    }
}
