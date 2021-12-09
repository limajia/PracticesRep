package com.mlj.practicesrep.floatbtntest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mlj.practicesrep.R;

public class FloatBtnTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_btn_test);
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                new SqWindowManagerFloatView(FloatBtnTestActivity.this, R.mipmap.ic_launcher).show();
            }
        }, 1000);

    }

    public void jumpto(View view) {
        FloatBtnTestActivity.this.startActivity(new Intent(FloatBtnTestActivity.this, AnotherTestActivity.class));
    }
}