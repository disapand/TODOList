package com.disapand.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.disapand.todolist.com.disapand.tools.CommonAdapter;
import com.disapand.todolist.com.disapand.tools.ListItem;
import com.disapand.todolist.com.disapand.tools.ViewHolder;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private long exitTime = 0;
    private LinkedList<ListItem> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new LinkedList<>();
        list.add(new ListItem("测试一下"));
        list.add(new ListItem("测试一下2"));
        list.add(new ListItem("测试一下3"));
        list.add(new ListItem("测试一下4"));
        list.add(new ListItem("测试一下5"));
        list.add(new ListItem("测试一下6"));
        list.add(new ListItem("测试一下7"));

        ListView l = (ListView) findViewById(R.id.todo_list);

        l.setAdapter(new CommonAdapter<ListItem>(MainActivity.this, list, R.layout.list_item) {

            @Override
            public void covert(ViewHolder holder, ListItem listItem) {
                holder.setText(R.id.todo_list_title,listItem.getTodo_list_title());
                holder.setText(R.id.todo_list_time,listItem.getTodo_list_time());
            }
        });

        ImageButton btn_add = (ImageButton) findViewById(R.id.todo_list_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了add按钮", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000){
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
