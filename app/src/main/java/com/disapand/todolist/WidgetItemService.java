package com.disapand.todolist;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2017/3/29.
 */

public class WidgetItemService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ItemFactory(this.getApplicationContext(), intent);
    }

    private class ItemFactory implements RemoteViewsFactory {

        private Context context;
        private Intent intent;
        private List<String> list = new ArrayList<>();

        public ItemFactory(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
        }

        @Override
        public void onCreate() {
            for (int i = 0; i < 5; i++) {
                list.add("item" + i);
            }
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {
            list.clear();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (position <0 || position> list.size())
                return null;
            String content = list.get(position);
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
            rv.setTextViewText(R.id.widget_list_item, content);

            Intent intent1 = new Intent();
            intent1.putExtra("content", content);
            rv.setOnClickFillInIntent(R.id.widget_list_item, intent1);
            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
