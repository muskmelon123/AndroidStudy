package com.example.lvhang.androidstudyproject.DatabaseStudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import static com.example.lvhang.androidstudyproject.DatabaseStudy.DBContants.*;

/**
 * Created by lv.hang on 2016/11/18.
 */

public class DBOperator {
    private static String TAG = "DBOperator";
    private DBHelper dbHelper;

    public DBOperator(Context context) {
        this.dbHelper = DBHelper.getInstance(context);
    }

    public void insertOrUpdateContact(Contact contact) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_ID, contact.getContactId());
        contentValues.put(CONTACT_NAME, contact.getName());

        String selection = CONTACT_ID + " = ?";
        String[] selectionArgs = new String[]{contact.getContactId() + ""};
        Cursor cursor = database.query(CONTACT_TABLE, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            database.update(CONTACT_TABLE, contentValues, selection, selectionArgs);
            Log.i(TAG, "update contact: contactId: " + contact.getContactId() + " contactName: " + contact.getName());
        } else {
            database.insert(CONTACT_TABLE, null, contentValues);
            Log.i(TAG, "insert contact: contactId: " + contact.getContactId() + " contactName: " + contact.getName());
        }
        //cursor使用完要关闭,
        // 如果cursor不关闭，多线程插单个联系人的时候会报
        // Could not allocate CursorWindow '/data/data/com.example.lvhang.androidstudyproject/databases/db_study.db' of size 2097152 due to error
        //插多个联系人不能用多个线程插一个联系人的情况，就算cursor.close()之后，也会报内存溢出
        // java.lang.OutOfMemoryError: pthread_create (stack size 16384 bytes) failed: Try again at java.lang.VMThread.create(Native Method)
        if (cursor != null) {
            cursor.close();
        }
    }

    public void insertOrUpdateContacts(List<Contact> contacts) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.i("time", "insert contact begin");
        database.beginTransaction();
        Cursor cursor = null;
        for (Contact contact : contacts) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CONTACT_ID, contact.getContactId());
            contentValues.put(CONTACT_NAME, contact.getName());

            String selection = CONTACT_ID + " = ?";
            String[] selectionArgs = new String[]{contact.getContactId() + ""};
            cursor = database.query(CONTACT_TABLE, null, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                database.update(CONTACT_TABLE, contentValues, selection, selectionArgs);
                Log.i(TAG, "update contact: contactId: " + contact.getContactId() + " contactName: " + contact.getName());
            } else {
                database.insert(CONTACT_TABLE, null, contentValues);
                Log.i(TAG, "insert contact: contactId: " + contact.getContactId() + " contactName: " + contact.getName());
            }
            //cursor使用完要关闭,如果不关闭插入数据过多会报android.database.CursorWindowAllocationException: Cursor window allocation of 2048 kb failed.
            if (cursor != null) {
                cursor.close();
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        //不加transaction，插入2000条数据耗时32s,加transaction,插入2000条数据耗时20s
        Log.i("time", "insert contact over");
    }

    public Contact getContact(long contactId) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String selection = CONTACT_ID + " = ?";
        String[] selectionArgs = new String[]{contactId + ""};
        Cursor cursor = database.query(CONTACT_TABLE, null, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Contact contact = new Contact();
            String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
            contact.setContactId(contactId);
            contact.setName(contactName);
            cursor.close();
            return contact;
        }
        cursor.close();
        return null;
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String selection = CONTACT_ID + " = ?";
        String[] selectionArgs = new String[]{contact.getContactId() + ""};
        Cursor cursor = database.query(CONTACT_TABLE, null, selection, selectionArgs, null, null, null);
        database.delete(CONTACT_TABLE, selection, selectionArgs);
        cursor.close();
    }
}
