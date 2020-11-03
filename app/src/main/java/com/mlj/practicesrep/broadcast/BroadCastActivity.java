package com.mlj.practicesrep.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.mlj.practicesrep.R;

public class BroadCastActivity extends AppCompatActivity {

    private static final String TAG = "BroadCastActivity";
    private DynamicReceiver mDynamicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        //1.------------动态注册网络变化---
        mDynamicReceiver = new DynamicReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mDynamicReceiver, intentFilter);
    }


    public static class DynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 动态注册 每次进入页面都会有回调
            // [action=android.net.conn.CONNECTIVITY_CHANGE]
            Log.d(TAG, "DynamicReceiver: onReceive() called with: context = [" + context + "], intent = [" + intent + "]," + "[action=" + action + "]");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDynamicReceiver);
    }
}