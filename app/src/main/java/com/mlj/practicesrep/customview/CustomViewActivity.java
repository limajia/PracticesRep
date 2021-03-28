package com.mlj.practicesrep.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;
import com.mlj.practicesrep.customview.animate.CircleView;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        view.getResources()
//        getApplicationContext().getResources()
//        context().getResources()
//        Resources.getSystem().getDisplayMetrics() //只会得到系统参数的上下文

        CircleView circleView = findViewById(R.id.animate_circle_view);

        ObjectAnimator pingyiAnimator = ObjectAnimator.ofFloat(circleView,
                "translationX",
                200);
        pingyiAnimator.setStartDelay(500);
        pingyiAnimator.start();

        // 第二个是view中的属性方法 其中调用了invalidate 这里的float得和接收的方法参数类型相同
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(circleView,
                "radius",
                300);
        objectAnimator.setStartDelay(1000);
        objectAnimator.start(); //AnimatorSet 中时，可去掉

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(pingyiAnimator, objectAnimator); // 这里是Animator的数组
//        animatorSet.setStartDelay();
        animatorSet.start();

        PropertyValuesHolder m; //同一个动画过程中 设置多个动画属性

        //
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat();
        // 最常用的四种
        valueAnimator.setInterpolator(
//                new AccelerateDecelerateInterpolator()
//                new AccelerateInterpolator()
//                new DecelerateInterpolator()
                new LinearInterpolator() //匀速 最少用 不符合生活
        );

//        ObjectAnimator.ofObject(); // object的参数可以添加估值器
    }
}