package com.disapand.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hh on 2017/3/22.
 */

public class ListItem {
    private String todo_list_title;
    private String todo_list_time;

    public ListItem() {
    }

    public ListItem(String todo_list_title) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        this.todo_list_time = sdf.format(new Date());
        this.todo_list_title = todo_list_title;
    }

    public String getTodo_list_title() {
        return todo_list_title;
    }

    public void setTodo_list_title(String todo_list_title) {
        this.todo_list_title = todo_list_title;
    }

    public void setTodo_list_time(String todo_list_time) {
        this.todo_list_time = todo_list_time;
    }

    public String getTodo_list_time() {
        return todo_list_time;
    }
}
