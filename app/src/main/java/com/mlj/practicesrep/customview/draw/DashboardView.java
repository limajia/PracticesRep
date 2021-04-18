package com.mlj.practicesrep.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

// 仪表盘
public class DashboardView extends View {
    private static final String TAG = "DashboardView";

    private static final int RADIUS = 80;
    private static final int ZHIZHEN_LENGTH = 60;

    private static final int OPEN_ANGLE = 120;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // pathdashpatheffect //用path虚线（小蝴蝶）画到path effect(指定路径)上
    //小刻度   dash line // 虚线
    private static final int DASH_WIDTH = 2;
    private static final int DASH_LENGTH = 10;

    Path path = new Path();
    Path dash = new Path();
    private PathDashPathEffect pathDashPathEffect;

    public DashboardView(Context context) {
        super(context);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE); //只要边框 不填充
        dash.addRect(0, 0, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.reset();
        path.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                // startAngle    // userCenter 是不是连接到中心点 形成一个封闭的图形
                90 + OPEN_ANGLE / 2, 360 - OPEN_ANGLE);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        // 平均分成21个刻度 20个间隔 要花完成吧21间隔的刻度宽度划出了
        // 每一次画 都是|____|____ .....|
        float dashLength = (pathMeasure.getLength() - DASH_WIDTH) / 20f; //20里面包含dash的宽度 少华一个宽度 减少一个宽度
        pathDashPathEffect = new PathDashPathEffect(dash, dashLength, 0, PathDashPathEffect.Style.ROTATE);

    }

    public DashboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //todo 不要在draw中创建对象
        super.onDraw(canvas);
        //1.画弧线 把Arc 换做path 这个可以测量长度 然后均分每一段
        canvas.drawPath(path, paint);

        // 2.画dash 虚线 刻度
        // PathDashPathEffect   advance =起始offset  phase【feizi】间隔 阶段
        // fix 但是android bug 把下面的两个参数弄反了 交换一下
        // 这个方法是用一下的特效去画弧线，之前话的那个弧线没了
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);

        paint.setPathEffect(null);

        // 3.画指针 通过sin cos 直接计算就好 包含了正负值
        double a = Math.toRadians(90 + OPEN_ANGLE / 2 + (360 - OPEN_ANGLE) / 20 * 5);
        canvas.drawLine(getWidth() / 2,
                getHeight() / 2,
                getWidth() / 2 + (float) (ZHIZHEN_LENGTH * Math.cos(a)),
                getHeight() / 2 + (float) (ZHIZHEN_LENGTH * Math.sin(a)),
                paint);
    }

}
