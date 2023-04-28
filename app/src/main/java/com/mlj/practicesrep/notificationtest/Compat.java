package com.mlj.practicesrep.notificationtest;

public class Compat {

    /*
    *
    * `NotificationChannelCompat`类是Android支持库中用于创建通知渠道的兼容性类。以下是使用`NotificationChannelCompat`的步骤：

1. 在build.gradle文件中添加以下依赖项以使用Android支持库：

```
implementation 'com.android.support:support-compat:28.0.0'
```

2. 在应用程序中创建通知渠道：

```java
// 创建一个NotificationChannel对象
NotificationChannelCompat channel = new NotificationChannelCompat.Builder(context, channelId)
    .setName(channelName)
    .setDescription(channelDescription)
    .setImportance(NotificationManagerCompat.IMPORTANCE_DEFAULT)
    .build();

// 注册通知渠道
NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
notificationManager.createNotificationChannel(channel);
```

其中，`channelId`是通知渠道的标识符，必须是应用程序内唯一的。`channelName`是通知渠道的显示名称，`channelDescription`是通知渠道的描述。

3. 创建通知时，将通知发送到所创建的通知渠道：

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
    .setSmallIcon(R.drawable.notification_icon)
    .setContentTitle(contentTitle)
    .setContentText(contentText)
    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
notificationManager.notify(notificationId, builder.build());
```

在这里，`channelId`是要将通知发送到的通知渠道的标识符。如果没有创建通知渠道，则通知将不会被发送。
Something went wrong. Please try again later.
    * */
}
