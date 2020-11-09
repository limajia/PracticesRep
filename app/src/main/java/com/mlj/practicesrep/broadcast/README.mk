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
2.权限的重复请求 会导致上一次的请求cancel，结果列表为null


        getWindow().getDecorView().postDelayed(runnable, 3000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
        // 会走取消
            Log.d("", "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = [" + permissions + "], grantResults = [" + grantResults + "]");
        }
    }

问题：
静态接收者-接收不到系统的网络变化广播？？？