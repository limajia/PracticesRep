package com.mlj.practicesrep.customview.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

// meshView 中有个drawble处理工具 用来绘制不同的 或者已有的drawable
// 蜡烛图 定义两个横竖的蜡烛view 在view的ondraw中使用相同的Drawale之后，再绘制自己对象的细节
public class MeshView extends View {
    //    ColorDrawable colorDrawable = new ColorDrawable(Color.DKGRAY);
    MeshDrawable meshDrawable = new MeshDrawable();

    public MeshView(Context context) {
        super(context);
    }

    public MeshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        colorDrawable.setBounds(0, 0, getWidth(), getHeight());// 必须指定
//        colorDrawable.draw(canvas);
        meshDrawable.setBounds(0, 0, getWidth(), getHeight());
        meshDrawable.draw(canvas);
    }
}
