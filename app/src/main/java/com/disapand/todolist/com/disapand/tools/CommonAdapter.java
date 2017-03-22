package com.disapand.todolist.com.disapand.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;

/**
 * Created by hh on 2017/3/20.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context context;
    protected LinkedList<T> datas;
    protected LayoutInflater mLayout;
    private int layoutId;

    public CommonAdapter(Context context, LinkedList<T> datas, int layoutId) {
        this.context = context;
        mLayout = LayoutInflater.from(context);
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //获取ViewHolder
        ViewHolder holder = ViewHolder.get(context, convertView, parent, layoutId, position);
        //绑定holder中对应的view组件,需要具体实现该方法
        covert(holder, getItem(position));
        //返回holder对应的convertView,而不应该返回当前的convertView,因为convertView定义在holder中
        return holder.getConvertView();
    }


    public abstract void covert(ViewHolder holder, T t);

}
