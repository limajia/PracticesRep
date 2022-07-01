package com.mlj.practicesrep.matrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class CustomVIew extends ImageView {
    public CustomVIew(Context context) {
        super(context);
    }

    public CustomVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        //res中设置的会走这里
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.YELLOW);
        Matrix matrix = new Matrix();
        //canvas.drawBitmap(); //matrix只能用于bitmap


        /**
         * 对图片进行旋转操作（顺时针方向进行）
         * 参数1：旋转角度
         * 参数2：中心点的x坐标
         * 参数3：中心点的y轴坐标
         */
//        matrix.setRotate(45,mBitmapMatrix.getWidth()/2,mBitmapMatrix.getHeight()/2);
//
//        //平移图片到控件中心方便观察
//        matrix.postTranslate(getMeasuredWidth()/2-mBitmapMatrix.getWidth()/2,getMeasuredHeight()/2-mBitmapMatrix.getHeight()/2);

    }
}
