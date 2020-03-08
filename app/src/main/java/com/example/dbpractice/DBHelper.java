package com.example.dbpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static java.security.AccessController.getContext;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBPractice.db";

    public static class Record implements BaseColumns {
        public static final String TABLE_NAME = "DBPracticeRecord";
        public static final String COLUMN_NAME_NAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "phone";
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Record.TABLE_NAME + " (" +
                    Record.COLUMN_NAME_NAME + " TEXT PRIMARY KEY," +
                    Record.COLUMN_NAME_EMAIL+"TEXT,"+
                    Record.COLUMN_NAME_PHONE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Record.TABLE_NAME;
//TODO

    /**
     *
     * @param context :Context object, like Acitivity.
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }





}
