package com.mlj.practicesrep.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyLayout1 extends LinearLayout implements View.OnTouchListener {
    private static final String TAG = "malijia";

    public MyLayout1(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public MyLayout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public MyLayout1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, " shangbian--dispatchTouchEvent()" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "shangbian---onInterceptTouchEvent()" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "shangbian---onTouchEvent()" + ev.getAction());
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("gggmmm", "onTouch: " + motionEvent.getAction());
        if (motionEvent.getAction() == 0) {
            getParent().requestDisallowInterceptTouchEvent(false);
            return true;
        } else if (motionEvent.getAction() == 2) {
            return true;
        } else {
            return false;
        }
    }
//    这些事件的前提都是在一次手势过程中的
//1.onTouch 返回true
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/malijia: shangbian---onInterceptTouchEvent()
    //D/gggmmm: onTouch: 0
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/gggmmm: onTouch: 2
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/gggmmm: onTouch: 2
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/gggmmm: onTouch: 2
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/gggmmm: onTouch: 1

    // 当前VIew【viewGroup】处理了OnTouch down 后，不会走当前的VIew【viewGroup】的Intercept了 ，但是父亲的interce照样走
    // 包括父亲intercept之后，拦截事件的父亲的父亲照样走，拦截的这个父亲不走intece了

// 2. 只有down返回true  其他的路程照样走，但是还是不会走Intercept了，只是后续事件继续走
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/malijia: shangbian---onInterceptTouchEvent()
    //D/gggmmm: onTouch: 0

    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/gggmmm: onTouch: 2
    //D/malijia: shangbian---onTouchEvent()
    //D/malijia: activity- onTouchEvent:

    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia:  shangbian--dispatchTouchEvent()
    //D/gggmmm: onTouch: 1
    //D/malijia: shangbian---onTouchEvent()
    //D/malijia: activity- onTouchEvent:

// 4.   onInterceptTouchEvent 和ontouchEvtne 得陪着使用，若果只拦截了，只是截断子view的传递，
// 如果拦截的View不处理ONtouchevent，还是后续流程传递，activity的ontoucheEVENT等。
//    rootView 拦截所有的事件
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia:  rootView--dispatchTouchEvent()
    //D/malijia: rootView---onInterceptTouchEvent()
    //D/malijia: rootView---onTouchEvent()
    //D/malijia: activity- onTouchEvent:
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia: activity- onTouchEvent:
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia: activity- onTouchEvent:
    //D/malijia: activity- dispatchTouchEvent:
    //D/malijia: activity- onTouchEvent:
    // 自己activity onTouchEvent后，不走自己的intercept

//    5.父亲只拦截move事件，子类只处理Down事件的情况  //后续的事件全部拦截了  如果某个View 一旦决定拦截某次事件,那么这一个事件序列都只能由他来处理即不会在向子View传递,且它的onInterceptTouchEvent不会再被调用!
//D/malijia: activity- dispatchTouchEvent: 0
//D/malijia:  rootView--dispatchTouchEvent()0
//D/malijia: rootView---onInterceptTouchEvent()0
//D/malijia:  shangbian--dispatchTouchEvent()0
//D/malijia: shangbian---onInterceptTouchEvent()0
//D/gggmmm: onTouch: 0
//D/malijia: activity- dispatchTouchEvent: 2
//D/malijia:  rootView--dispatchTouchEvent()2
//D/malijia: rootView---onInterceptTouchEvent()2
//D/malijia:  shangbian--dispatchTouchEvent()3 ///注意这里
//D/gggmmm: onTouch: 3 ///
//D/malijia: shangbian---onTouchEvent()3 ///
//D/malijia: activity- onTouchEvent: 2 ///注意这里
//D/malijia: activity- dispatchTouchEvent: 2
//D/malijia:  rootView--dispatchTouchEvent()2
//D/malijia: rootView---onTouchEvent()2
//D/malijia: activity- onTouchEvent: 2
//D/malijia: activity- dispatchTouchEvent: 2
//D/malijia:  rootView--dispatchTouchEvent()2
//D/malijia: rootView---onTouchEvent()2
//D/malijia: activity- onTouchEvent: 2
//D/malijia: activity- dispatchTouchEvent: 2
//D/malijia:  rootView--dispatchTouchEvent()2
//D/malijia: rootView---onTouchEvent()2
//D/malijia: activity- onTouchEvent: 2
//D/malijia: activity- dispatchTouchEvent: 2
//D/malijia:  rootView--dispatchTouchEvent()2
//D/malijia: rootView---onTouchEvent()2
//D/malijia: activity- onTouchEvent: 2
//D/malijia: activity- dispatchTouchEvent: 2
//D/malijia:  rootView--dispatchTouchEvent()2
//D/malijia: rootView---onTouchEvent()2
//D/malijia: activity- onTouchEvent: 2
//D/malijia: activity- dispatchTouchEvent: 1
//D/malijia:  rootView--dispatchTouchEvent()1
//D/malijia: rootView---onTouchEvent()1
//D/malijia: activity- onTouchEvent: 1

//    6.父亲只拦截down事件

    //D/malijia: activity- dispatchTouchEvent: 0
    //D/malijia:  rootView--dispatchTouchEvent()0
    //D/malijia: rootView---onInterceptTouchEvent()0
    //D/malijia: rootView---onTouchEvent()0
    //D/malijia: activity- onTouchEvent: 0
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 1
    //D/malijia: activity- onTouchEvent: 1

    // 7.if (motionEvent.getAction() == 0) {
    //            getParent().requestDisallowInterceptTouchEvent(true);
    //            return true;
    //        } else {
    //            return false;
    //        }

    //D/malijia: activity- dispatchTouchEvent: 0
    //D/malijia:  rootView--dispatchTouchEvent()0
    //D/malijia: rootView---onInterceptTouchEvent()0
    //D/malijia: rootView---onTouchEvent()0
    //D/malijia: activity- onTouchEvent: 0
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //W/HiTouch_PressGestureDetector: Touch pointer move a lot. The moving distance of X is:0.36499023, limit is:60The moving distance of Y is:68.70764, limit is:60
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 1
    //D/malijia: activity- onTouchEvent: 1

    // 不能在子view的down事件中去干扰父亲View是否拦截down事件


//    8.requestAllDisallow的使用方法

//    子类
    // if (motionEvent.getAction() == 0) {
    //            getParent().requestDisallowInterceptTouchEvent(false);
    //            return true;
    //        } else if (motionEvent.getAction() == 2) {
    //            return true;
    //        } else {
    //            return false;
    //        }


    //父类
    //  Log.d(TAG, "rootView---onInterceptTouchEvent()" + ev.getAction());
    //        if (ev.getAction() == 2) {
    //            return true;
    //        }
    //        return super.onInterceptTouchEvent(ev);

//    子类会印象父类是否执行的结果 onInterceptTouchEvent先于 onTouch执行，只能在一个手势中的前一个动作中，来控制下一个动作。

    //9. 父布局处理了down事件 OnTouchEvent中返回了down的true 后续直走到父布局这里了  其他的事件不重要 重要的是down事件
    //D/malijia: activity- dispatchTouchEvent: 0
    //D/malijia:  rootView--dispatchTouchEvent()0
    //D/malijia: rootView---onInterceptTouchEvent()0
    //D/malijia:  shangbian--dispatchTouchEvent()0
    //D/malijia: shangbian---onInterceptTouchEvent()0
    //D/malijia: shangbian--onTouch: 0
    //D/malijia: shangbian---onTouchEvent()0
    //D/malijia:  xiabian--dispatchTouchEvent()
    //D/malijia: xiabian---onInterceptTouchEvent()
    //D/malijia: xiabian---onTouchEvent()
    //D/malijia: rootView---onTouchEvent()0
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia:  rootView--dispatchTouchEvent()2
    //D/malijia: rootView---onTouchEvent()2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 2
    //D/malijia:  rootView--dispatchTouchEvent()2
    //D/malijia: rootView---onTouchEvent()2
    //D/malijia: activity- onTouchEvent: 2
    //D/malijia: activity- dispatchTouchEvent: 1
    //D/malijia:  rootView--dispatchTouchEvent()1
    //D/malijia: rootView---onTouchEvent()1
    //D/malijia: activity- onTouchEvent: 1
}
