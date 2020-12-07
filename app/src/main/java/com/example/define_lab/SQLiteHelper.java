package com.example.define_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "define_lab";
    public static final String TABLE_NAME = "users";
    public static final String Key_data_id = "data_id";
    public static final String Key_name = "name";
    public static final String Key_contact = "contact";
    public static final String Key_location = "location";
    public static final String Key_Star = "star";

    public SQLiteHelper(Context context) {


        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("e","helli");
        String CREATE_TABLE_USERS = " CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT ,data_id VARCHAR NOT NULL,name VARCHAR NOT NULL, contact VARCHAR NOT NULL,location VARCHAR NOT NULL,star VARCHAR NOT NULL)";

        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

}
