package com.example.asus.donationtracker.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super (context, name, factory, version);
    }

    // Called when no db exists on disk
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(LocationDatabaseAdapter.DATABASE_CREATE);
        } catch(Exception e) {
            Log.e("Error", "exception");
        }
    }

    // Called when current db version differs from the version on disk
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "LOCATION");
        // recreate db
        onCreate(db);
    }
}
