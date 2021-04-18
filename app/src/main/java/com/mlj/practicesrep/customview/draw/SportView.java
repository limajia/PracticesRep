package com.mlj.practicesrep.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.mlj.practicesrep.R;

public class SportView extends View {
    private int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");

    private int RING_WIDTH = 20;
    private int RADIUS = 150;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Rect bounds = new Rect();
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportView(Context context) {
        super(context);
    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制环
        paint.setStyle(Paint.Style.STROKE); // 这是描边
        paint.setColor(CIRCLE_COLOR);
        paint.setStrokeWidth(RING_WIDTH);
        // radis 处向外开始画
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, RADIUS, paint);

        // 绘制进度条
        paint.setColor(HIGHLIGHT_COLOR);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS,
                getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS,
                -90f, 225f, false, paint);

        // 1.绘制文字 静态文字 确定的文字
        // 需要设置text属性的paint
        paint.setStyle(Paint.Style.FILL); // 去除描边改为填充
        paint.setTextSize(80);
        paint.setTypeface(ResourcesCompat.getFont(getContext(), R.font.font));//设置字体
        // paint.setFakeBoldText(true);// 假粗 不是真的字体 描粗了
        paint.setTextAlign(Paint.Align.CENTER);//在目标点 开始绘制的位置[注意] 左右开始绘制居中  //只是垂直的位置 不太好绘制
        paint.getTextBounds("abab", 0, "abab".length(), bounds); //baseline之上 和alin有关系
        canvas.drawText("abcb", getWidth() / 2f, getHeight() / 2f - (bounds.top + bounds.bottom) / 2, paint);
        // 上面这适合--静态--文字，abcd apcp 会给人上下跳动的感觉
        // baseLine 一个稳的基线  【中心位置= (上+下) /2】 bounds[3,-59,189,1] 应该是是view坐标系方向，原点是baseLine的左下角


        // 2.绘制动态文字时候
        // ascent【上升】 核心文字的顶部
        // descent【下降】 核心文字的底部
        // top 最高极限
        // bottom 最低极限
        // 还有一个baseLine
        paint.getFontMetrics(fontMetrics);
        canvas.drawText("apcp", getWidth() / 2f, getHeight() / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f, paint);

        // 3.贴边效果
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getFontMetrics(fontMetrics); // 获取字体的一些固定属性
        paint.getTextBounds("abab", 0, "abab".length(), bounds); //baseline之上 和alin有关系
        canvas.drawText("abab", -bounds.left, -bounds.top, paint); //建议top 不用ascent 万一特殊的高字 会截断
        // bounds.left 这个距离是文字的默认的文字内边距
    }
}