package com.mlj.practicesrep.drawabletest;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LetterDrawable extends Drawable {

    private char letter = 'A';
    Paint paint = new Paint();


    @Override
    public void draw(@NonNull Canvas canvas) {
        paint.setTextSize(60f);
        paint.setColor(0xff00ff00);
        canvas.drawText(String.valueOf(letter), 0f, 100f, paint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    //支持读取属性

    @Override
    public void inflate(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs) throws IOException, XmlPullParserException {
        super.inflate(r, parser, attrs);
        //
    }
}

