package com.example.lvhang.androidstudyproject.DatabaseStudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.CURRENT_VERSION;
import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.DATABASE_NAME;

/**
 * Created by lv.hang on 2016/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper ins;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (ins == null) {
            ins = new DBHelper(context.getApplicationContext());
        }
        return ins;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
