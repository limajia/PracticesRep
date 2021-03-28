package com.mlj.practicesrep.customview.cutomEditText;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.mlj.practicesrep.R;

//hint 顶部提示自定义
public class MaterialEditText extends AppCompatEditText {

    private static final String TAG = "MaterialEditText";

    private static final int TEXT_SIZE = 30;
    private static final int TEXT_MARGIN = 5;

    private static final int HORIZONTAL_OFFSET = 20;
    private static final int VERTICAL_OFFSET = 23;
    private static final int EXTRA_VERTICAL_OFFSET = 10;

    private boolean useFloatingLabel;

    private boolean floatingLabelShown;

    float floatingLabelFraction = 0f;

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    public MaterialEditText(@NonNull Context context) {
        this(context, null);
    }

    public MaterialEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 添加显示的距离  这些只是穿进去的值 和绘制流程没有关系
        setPadding(getPaddingLeft(), getPaddingTop() + TEXT_SIZE + TEXT_MARGIN,
                getPaddingRight(), getPaddingBottom());
        // 这里包括所以的属性
        //D/MaterialEditText: MaterialEditText: name =layout_gravity value =0x11
        //D/MaterialEditText: MaterialEditText: name =layout_width value =-1
        //D/MaterialEditText: MaterialEditText: name =layout_height value =-2
        //D/MaterialEditText: MaterialEditText: name =hint value =UserName
        //D/MaterialEditText: MaterialEditText: name =useFloatingLabel value =true
        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            Log.d(TAG, "MaterialEditText: name ="
                    + attrs.getAttributeName(i)
                    + "value =" + attrs.getAttributeValue(i));
        }
        // 过滤style中的attr
//        runtime_symbol_list 文件

        // int[] styleable MaterialEditText { 0x7f03033d }
        // int attr useFloatingLabel 0x7f03033d
        // int styleable MaterialEditText_useFloatingLabel 0
        // 这其实是一个过滤器
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        typedArray.recycle();
    }

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 只一个点的话为终点  动画自动取当前的的属性的get值，所以需要设置set方法，否则动画是没有的
    ObjectAnimator animator = ObjectAnimator.ofFloat(this,
            "floatingLabelFraction", 0f, 1f);

    {
        paint.setTextSize(TEXT_SIZE);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (floatingLabelShown && text.length() == 0) {
            floatingLabelShown = false;
            animator.reverse();
        } else if (!floatingLabelShown && text.length() != 0) {
            floatingLabelShown = true;
            animator.start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (floatingLabelFraction * 0xFF));
        int currentVertivalValue = (int) (VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction));
        canvas.drawText(getHint().toString(), HORIZONTAL_OFFSET, currentVertivalValue, paint);
    }
}
