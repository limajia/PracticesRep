package com.mlj.practicesrep.beyondoutertest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class BeyondView extends LinearLayout {
    public BeyondView(Context context) {
        super(context);
    }

    public BeyondView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BeyondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BeyondView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(2000,2000);
    }
}
