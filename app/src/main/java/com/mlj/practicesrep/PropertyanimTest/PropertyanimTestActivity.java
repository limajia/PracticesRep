package com.mlj.practicesrep.PropertyanimTest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class PropertyanimTestActivity extends AppCompatActivity {

    private Button mAnimview;
    private Button mPingyix1;
    private Button mPingyix2;
    private Button mPingyix3;
    ObjectAnimator animator;
    ObjectAnimator proressanimator;
    private ProgressBar mProgressHorizontal;
    private Button mProgres1;
    private Button mProgres2;
    private Button mProgres3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyanim_test);
        initView();
    }

    //1.多个同一属性的属性动画执行，后面的会替换之前的
    //2.同一个ObjectAnimator可以多次start，都从属性额的开始到结束执行,是可以使用的
    private void initView() {
        mAnimview = findViewById(R.id.animview);
        animator = ObjectAnimator.ofFloat(mAnimview, "translationY", 0, 200);
        animator.setDuration(2000);
        mPingyix1 = findViewById(R.id.pingyix1);
        mPingyix1.setOnClickListener(v -> {
            animator.start();
        });
        mPingyix2 = findViewById(R.id.pingyix2);
        mPingyix2.setOnClickListener(v -> {
            animator.start();
        });
        mProgressHorizontal = findViewById(R.id.progress_horizontal);
        proressanimator = ObjectAnimator.ofInt(mProgressHorizontal, "progress", 0, 100);
        proressanimator.setDuration(3000);
        mProgres1 = findViewById(R.id.progres1);
        mProgres1.setOnClickListener(v -> {
            proressanimator.start();
        });
        mProgres2 = findViewById(R.id.progres2);
        mProgres2.setOnClickListener(v -> {
            proressanimator.start();
        });
        mProgres3 = findViewById(R.id.progres3);
        mProgres3.setOnClickListener(v -> {
            proressanimator.cancel();
        });
    }
}