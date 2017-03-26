package com.disapand.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.disapand.todolist.com.disapand.tools.CommonAdapter;
import com.disapand.todolist.com.disapand.tools.ViewHolder;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private long exitTime = 0;
    private LinkedList<ListItem> list = null;
    private ListItemDatabase database;
    private ItemAdapter itemAdapter;
    private AlertDialog alert;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new ListItemDatabase(MainActivity.this);
        Cursor cursor = database.getAllItems();
        list = new LinkedList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ListItem item = new ListItem();
                item.setTodo_list_title(cursor.getString(cursor.getColumnIndex("content")));
                item.setTodo_list_time(cursor.getString(cursor.getColumnIndex("time")));
                list.add(item);
            }
            cursor.close();
        }

        final ListView l = (ListView) findViewById(R.id.todo_list);
        itemAdapter = new ItemAdapter(this, list, R.layout.list_item);
        l.setAdapter(itemAdapter);

        ImageButton btn_add = (ImageButton) findViewById(R.id.todo_list_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                alert = null;
                builder = new AlertDialog.Builder(MainActivity.this);
                alert = builder.setTitle("您确定要删除这条记录吗？")
                        .setMessage(itemAdapter.getItem(position).getTodo_list_title())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.deleteItem(itemAdapter.getItem(position).getTodo_list_title());
                                list.remove(position);
                                itemAdapter.notifyDataSetChanged();
                            }
                        }).create();
                alert.show();
                return true;
            }
        });

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                intent.putExtra("todo_content", itemAdapter.getItem(position).getTodo_list_title());
                startActivity(intent);
                MainActivity.this.finish();
            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
