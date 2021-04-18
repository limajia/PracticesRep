package com.mlj.practicesrep.customview.layout;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class SquareImageView extends androidx.appcompat.widget.AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec); //先自己测量一遍 可以拿到下面的两个值
        int measuredWidth = getMeasuredWidth();//测量的宽度 300dp
        int measuredHeight = getMeasuredHeight();//测量的高度 200dp
        int min = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(min, min); //保存一下测量的数据
        //getMeasuredWidth 期望值 后面可能会失效 测量过程中使用
        //getWidth 实际值 布局后的右-左
    }


    //    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//       super.onLayout(changed, left, top, left + 100, top + 100);
//    }

    // layout 是当前View保存位置的 onLayout是布局子View的 ！！！
    // 只修改layout的话，父view不知道，子view变化了，会产生异常的像是情况 宽高还是那样的
    /*
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, l + 300, t + 300);
    }
    */

}
