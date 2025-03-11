package com.mlj.practicesrep.fullscreenintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import com.mlj.practicesrep.R;

public class FullScreenIntentTestActivity extends AppCompatActivity {

    private PendingIntent fullScreenIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_inten);

        initNotify();

        findViewById(R.id.btnSend).setOnClickListener(v -> {
            // 发送通知
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                    "high_priority_channel_id")
                    .setSmallIcon(R.drawable.block_canary_icon)
                    .setContentTitle("Important Notification")
                    .setContentText("This is a high priority notification.")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setFullScreenIntent(fullScreenIntent, true);// 设置悬挂式通知
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        });

        findViewById(R.id.btnSetting).setOnClickListener(v -> {
            // 跳转到fullScreenIntent设置页面
            //添加Intent action 检查
            Intent intent = new Intent("android.settings.MANAGE_APP_USE_FULL_SCREEN_INTENT");
            if (intent.resolveActivity(getPackageManager()) != null) {
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            } else {
                // 无法跳转到设置页面
                Toast.makeText(this, "无法跳转到设置页面,android14 添加的该常量", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.btnCheck).setOnClickListener(v -> {
            // 检查通知是否正在展示
            if (isFullScreenIntentNotificationActive()) {
                // 通知正在展示
                Toast.makeText(this, "通知正在展示", Toast.LENGTH_SHORT).show();
            } else {
                // 通知未展示
                Toast.makeText(this, "通知未展示", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 检查是否有正在展示的 fullScreenIntent 通知
    private boolean isFullScreenIntentNotificationActive() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
            for (StatusBarNotification notification : activeNotifications) {
                if (notification.getId() == 1 &&
                        notification.getNotification().fullScreenIntent != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private void initNotify() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "high_priority_channel_id",
                    "High Priority Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, FullScreenIntentActivity.class);
        fullScreenIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}