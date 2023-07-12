package com.mlj.practicesrep.PaintFunctionsTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathPaint extends View {
    public PathPaint(Context context) {
        super(context);
    }

    public PathPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Paint paint = new Paint();


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        float left = 100;     // 矩形的左边界
        float top = 200;      // 矩形的上边界
        float right = 400;    // 矩形的右边界
        float bottom = 600;   // 矩形的下边界
        float radius = 100;    // 圆角的半径

        canvas.drawRect(new Rect((int) left, (int) top, (int) right, (int) bottom), paint);

        paint.setColor(Color.RED);
        Path path = new Path();

        path.addRoundRect(left, top, right, bottom, radius, radius, Path.Direction.CW);
        Matrix matrix = new Matrix();
        matrix.setRotate(45, (left + right) / 2, (top + bottom) / 2);
        path.transform(matrix);
        canvas.drawPath(path, paint);




        //在Android中，Rect类表示一个矩形区域，其边界由左上角和右下角的坐标确定。然而，Rect类本身并不支持斜角旋转。要表示一个45度倾斜的矩形，你可以使用Path类来绘制斜角矩形的路径。

    }
}
