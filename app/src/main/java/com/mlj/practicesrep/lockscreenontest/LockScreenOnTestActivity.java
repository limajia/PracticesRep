package com.mlj.practicesrep.lockscreenontest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;

import com.mlj.practicesrep.App;
import com.mlj.practicesrep.R;

/**
 * @author pua
 */
public class LockScreenOnTestActivity extends AppCompatActivity {

    Handler handler = new Handler(Looper.getMainLooper());

    private final BroadcastReceiver receiver = new BroadcastReceiver() { //和((PowerManager) (App.getApp().getSystemService(Context.POWER_SERVICE))).isInteractive()方法是一样的
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Intent.ACTION_SCREEN_OFF:
                    System.out.println("docker: BroadcastReceiver ACTION_SCREEN_OFF"); //忽略锁屏的
                    break;
                case Intent.ACTION_SCREEN_ON:
                    System.out.println("docker: BroadcastReceiver ACTION_SCREEN_ON"); //忽略锁屏的
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen_on_test);
        // handler 每隔5秒执行一次
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("docker: 执行 5s 消息======================");
                System.out.println("docker:(app.getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive: " + ((PowerManager) (App.getApp().getSystemService(Context.POWER_SERVICE))).isInteractive()); //忽略锁屏的
                KeyguardManager keyguardManager = (KeyguardManager) App.getApp().getSystemService(Context.KEYGUARD_SERVICE);
                boolean inKeyguardLock = keyguardManager.isKeyguardLocked();
                System.out.println("docker:(KeyguardManager) App.getApp().getSystemService(Context.KEYGUARD_SERVICE).inKeyguardRestrictedInputMode(): " + inKeyguardLock);//是否是错屏 和亮灭米有关系
                handler.postDelayed(this, 5000);
            }
        }, 5000);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.setPriority(1000);
        App.getApp().registerReceiver(receiver, intentFilter);
    }
}