package com.mlj.practicesrep;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.broadcast.BroadCastActivity;
import com.mlj.practicesrep.customdialog.CustomDialogActivity;

public class MainActivity extends AppCompatActivity {

    private Button mAnimationDrawTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.
        View broadCastTestBtn = findViewById(R.id.broadCastTestBtn);
        broadCastTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BroadCastActivity.class);
            startActivity(intent);
        });
        //2.
        View customDialogTestBtn = findViewById(R.id.customDialogTestBtn);
        customDialogTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomDialogActivity.class);
            startActivity(intent);
        });
        //3.
//        75-13975/com.mlj.practicesrep D/docker: 288
//        2020-11-11 11:13:58.146 13975-13975/com.mlj.practicesrep D/docker----: 288
        mAnimationDrawTestBtn = findViewById(R.id.animationDrawTestBtn);
        mAnimationDrawTestBtn.postDelayed(() -> {
            Log.d("docker", mAnimationDrawTestBtn.getTop() + "");
            mAnimationDrawTestBtn.setTranslationY(400);
            Log.d("docker----", mAnimationDrawTestBtn.getTop() + "");
        }, 3000);

        // 看来是没有改原来View的属性，单独维护TranslationY值，在绘制的时候，添加这个offset。点击的位置矩阵也跟着变化了。
        // view动画 也是这样的原理，但是没有修改点击的位置矩阵
        // property动画，则同时修改了位置矩阵
    }
}