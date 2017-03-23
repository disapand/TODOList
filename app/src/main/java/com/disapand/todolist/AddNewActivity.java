package com.disapand.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shado on 2017/3/22.
 */

public class AddNewActivity extends AppCompatActivity {

    private ListItemDatabase database;
    private EditText item_title;
    private Boolean isUpdate;
    private String oldContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new);

        isUpdate = false;
        item_title = (EditText) findViewById(R.id.new_list_item);
        Intent it2 = getIntent();
        oldContent = it2.getStringExtra("todo_content");
        if (oldContent != null && oldContent != "") {
            item_title.setText(oldContent);
            isUpdate = true;
        }

        Button btn_submit = (Button) findViewById(R.id.btn_submit);
        database = new ListItemDatabase(AddNewActivity.this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isUpdate) {
                    database.insertItem(new ListItem(item_title.getText().toString()));
                    Toast.makeText(AddNewActivity.this, "新的记录添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    database.updateItem(oldContent, item_title.getText().toString());
                    Toast.makeText(AddNewActivity.this, "记录修改成功", Toast.LENGTH_SHORT).show();
                }

                isUpdate = false;
                Intent intent = new Intent(AddNewActivity.this, MainActivity.class);
                startActivity(intent);

               /* 隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);*/
            }
        });

    }
}
