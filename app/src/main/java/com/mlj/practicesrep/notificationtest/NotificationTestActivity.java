package com.mlj.practicesrep.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
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
    private Button mNotification6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        mNotification1 = findViewById(R.id.notification1);
        mNotification2 = findViewById(R.id.notification2);
        mNotification3 = findViewById(R.id.notification3);
        mNotification4 = findViewById(R.id.notification4);
        mNotification5 = findViewById(R.id.notification5);
        mNotification6 = findViewById(R.id.notification6);
        mNotification6.setOnClickListener(v -> {
            //Context context, int id, String title, String content, String[] messages, Intent intent
            //也是根据内容动态调整布局的，不是写死固定的位置显示固定的内容
            //如String[]就一条内容，标准的title和content就不显示了
            //就是说没有规律，要么符合风格的内容全部写上，不符合规格的可能就乱了
            NotificationUtil.showInboxNotification(getApplicationContext(),1,"inBoxbigContentTitle","inBoxSummaryText",new String[]{"这是第一行","这是第二行"},new Intent(NotificationTestActivity.this,MainActivity.class));
        });
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
        //AudioManger 拿到通知音量
        AudioManager audioManager = (AudioManager)(this.getSystemService(Context.AUDIO_SERVICE));
        int streamMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
        System.out.println("audioManager: streamMaxVolume="+streamMaxVolume);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            int streamMinVolume = audioManager.getStreamMinVolume(AudioManager.STREAM_NOTIFICATION);
            System.out.println("audioManager: streamMinVolume="+streamMinVolume);
        }
        int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        System.out.println("audioManager: streamVolume="+streamVolume);
        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,7,AudioManager.FLAG_SHOW_UI);

        /*
        * flags 参数的作用
flags 参数指定了设置音量时的行为，例如是否显示音量调整 UI、是否强制设置音量等。它的值通常来自 AudioManager 类中定义的常量。

可用的取值范围（标志常量）
以下是 AudioManager 中与 setStreamVolume() 相关的常用标志：

0
表示没有特殊行为，直接设置音量，不显示 UI。这是默认值，也是你之前代码中使用的值。
示例：audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, 0)
AudioManager.FLAG_SHOW_UI (值为 1)
显示音量调整的用户界面（例如音量条），让用户看到音量变化。
示例：audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_SHOW_UI)
AudioManager.FLAG_PLAY_SOUND (值为 4)
在设置音量时播放提示音（如果适用），以便用户听到音量变化的效果。
示例：audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_PLAY_SOUND)
AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE (值为 8)
移除与音量相关的提示音和振动（通常用于静音场景）。
示例：audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)
AudioManager.FLAG_VIBRATE (值为 16)
在设置音量时触发振动（如果设备支持且振动设置已启用）。
示例：audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_VIBRATE)
AudioManager.FLAG_ALLOW_RINGER_MODES (值为 2)
允许在设置音量时影响响铃模式（例如在静音模式下仍可调整音量）。
示例：audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_ALLOW_RINGER_MODES)
        * */
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