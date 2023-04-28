package com.mlj.practicesrep.notificationtest;

public class NoCompat {
   /* 在 Android 8.0 以下创建通知，您需要使用 NotificationCompat 类。但是，如果您不想使用 compat 类，则可以使用 Notification 类来创建通知。以下是一个示例：
    private void sendNotification(String message) {

        android.app.NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("My Notification")
                .setContentText(message)
                .setSmallIcon(R.drawable.notification_icon)
                .build();

        notificationManager.notify(0, notification);
    }
    在上面的代码中，我们首先获得了 NotificationManager 的实例。然后，我们创建了一个 Notification.Builder 对象并设置通知的标题、内容和图标。最后，我们使用 notify() 方法将通知显示在状态栏中。
    请注意，此示例只适用于 Android 8.0 以下的版本。如果您需要支持 Android 8.0 及以上版本，则需要使用 NotificationCompat 类。*/
}
