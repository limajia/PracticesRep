package com.docker.handwrite.handevent.listener;

import com.docker.handwrite.handevent.MotionEvent;
import com.docker.handwrite.handevent.View;

public interface OnTouchListener {
    boolean onTouch(View view, MotionEvent event);
}
