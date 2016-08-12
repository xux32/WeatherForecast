package com.example.xux32.weatherfroecast.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by xux32 on 2016/8/12.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS weather" + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR, daytime VARCHAR, night VARCHAR, temperature VARCHAR, week VARCHAR, wind VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("ALTER TABLE weather ADD COLUMN other STRING");
    }
}
