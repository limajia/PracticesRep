package com.mlj.practicesrep.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

// 仪表盘
public class PieView extends View {

    private static final int RADIUS = 80;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float[] angels = new float[]{60, 90, 150, 60};
    int[] colors = new int[]{Color.parseColor("#ff0000"), Color.parseColor("#00ff00"),
            Color.parseColor("#0000ff"), Color.parseColor("#ffff00")};


    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
//        paint.setStrokeWidth(3f);
//        paint.setStyle(Paint.Style.STROKE); //只要边框 不填充
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //todo 不要在draw中创建对象
        super.onDraw(canvas);
        float startangle = 0;
        for (int i = 0; i < angels.length; i++) {
            float angel = angels[i];
            paint.setColor(colors[i]);
            if (i == 0) {
                canvas.save();
                // 移动sweep角度的中心 向外长度 cos sin 计算
                canvas.translate(10f * (float) Math.cos(Math.toRadians(angel / 2)), 10f * (float) Math.sin(Math.toRadians(angel / 2)));
            }
            canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                    getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                    // startAngle    // userCenter 是不是连接到中心点 形成一个封闭的图形
                    startangle, angel, true, paint);
            startangle += angel;
            if (i == 0) {
                canvas.restore();
            }
        }
    }

}
