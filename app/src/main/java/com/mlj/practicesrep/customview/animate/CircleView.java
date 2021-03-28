package com.mlj.practicesrep.customview.animate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate(); // 只是标识为失效 不是refresh  界面刷新的时候 不会自动重绘 除非标识为失效
    }

    private float radius = 100;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }
}
