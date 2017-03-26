package com.disapand.todolist;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shado on 2017/3/26.
 */

public class TimerService extends Service {
    private Timer timer;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateViews();
            }
        }, 0, 1000);
    }

    private void updateViews(){
        String time = simpleDateFormat.format(new Date());
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.time_widget);
        remoteViews.setTextViewText(R.id.appwidget_text, time);
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getApplicationContext(), TimeWidget.class);
        manager.updateAppWidget(componentName, remoteViews);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer = null;
    }
}
