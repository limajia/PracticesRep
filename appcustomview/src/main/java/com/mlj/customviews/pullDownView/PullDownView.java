package com.mlj.customviews.pullDownView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

public class PullDownView extends FrameLayout {

    private static final String TAG = "PullDownView";
    private ViewDragHelper mViewDragHelper;
    private View mChildView;
    private boolean mFirstLayout;
    private int mOriginTop;
    private View currentView;

    public PullDownView(@NonNull Context context) {
        super(context);
        currentView = this;
    }

    public PullDownView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        currentView = this;
    }

    public PullDownView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        currentView = this;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        float density = getContext().getResources().getDisplayMetrics().density;
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mViewDragHelper.setMinVelocity(density * 400);
        //
        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                // 移除View时候，恢复到之前默认的状态
            }
        });
        // 假如只有一个孩子View
        mChildView = getChildAt(0);
        mChildView.animate().alpha(0.5f).setDuration(3000).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                mChildView.setLayoutParams(mChildView.getLayoutParams());
//                if (currentView.getLayoutParams() != null) {
//                    currentView.getLayoutParams().height += 100;
//                    currentView.setLayoutParams(currentView.getLayoutParams());
//                }

            }
        }).start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout() called with: changed = [" + changed + "], left = [" + left + "], top = [" + top + "], right = [" + right + "], bottom = [" + bottom + "]");
        super.onLayout(changed, left, top, right, bottom);
        if (mFirstLayout) {
            mOriginTop = mChildView.getTop();
            mFirstLayout = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw() called with: canvas = [" + canvas + "]");
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == mChildView;
        }

        /**
         * @param child
         * @param top   目标位置 相对于Parent
         * @param dy    每两次移动的距离差
         * @return
         */
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            Log.d(TAG, "clampViewPositionVertical() called with: child = [" + child + "], top = [" + top + "], dy = [" + dy + "]");
            return top;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            mViewDragHelper.settleCapturedViewAt(mChildView.getLeft(), mOriginTop);
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        // 配合mViewDragHelper.settleCapturedViewAt()方法 // 不写相当于release中的操作不执行
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }
}
