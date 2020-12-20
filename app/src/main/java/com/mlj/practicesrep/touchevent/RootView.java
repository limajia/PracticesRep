package com.mlj.practicesrep.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RootView extends FrameLayout {
    private static final String TAG = "malijia";

    public RootView(@NonNull Context context) {
        super(context);
    }

    public RootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RootView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, " rootView--dispatchTouchEvent()" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "rootView---onInterceptTouchEvent()" + ev.getAction());
        if (ev.getAction() == 2) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "rootView---onTouchEvent()" + event.getAction());
        return super.onTouchEvent(event);
    }

}
