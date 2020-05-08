package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "total integer);");

        for (int i = 0; i < 10; i++) {
            db.execSQL("INSERT INTO mytable VALUES(?,?,?)", new Object[]{Integer.toString(i), Integer.toString(i), 10});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
