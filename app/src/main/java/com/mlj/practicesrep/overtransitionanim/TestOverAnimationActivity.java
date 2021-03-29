package com.mlj.practicesrep.overtransitionanim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class TestOverAnimationActivity extends AppCompatActivity {

    //1、必须在 StartActivity()  或 finish() 之后立即调用。
    //2、而且在 2.1 以上版本有效
    //3、手机设置-显示-动画，要开启状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_over_animation);
    }
}