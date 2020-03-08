package com.example.dbpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class DBUtil {
    //DBHelper dbHelper;
    Context context;

    DBUtil(Context con){
        //DBHelper dbHelper = new DBHelper(context);
        context=con;
    }

    public static class Record implements BaseColumns {
        public static final String TABLE_NAME = "DBPracticeRecord";
        public static final String COLUMN_NAME_NAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "phone";
    }

    public void insert(String name,String email,String phone){
        //check input
        // Gets the data repository in write mode
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase dbCheck=dbHelper.getReadableDatabase();
        //TODO
        Cursor c = dbCheck.query(Record.TABLE_NAME,new String [] {Record.COLUMN_NAME_NAME},"username=?",new  String[]{ name },null,null,null,null);
        if(c!=null){
            String query2="INSERT INTO "+Record.TABLE_NAME +" VALUES ("+
                    "'"+name+"',"+
                    "'"+email+"',"+
                    phone+
                    ")";
            db.execSQL(query2);
        }
        else{
            Log.i("SQL","Same name detected.");
        }
        c.close();
    }

    public String read(){
        DBHelper dbHelper = new DBHelper(context);

        String result="INI",name,email,number;
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.query(Record.TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();

        if (c.moveToFirst()!=false) {
            //c.moveToNext();
            c.moveToFirst();
            Log.i("SQL","find "+String.valueOf(c.getCount())+" records");

            name=c.getString(0);
            Log.i("SQL","Find Name "+name);
            email=c.getString(1);
            Log.i("SQL","Find email "+email);
            number=c.getString(2);
            Log.i("SQL","Find number "+number);
            result=result+name+email+number;
            result+="\n";

            while(c.moveToNext()){
                //name=c.getString(c.getColumnIndex(Record.COLUMN_NAME_NAME));
                name=c.getString(0);
                Log.i("SQL","Find Name "+name);
                email=c.getString(1);
                Log.i("SQL","Find email "+email);
                number=c.getString(2);
                Log.i("SQL","Find number "+number);
                //email = c.getString(c.getColumnIndex(Record.COLUMN_NAME_EMAIL));
                //number = c.getString(c.getColumnIndex(Record.COLUMN_NAME_PHONE));
                result=result+name+email+number;
                result+="\n";
            }
            c.close();
            return result;
        }
        else{
            Log.i("SQL","Query result is empty");
            result="Empty table";
            return result;
        }

    }
    public void update(String name,String email,String phone) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase dbCheck = dbHelper.getReadableDatabase();
        Cursor c = dbCheck.query(Record.TABLE_NAME, new String[]{Record.COLUMN_NAME_NAME}, "username=?", new String[]{name}, null, null, null, null);
        if (c != null) {
            name="'"+name+"'";
            email="'"+email+"'";

            String sql="UPDATE  " +Record.TABLE_NAME+
                    " SET "+ Record.COLUMN_NAME_PHONE+" = "+phone+
                    "WHERE "+"username = " +name;
            //TODO: i HAVE NO FUCKIN IDEA WHY "no such column: email" HERE.
            Log.i("SQL",sql);
            db.execSQL(sql);
        }
        else {
            Log.i("SQL","tired to update info that doesn't exist");
        }
        c.close();

    }

    public void delete(String name){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM " +Record.TABLE_NAME+
                " WHERE username = " +"'"+name + "'";
        db.execSQL(sql);

    }


    /*
    public static void cursorToStringArray(Cursor c,
                                           ArrayList<String> arrayList, String columnName) {
        int columnIndex = c.getColumnIndex(columnName);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            arrayList.add(c.getString(columnIndex));
        }
    }
 }
 */
}
