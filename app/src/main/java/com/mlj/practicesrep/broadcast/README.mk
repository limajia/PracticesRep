接收广播不需要权限，只要继承BroadCastReceiver即可。不能操作系统广播发出者。
动态注册的网络状态广播，没添加任何权限，进入页面注册就执行一次receiver广播。

若自定义发送广播
1.
 <receiver android:name=".PermissionRecevicer"
            android:permission="com.example.broadcast.permission">///
            <intent-filter>
                <action android:name="com.example.permissionbroadcastreceiver.message" />
            </intent-filter>
</receiver>

发送方需要给与权限
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.permissionbroadcast">

    <uses-permission android:name="com.example.broadcast.permission" />///
    <application
      ......


问题：
静态接收者-接收不到系统的网络变化广播？？？