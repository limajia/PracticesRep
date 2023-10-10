package com.docker.handwrite.handeventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.docker.handwrite.R;
import com.docker.handwrite.handeventbus.core.DoEventBus;
import com.docker.handwrite.handeventbus.core.DoSubscribe;
import com.docker.handwrite.handeventbus.core.DoThreadMode;

public class EventBusActivity extends AppCompatActivity {
    View rootView;
    private Runnable abc = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            // TODO: 2021/1/5 注意这里的类型
            DoEventBus.getDefault().post(i++); // post obj一个int 则转化为了Integer类型
            rootView.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        DoEventBus.getDefault().register(this);
        rootView = findViewById(Window.ID_ANDROID_CONTENT);
        rootView.post(abc);
    }


    @DoSubscribe(threadMode = DoThreadMode.MAIN)
    public void fun(Integer abc) { // int 直接通过Class 还是int  // 这里注意是public 方法 反射才能调用到
        // TODO: 2021/1/5 注意如果这里类型是int 会类型匹配失败
        Toast.makeText(EventBusActivity.this, abc + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        DoEventBus.getDefault().unRegister(this);
        super.onDestroy();
    }
}