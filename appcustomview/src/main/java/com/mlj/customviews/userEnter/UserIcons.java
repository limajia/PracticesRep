package com.mlj.customviews.userEnter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class UserIcons extends ViewGroup {

    protected float pileWidth = 0; //重叠宽度

    public UserIcons(Context context) {
        this(context, null, 0);
    }

    public UserIcons(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserIcons(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pileWidth = dp2px(10);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        //AT_MOST
        int width = 0;
        int height = 0;
        int rawWidth = 100;//总宽度

        int rowIndex = 0;//列位置
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            //调用measureChildWithMargins 而不是measureChild
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            rawWidth += childWidth;
            if (rowIndex > 0) {
                rawWidth -= pileWidth;
            }

            if (i == count - 1) {
                width = Math.max(rawWidth, width);
            }
            rowIndex++;
        }

        setMeasuredDimension(
                widthSpecMode == MeasureSpec.EXACTLY ? widthSpecSize : width + getPaddingLeft() + getPaddingRight(),
                heightSpecMode == MeasureSpec.EXACTLY ? heightSpecSize : height + getPaddingTop() + getPaddingBottom()
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();
        int columnIndex = 0;
        View childView;
        for (int count = getChildCount(), w = count - 1; w >= 0; w--) {
            if (w >= count - 4) {
                childView = getChildAt(w);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int occupyWidth = lp.leftMargin + childView.getMeasuredWidth() + lp.rightMargin;

                int left = parentPaddingLeft + lp.leftMargin;
                int top = parentPaddingTop + lp.topMargin;
                int right = parentPaddingLeft + lp.leftMargin + childView.getMeasuredWidth();
                int bottom = parentPaddingTop + lp.topMargin + childView.getMeasuredHeight();
                childView.layout(left, top, right, bottom);
                //
                if (count > 3) {
                    if (w == count - 1) {
                        translateAnimationNew(childView, lp.leftMargin + occupyWidth, -occupyWidth);
                    } else if (w >= count - 3) {
                        translateAnimation(childView, lp.leftMargin, -occupyWidth);
                    } else if (w == count - 4) {
                        alphaAnimation(childView);
                    }
                }
                // 横向偏移
                parentPaddingLeft += occupyWidth;
                if (columnIndex != 0) {
                    parentPaddingLeft -= pileWidth;
                }
                columnIndex++;
            }
        }
    }


    public float dp2px(float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    private void translateAnimation(View view, float fromX, float toX) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, fromX,
                Animation.ABSOLUTE, toX, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0f);
        translateAnimation.setDuration(800);
        view.startAnimation(translateAnimation);
    }

    private void translateAnimationNew(View view, float fromX, float toX) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, fromX + 20,
                Animation.ABSOLUTE, toX, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0f);
        translateAnimation.setDuration(800);
        view.startAnimation(translateAnimation);
    }

    private void alphaAnimation(final View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(800);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                removeView(getChildAt(0));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
