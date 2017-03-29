package com.disapand.todolist;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class TimeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        /*CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.time_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);*/
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
       /* for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
//       super.onUpdate(context, appWidgetManager, appWidgetIds);
        ComponentName cn = new ComponentName(context, TimeWidget.class);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.time_widget);

        Intent intent = new Intent(context, WidgetItemService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[0]);

        rv.setRemoteAdapter(R.id.widget_list, intent);

//        Intent clickIntent = new Intent(context, TimeWidget.class);
//
//        clickIntent.setAction(clickAction);
        appWidgetManager.updateAppWidget(cn, rv);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        super.onEnabled(context);
        context.startService(new Intent(context, TimerService.class));
        Toast.makeText(context, "Widget被添加", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
        context.stopService(new Intent(context, TimerService.class));
        Toast.makeText(context, "Widget已经被删除", Toast.LENGTH_SHORT).show();
    }
}

