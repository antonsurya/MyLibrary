package com.example.MyLibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antonius on 15/02/2014.
 */
public class MyLibraryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String MYLIBRARY_TABLE_NAME = "MYLIBRARY";
    private static final String MYLIBRARY_TABLE_CREATE =
            "CREATE TABLE " + MYLIBRARY_TABLE_NAME + " (" +
                    KEY_WORD + " TEXT, " +
                    KEY_DEFINITION + " TEXT);";

    MyLibraryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MYLIBRARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        onCreate(sqLiteDatabase);
    }
}