package com.disapand.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shado on 2017/3/22.
 */

public class ListItemDatabase extends SQLiteOpenHelper {

    public ListItemDatabase(Context context) {
        super(context, "todo_list", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists todo_list(" +
                "id integer primary key AUTOINCREMENT," +
                "content varchar," +
                "time varchar)");
    }

    public void insertItem(ListItem listItem) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", listItem.getTodo_list_title());
        contentValues.put("time", listItem.getTodo_list_time());
        database.insert("todo_list", null, contentValues);
    }

    public Cursor getAllItems() {
        SQLiteDatabase database = getWritableDatabase();
        return database.query("todo_list", null, null, null, null, null, "id ASC");
    }

    public void updateItem(String oldContent, String newContent) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", newContent);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        contentValues.put("time", sdf.format(new Date()));
        database.update("todo_list", contentValues,  "content = ?", new String[]{oldContent});
    }

    public void deleteItem(int item_id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("todo_list", "id = ?", new String[]{Integer.toString(item_id)});
    }

    public void deleteItem(String content) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("todo_list", "content = ?", new String[]{content});
    }

    public void deleteItems() {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("todo_list", null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
