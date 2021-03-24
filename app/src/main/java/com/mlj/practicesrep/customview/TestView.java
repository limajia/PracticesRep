package com.mlj.practicesrep.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author lijia19
 */
public class TestView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 配置样式
    Path path = new Path();

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.reset();
        path.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2,
                Path.Direction.CCW); //方向和 填充方式有关系
        path.setFillType(Path.FillType.EVEN_ODD);//循环圆环上升  镂空效果
        // Path.FillType.INVERSE_EVEN_ODD
        // 这些是设计需要做的事情  winding 复杂的情况 在具体情况具体分析 无需 学精通
        PathMeasure pathMeasure = new PathMeasure(path, false);// 是否自动闭合断开位置
//        pathMeasure.getLength();//长度
//        pathMeasure.getPosTan();//正切值
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);

        // DrawPath 不是每次画的时候 去创建 在size变化的时候 去生成
        canvas.drawPath(path, paint);
    }
}
