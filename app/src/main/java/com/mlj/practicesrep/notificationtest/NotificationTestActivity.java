package com.mlj.practicesrep.notificationtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.mlj.practicesrep.MainActivity;
import com.mlj.practicesrep.R;

import java.util.Random;

public class NotificationTestActivity extends AppCompatActivity {

    private Button mNotification1;
    private Button mNotification2;
    private Button mNotification3;
    private Button mNotification4;
    private Button mNotification5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        mNotification1 = findViewById(R.id.notification1);
        mNotification2 = findViewById(R.id.notification2);
        mNotification3 = findViewById(R.id.notification3);
        mNotification4 = findViewById(R.id.notification4);
        mNotification5 = findViewById(R.id.notification5);
        mNotification1.setOnClickListener(v -> {
            sendNotification1("发送一个消息" + new Random().nextInt(1000));
        });
        mNotification2.setOnClickListener(v -> {
            sendNotification2("发送一个消息" + new Random().nextInt(1000));
        });
        mNotification3.setOnClickListener(v -> {
            sendNotification3("发送一个消息" + new Random().nextInt(1000));
        });
        mNotification4.setOnClickListener(v -> {
            sendNotification4("发送一个消息" + new Random().nextInt(1000));
        });
        mNotification5.setOnClickListener(v -> {
            sendNotification5("发送一个消息" + new Random().nextInt(1000));
        });
    }

    private void sendNotification5(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_IMMUTABLE);


        // Get the layouts to use in the custom notification
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);

        Bitmap closspBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notify2);
        String channelId = "channelId5";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.block_canary_icon)
//                        .setStyle(new NotificationCompat.BigTextStyle()
//                                .setBigContentTitle("bigcontent")
//                                .setSummaryText("sumaryText")
//                                .bigText("这里是bigTextskjdfhskhfskhfskdhfksdhfkshfksjhfkshfkshfksjhfksjhfkshfkshskdfhskjhfkjshfksdhfksjhdfk"))
                        .setStyle(new NotificationCompat.BigTextStyle())
                        .setContentTitle("在前台弹出的title")
                        //.setLargeIcon(closspBitmap)
                        .setCustomContentView(notificationLayout)
                        //.setCustomBigContentView(notificationLayoutExpanded)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "ChannelID=5的title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification4(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_IMMUTABLE);


        Bitmap closspBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notify2);
        String channelId = "channelId4";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.block_canary_icon)
                        .setContentTitle("在前台弹出的title")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setLargeIcon(closspBitmap) //合起来的缩略图
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本这里是很多的文本"))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "ChannelID=4的title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification3(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_IMMUTABLE);


        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notify1);
        Bitmap closspBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notify2);
        String channelId = "channelId3";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.block_canary_icon)
                        .setContentTitle("在前台弹出的title")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setLargeIcon(closspBitmap) //合起来的缩略图
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(myBitmap) //展开大图
                                .bigLargeIcon(null))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "ChannelID=3的title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification2(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_IMMUTABLE);


        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notify1);
        String channelId = "channelId2";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.block_canary_icon)
                        .setContentTitle("在前台弹出的title")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(myBitmap))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "ChannelID=2的title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification1(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_IMMUTABLE);

        String channelId = "channelId1";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.block_canary_icon)
                        .setContentTitle("在前台弹出的title")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "ChannelID=1的title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}