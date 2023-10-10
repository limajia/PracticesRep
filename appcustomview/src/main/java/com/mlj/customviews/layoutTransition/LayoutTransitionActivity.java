package com.mlj.customviews.layoutTransition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


import com.mlj.customviews.R;

import java.util.Random;

public class LayoutTransitionActivity extends AppCompatActivity {

    private RelativeLayout mViewContainer;
    private int mViewCount = 0;
    private boolean isTransitionFinish = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        mViewContainer = findViewById(R.id.view_container);
        // 布局动画
        LayoutTransition mLayoutTransition = new LayoutTransition();
        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, getAppearingAnimation());
        mLayoutTransition.setDuration(LayoutTransition.APPEARING, 200);
        mLayoutTransition.setStartDelay(LayoutTransition.APPEARING, 0);//源码中带有默认300毫秒的延时，需要移除，不然view添加效果不好！！

        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, getDisappearingAnimation());
        mLayoutTransition.setDuration(LayoutTransition.DISAPPEARING, 200);

//        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, getAppearingChangeAnimation());
//        mLayoutTransition.setDuration(200);
//
//        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, getDisappearingChangeAnimation());
//        mLayoutTransition.setDuration(200);

//        mLayoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
//        mLayoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);//源码中带有默认300毫秒的延时，需要移除，不然view添加效果不好！！
        mLayoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i("zyq", "LayoutTransition:startTransition");
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i("zyq", "LayoutTransition:endTransition");
//                isTransitionFinish = true;
            }
        });
        mViewContainer.setLayoutTransition(mLayoutTransition);
    }

    private Animator getAppearingAnimation() {
        AnimatorSet mSet = new AnimatorSet();
        mSet.playTogether(ObjectAnimator.ofFloat(null, "ScaleX", 2.0f, 1.0f),
                ObjectAnimator.ofFloat(null, "ScaleY", 2.0f, 1.0f),
                ObjectAnimator.ofFloat(null, "Alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(null, "translationX", 400, 0));
        return mSet;
    }

    private Animator getDisappearingAnimation() {
        AnimatorSet mSet = new AnimatorSet();
        mSet.playTogether(ObjectAnimator.ofFloat(null, "ScaleX", 1.0f, 0f),
                ObjectAnimator.ofFloat(null, "ScaleY", 1.0f, 0f),
                ObjectAnimator.ofFloat(null, "Alpha", 1.0f, 0.0f),
                ObjectAnimator.ofFloat(null, "translationX", 0, 400));
        return mSet;
    }

    private Animator getDisappearingChangeAnimation() {
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0f, 1.0f);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotation", 0, 0, 0);
        return ObjectAnimator.ofPropertyValuesHolder((Object) null, pvhLeft, pvhTop, pvhRight, pvhBottom, scaleX, scaleY, rotate);
    }

    private Animator getAppearingChangeAnimation() {
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 3f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 3f, 1.0f);
        return ObjectAnimator.ofPropertyValuesHolder((Object) null, pvhLeft, pvhTop, pvhRight, pvhBottom, scaleX, scaleY);
    }

    public void addViewToParent(View view) {
//        if (isTransitionFinish) {
//            isTransitionFinish = false;
            View view1 = LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_1, mViewContainer, false);
//          view1.setBackgroundColor(Color.parseColor(getRandColor()));
            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(150, 120);
            mViewContainer.addView(view1, layoutParams1);
            view1.setBackgroundColor(Color.parseColor(getRandColor()));
            mViewCount++;
            int childCount = mViewContainer.getChildCount();
            for (int i = childCount - 2; i >= 0; i--) {
                View childAt = mViewContainer.getChildAt(i);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) childAt.getLayoutParams();
                layoutParams.leftMargin = (childCount - 1 - i) * 150;
                childAt.setLayoutParams(layoutParams);
            }
//        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                int childCount = mViewContainer.getChildCount();
                if (childCount > 3) {
                    removeViewFromParentEnd();
                }
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },0);

    }

    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }

    public void removeViewFromParent(View view) {
//        if (isTransitionFinish) {
            if (mViewCount >= 1) {
//                isTransitionFinish = false;
                mViewContainer.removeViewAt(mViewCount - 1);
                mViewCount--;
                int childCount = mViewContainer.getChildCount();
                for (int i = childCount - 1; i >= 0; i--) {
                    View childAt = mViewContainer.getChildAt(i);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) childAt.getLayoutParams();
                    layoutParams.leftMargin -= 150;
                    childAt.setLayoutParams(layoutParams);
                }
            }
//        }
    }
    public void removeViewFromParentEnd() {
//        if (isTransitionFinish) {
            if (mViewCount >= 1) {
                isTransitionFinish = false;
                mViewContainer.removeViewAt(0);
                mViewCount--;
            }
        }
//    }
}