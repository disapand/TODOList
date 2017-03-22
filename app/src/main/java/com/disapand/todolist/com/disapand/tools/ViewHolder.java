package com.disapand.todolist.com.disapand.tools;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hh on 2017/3/20.
 */

public class ViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    /**
     * ViewHolder构造方法，初始化ViewHolder
     * @param context
     * @param parent
     * @param layoutId
     * @param position
     */
    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        //初始化存储View组件的容器，其中每一项都为<key, value>(viewId, view)键值对
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * ViewHolder入口方法，初始化ViewHolder
     * @param context           环境上下文
     * @param convertView       复用的View组件
     * @param parent            ViewGroup
     * @param layoutId          ViewHolder对应的布局文件Id
     * @param position          指示布局文件中每个Item的序号
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        //如果covertView不存在，则初始化调用构造方法初始化ViewHolder
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            //如果convertView存在，就用getTag()方法从convertView中取出ViewHolder
            ViewHolder holder = (ViewHolder) convertView.getTag();
            //给ViewHolder同步当前position游标，避免convertView是第8个了，position还指向第一个convertView
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 获取当前covertView
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        //通过viewId来获取对应的组件
        View view = mViews.get(viewId);

        //如果组件不存在，通过convertView的findViewById()方法来获取组件
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            //将组件放进view组件容器中
            mViews.put(viewId, view);
        }

        //存在组件就直接返回该组件
        return (T) view;
    }

    /**
     * 通过viewId为对应TextView控件设置文本内容
     * @param viewId
     * @param txt
     * @return
     */
    public ViewHolder setText(int viewId, String txt) {
        TextView tv = getView(viewId);
        tv.setText(txt);
        return this;
    }

    /**
     * 通过viewId为对应ImageView控件设置资源值
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView img = getView(viewId);
        img.setImageResource(resId);
        return this;
    }

}
