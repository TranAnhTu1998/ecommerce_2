package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Long, Void, Void> {
    DBHelper dbHelper;
    MyPagerAdapter adapter;

    public MyAsyncTask(DBHelper dbHelper, MyPagerAdapter adapter) {
        super();
        this.dbHelper = dbHelper;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Long... params) {
        long id = params[0];

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE mytable SET name = ?, total = total - 1 WHERE id = ?", new String[]{ "test", String.valueOf(id) });
        db.close();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.adapter.updateCursor();
    }
}
