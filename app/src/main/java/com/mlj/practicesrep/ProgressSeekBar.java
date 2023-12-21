package com.mlj.practicesrep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;

public class ProgressSeekBar extends AppCompatSeekBar {

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);
        paint.setTextSize(16.0f * getResources().getDisplayMetrics().density);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);//center就是骑着坐标点画的，left是在坐标点右边边画的，左边文字对齐坐标点。right是在坐标点左边画的，右边文字对齐坐标点。
        return paint;
    }

    public ProgressSeekBar(Context context) {
        super(context);
    }

    public ProgressSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            Paint paint = getPaint();
            android.graphics.Rect thumbRect = getThumb().getBounds();
            float ascent = paint.ascent();
            float textHeight = paint.descent() - ascent;
            int thumbWidth = thumbRect.width();
            int thumbHeight = thumbRect.height();
            String progress = String.valueOf(getProgress());

            Rect bounds = new Rect();
            paint.getTextBounds(progress, 0, progress.length(), bounds);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();

            float textWidth = bounds.width();//z这个是准的
            //paint.measureText(progress); 这个有点偏差
            //
            float x = thumbRect.left + (thumbWidth - textWidth) / 2.0f;
            float y = thumbRect.top - ascent + (thumbHeight - textHeight) / 2.0f;

            canvas.drawText(progress, x, y, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DefaultProgressBarChangeListener implements OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
