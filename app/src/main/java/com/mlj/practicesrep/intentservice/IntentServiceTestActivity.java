package com.mlj.practicesrep.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class IntentServiceTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service_test);

        startService(new Intent(IntentServiceTestActivity.this, CustomIntentService.class));
        startService(new Intent(IntentServiceTestActivity.this, CustomIntentService.class));
        startService(new Intent(IntentServiceTestActivity.this, CustomIntentService.class));

    }

// @param startId The most recent start identifier received in {@link
//     *                #onStart}.
//            * @return Returns true if the startId matches the last start request
//     * and the service will be stopped, else false.
//            *
//            * @see #stopSelf()
//     */
// public final boolean stopSelfResult(int startId) { 最后一个startId时候才会关闭
    //startId 在调用onStartCommand[多次调用]的时候从1 开始递进

    // 服务当然要注册
    //IntentService（异步的，会自动停止的服务）
    public static class CustomIntentService extends IntentService {

        int index = 0;

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         * <p>
         * name Used to name the worker thread, important only for debugging.
         */
        public CustomIntentService() {
            super("threadname");
        }

        // super 会调用 stopSelfResult
        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            // 这里是子线程  所有的任务在子线程中执行，且按照顺序执行 运行在子线程的looper中
            SystemClock.sleep(3000);
            System.out.println("dockerintent: " + (index++));
        }
    }
}