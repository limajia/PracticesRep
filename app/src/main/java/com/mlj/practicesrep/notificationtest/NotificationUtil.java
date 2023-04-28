package com.mlj.practicesrep.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;

import com.mlj.practicesrep.R;

/*
 *在Notification.Builder中只能设置一个样式，因此Notification.InboxStyle和Notification.BigPictureStyle不能同时使用。
 *如果需要在一个通知中同时展示多种信息，可以使用NotificationCompat.Builder和NotificationCompat.Style，
 *其中NotificationCompat.Style可以是以下任意一种样式：BigTextStyle、BigPictureStyle、InboxStyle等
 */
public class NotificationUtil {

    private static final String CHANNEL_ID = "ChannelID";
    private static final String CHANNEL_NAME = "ChannelName";

    public static void showSimpleNotification(Context context, int id, String title, String content, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        //不要使用 new Notification.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.block_canary_icon)
                .setAutoCancel(true);

        if (intent != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        notificationManager.notify(id, builder.build());
    }

    public static void showBigPictureNotification(Context context, int id, String title, String content, Bitmap bitmap, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.block_canary_icon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_canary_icon))
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setAutoCancel(true);

        if (intent != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        notificationManager.notify(id, builder.build());
    }

    public static void showInboxNotification(Context context, int id, String title, String content, String[] messages, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .setBigContentTitle(title)
                .setSummaryText(content);

        for (String message : messages) {
            inboxStyle.addLine(message);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.block_canary_icon)
                .setContentTitle("这里是标准标题")
                .setContentText("这里是标准内容")
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_canary_icon))
                .setStyle(inboxStyle)
                .setAutoCancel(true);

        if (intent != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        notificationManager.notify(id, builder.build());
    }
}
