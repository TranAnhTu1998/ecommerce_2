package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyPagerAdapter extends CursorRecyclerAdapter<PagerVH> {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public MyPagerAdapter(DBHelper dbHelper) {
        super(null);
        this.dbHelper = dbHelper;
        db = dbHelper.getReadableDatabase();
        updateCursor();
    }

    public void updateCursor() {
        if (!db.isOpen()) {
            db = dbHelper.getReadableDatabase();
        }
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        swapCursor(c);
    }

    public PagerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false);
        return new PagerVH(itemView);
    }

    public void onBindViewHolder(PagerVH holder, Cursor c) {
        holder.name.setText("item " + c.getString(1));
        holder.total.setText(String.valueOf(c.getInt(2)));

        final long id = c.getLong(0);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            MyAsyncTask task = new MyAsyncTask(dbHelper, MyPagerAdapter.this);
            task.execute(id);
            }
        });
    }
}

class PagerVH extends RecyclerView.ViewHolder {
    TextView name;
    TextView total;
    Button button;

    public PagerVH(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        total = itemView.findViewById(R.id.total);
        button = itemView.findViewById(R.id.button);
    }
}
