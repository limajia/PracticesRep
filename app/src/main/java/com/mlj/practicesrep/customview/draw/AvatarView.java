package com.mlj.practicesrep.customview.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.mlj.practicesrep.R;

//Xfermode
public class AvatarView extends View {

    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_PADDING = 20;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    // 离屏区域
    RectF rectF = new RectF(IMAGE_PADDING, IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH);

    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 先画的为destination
        int count = canvas.saveLayer(rectF, null);//第几个离屏 能多小就多小
        canvas.drawOval(IMAGE_PADDING, IMAGE_PADDING,
                IMAGE_PADDING + IMAGE_WIDTH,
                IMAGE_PADDING + IMAGE_WIDTH,
                paint);
        //PorterDuffXfermode 其他过时 这是两个人的名字
        //offScreenBuffer离屏渲染 一个作用是去除了View的白色背景 离屏背景为透明的
        // 否则画的时候 画的圆在view的白色的底上，还有其他的内容，为一体的。不能实现Xfermode的样式。
        paint.setXfermode(xfermode);
        canvas.drawBitmap(getAvatar(IMAGE_WIDTH), IMAGE_PADDING, IMAGE_PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(count);//
    }

    public Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 解码两次
        BitmapFactory.decodeResource(getResources(), R.mipmap.abcd, options);
        options.inJustDecodeBounds = false;
        // in 决定了目的的大小  out是读取的大小
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.mipmap.abcd, options);
    }


}
