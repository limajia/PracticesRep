package com.mlj.practicesrep.lottietest;

import android.content.Context;
import android.util.AttributeSet;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;

public class MyAnimationView extends LottieAnimationView {

    public MyAnimationView(Context context) {
        super(context);
        initView();
    }

    public MyAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void initView() {
        setRepeatMode(LottieDrawable.RESTART);
        setRepeatCount(LottieDrawable.INFINITE);
        setImageAssetsFolder("images/");
        LottieCompositionFactory.fromAsset(getContext(), "data.json").addListener(this::setComposition);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        playAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnimation();
    }
}
