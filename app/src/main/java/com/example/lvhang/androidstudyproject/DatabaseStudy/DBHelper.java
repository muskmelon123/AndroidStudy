package com.example.lvhang.androidstudyproject.DatabaseStudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.CONTACT_ID;
import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.CONTACT_NAME;
import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.CONTACT_TABLE;
import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.CURRENT_VERSION;
import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.DATABASE_NAME;

/**
 * Created by lv.hang on 2016/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREATE_CONTACT_TABLE = "create table " + CONTACT_TABLE + "(" +
            CONTACT_ID + " BIGINT, " +
            CONTACT_NAME + " TEXT)";
    private static DBHelper ins;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (ins == null) {
            //这里使用context.getApplicationContext()，表示dbhelper的生命周期和app的生命周期一样，不使用activity的生命周期
            //以免发生leak的现象。
            ins = new DBHelper(context.getApplicationContext());
        }
        return ins;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + CONTACT_TABLE);
        onCreate(db);
    }
}
