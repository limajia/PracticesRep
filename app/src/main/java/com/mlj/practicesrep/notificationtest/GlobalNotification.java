package com.mlj.practicesrep.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.mlj.practicesrep.R;

public class GlobalNotification {

    private static final String CHANNEL_ID = "GlobalNotificationChannel";
    private static final String CHANNEL_NAME = "GlobalNotificationChannelName";
    private static final String CHANNEL_DESC = "GlobalNotificationChannelDesc";

    private NotificationManager notificationManager;
    private Notification.Builder notificationBuilder;

    public GlobalNotification(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            channel.setDescription(CHANNEL_DESC);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder = new Notification.Builder(context, CHANNEL_ID);
        } else {
            notificationBuilder = new Notification.Builder(context);
        }

        // 设置通知栏属性
        notificationBuilder.setSmallIcon(R.drawable.block_canary_icon);
        notificationBuilder.setContentTitle("全局通知栏标题");
        notificationBuilder.setContentText("全局通知栏内容");
        notificationBuilder.setPriority(Notification.PRIORITY_LOW);
    }

    public void show() {
        Notification notification = notificationBuilder.build();
        notificationManager.notify(0, notification);
    }

    public void hide() {
        notificationManager.cancel(0);
    }
}

