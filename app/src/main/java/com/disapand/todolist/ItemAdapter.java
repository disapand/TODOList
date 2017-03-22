package com.disapand.todolist;

import android.content.Context;

import com.disapand.todolist.com.disapand.tools.CommonAdapter;
import com.disapand.todolist.com.disapand.tools.ViewHolder;

import java.util.LinkedList;

/**
 * Created by shado on 2017/3/22.
 */

public class ItemAdapter extends CommonAdapter<ListItem> {

    public ItemAdapter(Context context, LinkedList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void covert(ViewHolder holder, ListItem listItem) {
        holder.setText(R.id.todo_list_title, listItem.getTodo_list_title());
        holder.setText(R.id.todo_list_time, listItem.getTodo_list_time());
    }
}
