package com.mlj.practicesrep.translucentTest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

// 这是根activity 测试之上的一个透明activity 根生命周期的变化
public class TranslucentActivity extends AppCompatActivity {

    private static final String TAG = "translucentTest111";
    private Button mJumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        setContentView(R.layout.activity_translucent);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    // home回来都会执行
    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    private void initView() {
        mJumpBtn = findViewById(R.id.jumpBtn);
        mJumpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TranslucentActivity.this, TranslucentTopActivity.class);
            startActivity(intent);
        });
    }
}