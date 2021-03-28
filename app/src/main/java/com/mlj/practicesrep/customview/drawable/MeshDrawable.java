package com.mlj.practicesrep.customview.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// mesh 网  一般的 是没用的 默认的已经提供的够用了
public class MeshDrawable extends Drawable {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int LINE_SPACE = 50;

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //有个内置的边界范围对象 bounds
        Rect bounds = getBounds();
        int left = bounds.left;
        while (left <= bounds.right) {
            canvas.drawLine(left, bounds.top, left, bounds.bottom, paint);
            left += LINE_SPACE;
        }
        int top = bounds.top;
        while (top <= bounds.bottom) {
            canvas.drawLine(bounds.left, top, bounds.right, top, paint);
            top += LINE_SPACE;
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return paint.getAlpha();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() { // ou pai se ti 不透明度 opaque
        if (getAlpha() == 0) {
            return PixelFormat.TRANSPARENT;// 透明
        } else if (getAlpha() == 0xff) {
            return PixelFormat.OPAQUE; // 不透明
        } else {
            return PixelFormat.TRANSLUCENT; //半透明
        }
    }
}
