package com.mlj.practicesrep.floatbtntest;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

// subWindow展示
// https://yuanfentiank789.github.io/2017/04/24/windowtype/
// https://juejin.cn/post/6911582503212384263#heading-4
//https://blog.csdn.net/weixin_41975165/article/details/79877562?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
//https://blog.csdn.net/cuiran/article/details/50184511
public class SqWindowManagerFloatView extends DragViewLayout {

    public SqWindowManagerFloatView(final Context context, final int floatImgId) {
        super(context);
        setClickable(true);
        final ImageView floatView = new ImageView(context);
        floatView.setImageResource(floatImgId);
        floatView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了悬浮球", Toast.LENGTH_SHORT).show();
            }
        });
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(floatView, params);
    }
}
