package com.mlj.practicesrep.configurationtest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class AnotherConfigurationActivity extends AppCompatActivity {
    
    private static final String TAG = "docker2222";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_configuration);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    // 当前页面关闭后 上个页面执行
    //D/docker11111: onConfigurationChanged:  也是在页面出现之前
    //D/docker11111: onRestart:
    //D/docker11111: onStart:
    //D/docker11111: onResume:

    //如果又回到了1111页面之前的状态，从2222页面返回之后，不会调用1111的onConfigurationChanged方法
}