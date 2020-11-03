package com.mlj.practicesrep.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StaticReceiver extends BroadcastReceiver {

    // ????? 静态广播接收不到
    private static final String TAG = "StaticReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "StaticReceiver:___onReceive: ");
    }
}