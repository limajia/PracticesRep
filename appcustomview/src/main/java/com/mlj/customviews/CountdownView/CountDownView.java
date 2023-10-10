package com.mlj.customviews.CountdownView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.mlj.customviews.R;


public class CountDownView extends View {

    private String mExampleString;
    private int mExampleColor = Color.RED;
    private float mExampleDimension = 0;
    private Drawable mExampleDrawable;

    private float mTextWidth;
    private float mTextHeight;

    private String[] mStrings = {"3", "2", "1", "ACTION"};
    private Paint mLinePaint;

    private int mLineColor = 0xffd8d8d7;
    private int mTextColor = 0xfffefefc;
    private int mShadowColor = 0xff595958;
    private Paint mShadowPaint;
    private TextPaint mMyTextPaint;
    private SoundPool mSoundPool;
    private int mVolumeId;
    private Point mCenterPoint;
    private ValueAnimator mValueAnimator;
    private int mAnimatedValue;

    public CountDownView(Context context) {
        super(context);
        init(null, 0);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CountDownView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.CountDownView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.CountDownView_exampleColor,
                mExampleColor);
        //
        mExampleDimension = a.getDimension(
                R.styleable.CountDownView_exampleDimension,
                mExampleDimension);

        //
        if (a.hasValue(R.styleable.CountDownView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.CountDownView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        //line
        mLinePaint = new Paint();
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(dp2px(2));

        // set up a lint for draw shadow
        mShadowPaint = new Paint();
        mShadowPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//flag 或者boolean
        mShadowPaint.setColor(mShadowColor);
        mShadowPaint.setStyle(Paint.Style.FILL);

        // set up a textPainf to draw text
        mMyTextPaint = new TextPaint();
        mMyTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mMyTextPaint.setTextAlign(Paint.Align.CENTER);
        mMyTextPaint.setColor(mTextColor);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                performStart();
            }
        });

        mSoundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 100);
        mVolumeId = mSoundPool.load(getContext(), R.raw.sound123action, 1);
        // Update TextPaint and text measurements from attributes
        // invalidateTextPaintAndMeasurements();
    }

    private int dp2px(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5);
    }

    public void performStart() {
        // change 20 times per seconds
        mValueAnimator = ValueAnimator.ofInt(0, mStrings.length * 20 - 1).setDuration(mStrings.length * 1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = (Integer) animation.getAnimatedValue();
                postInvalidate(); // 这里出发重绘
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                playSound();

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        mValueAnimator.start();
    }

    private void playSound() {
        AudioManager mgr = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play(mVolumeId, streamVolume, streamVolume, 1, 0, 1f);
    }

   /* private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        // 文字宽度
        mTextWidth = mTextPaint.measureText(mExampleString);
        // 文字高度
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
        postInvalidate();
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 不要创建对象 防止内存抖动
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        mCenterPoint = new Point(paddingLeft + contentWidth / 2, paddingTop + contentHeight / 2);

        //draw lines
        canvas.drawLine(paddingLeft, mCenterPoint.y, getWidth() - paddingRight, mCenterPoint.y, mLinePaint);
        canvas.drawLine(mCenterPoint.x, paddingTop + 120, mCenterPoint.x, getHeight() - paddingBottom - 120, mLinePaint);

        // draw circles
        float radiusLarge = Math.min(getWidth(), getHeight()) * 0.3f;
        RectF largetRectf = new RectF(mCenterPoint.x - radiusLarge, mCenterPoint.y - radiusLarge, mCenterPoint.x + radiusLarge, mCenterPoint.y + radiusLarge);
        mLinePaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(largetRectf, 0, 360, false, mLinePaint);
        mLinePaint.setStyle(Paint.Style.STROKE);
        float radiusSmall = radiusLarge * 0.9f;
        RectF smallRectf = new RectF(mCenterPoint.x - radiusSmall, mCenterPoint.y - radiusSmall, mCenterPoint.x + radiusSmall, mCenterPoint.y + radiusSmall);
        canvas.drawArc(smallRectf, 0, 360, false, mLinePaint); // false 是否实心的扇形
        // draw arc
        if (mAnimatedValue <= (mStrings.length - 1) * 20 && mAnimatedValue != 0) {
            int sweepAngle = (mAnimatedValue * 18) % 360;
            if (sweepAngle == 0) {
                sweepAngle = 360;
            }
            canvas.drawArc(largetRectf, -90, sweepAngle, true, mShadowPaint);
        }

        //draw text
        drawCountDownText(canvas);

        // Draw the text.
//        canvas.drawText(mExampleString,
//                paddingLeft + (contentWidth - mTextWidth) / 2,
//                paddingTop + (contentHeight + mTextHeight) / 2,
//                mTextPaint);
//
//        // Draw the example drawable on top of the text.
//        if (mExampleDrawable != null) {
//            mExampleDrawable.setBounds(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight);
//            mExampleDrawable.draw(canvas);
//        }
    }

    private void drawCountDownText(Canvas canvas) {
        int index = mAnimatedValue / 20;
        String text = mStrings[index];

        int textSize = dp2px(110);
        float width = mMyTextPaint.measureText(text);

        if (index == mStrings.length - 1) {
            textSize = (int) (width / (mStrings[mStrings.length - 1].length()));
            textSize = dp2px(75);
        }
        mMyTextPaint.setTextSize(textSize);
        mMyTextPaint.setStrokeWidth(dp2px(4));
        mMyTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
//        mMyTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        Paint.FontMetrics fontMetrics = mMyTextPaint.getFontMetrics();
        float textHight = fontMetrics.bottom;
        canvas.drawText(text, mCenterPoint.x, mCenterPoint.y + textHight, mMyTextPaint);
    }


    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
//        invalidateTextPaintAndMeasurements();
    }


    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
//        invalidateTextPaintAndMeasurements();
    }


    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
//        invalidateTextPaintAndMeasurements();
    }

    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
