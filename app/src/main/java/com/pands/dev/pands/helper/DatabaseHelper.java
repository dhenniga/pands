package com.pands.dev.pands.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David on 06/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pands.db";

    public static final String TABLE_NAME = "LoggedInUser";


    public static final String ID = "id";
    public static final String USER_ID = "userID";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String COOKIE = "cookie";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id integer primary key autoincrement, userID integer, firstName text, lastName text, cookie text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE if EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData (int userID, String firstName, String lastName, String cookie) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(USER_ID, userID);
        contentValue.put(FIRST_NAME, firstName);
        contentValue.put(LAST_NAME, lastName);
        contentValue.put(COOKIE, cookie);

        long result = db.insert(TABLE_NAME, null, contentValue);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
