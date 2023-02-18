package com.mlj.practicesrep.viewmainfun;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class TestViewMainFunsActivity extends AppCompatActivity {

    private Button mAddAnotherView;
    private LinearLayout mRooterView;
    boolean hasAdded;
    private ViewMainFunsView22 viewMainFunsView22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ViewMainFunsView activity: TestViewMainFunsActivity.onCreate");
        setContentView(R.layout.activity_test_view_main_funs);
        mAddAnotherView = findViewById(R.id.addAnotherView);
        mRooterView = findViewById(R.id.rooter_view);
        // 这个xml已经inflate完了，想自己处理，则自己定义view，到view中去监听那个回调
        mAddAnotherView.setOnClickListener(v -> {
            if (!hasAdded) {
                viewMainFunsView22 = new ViewMainFunsView22(
                        TestViewMainFunsActivity.this);
                ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);//若是400 看home回来
                mRooterView.addView(viewMainFunsView22, params);
                hasAdded = true;
            } else {
                mRooterView.removeView(viewMainFunsView22);
                hasAdded = false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("ViewMainFunsView activity: TestViewMainFunsActivity.onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("ViewMainFunsView activity: TestViewMainFunsActivity.onResume");
    }
}

// https://blog.csdn.net/nihaomabmt/article/details/109777372 参考Android View的生命周期函数总结
// 补充activity onCreate和onResume  //onWindowVisibilityChanged 应该是View自己的onVisibilityChanged()
/*
2023-02-18 20:11:31.895 20455-20455/com.mlj.practicesrep I/System.out: ViewMainFunsView activity: TestViewMainFunsActivity.onCreate
2023-02-18 20:11:31.904 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: ViewMainFunsView() called with: context = [com.mlj.practicesrep.viewmainfun.TestViewMainFunsActivity@8da5647], attrs = [android.content.res.XmlBlock$Parser@d9f4b6a]
2023-02-18 20:11:31.904 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onFinishInflate() called
2023-02-18 20:11:31.912 20455-20455/com.mlj.practicesrep I/System.out: ViewMainFunsView activity: TestViewMainFunsActivity.onStart
2023-02-18 20:11:31.914 20455-20455/com.mlj.practicesrep I/System.out: ViewMainFunsView activity: TestViewMainFunsActivity.onResume
2023-02-18 20:11:31.948 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onAttachedToWindow() called
2023-02-18 20:11:31.948 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onWindowVisibilityChanged() called with: visibility = [0]
2023-02-18 20:11:31.952 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073743996]
2023-02-18 20:11:31.963 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073743996]
2023-02-18 20:11:31.964 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onSizeChanged() called with: w = [1080], h = [2172], oldw = [0], oldh = [0]
2023-02-18 20:11:31.964 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onLayout() called with: changed = [true], left = [0], top = [0], right = [1080], bottom = [2172]
2023-02-18 20:11:31.965 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onDraw() called with: canvas = [android.graphics.RecordingCanvas@f72358]
2023-02-18 20:11:31.993 20455-20455/com.mlj.practicesrep D/ViewMainFunsView11: onWindowFocusChanged() called with: hasWindowFocus = [true]
* */
//



/*

// xml创建一个view 然后代码直接在view树中add 一个View，观察声明周期的执行顺序和结果

// 11创建完成

        D/ViewMainFunsView11: ViewMainFunsView() called with: context = [com.mlj.practicesrep.viewmainfun.TestViewMainFunsActivity@1cb4b24], attrs = [android.content.res.XmlBlock$Parser@cca9ca7]
        D/ViewMainFunsView11: onFinishInflate() called
        D/ViewMainFunsView11: onAttachedToWindow() called
        D/ViewMainFunsView11: onWindowVisibilityChanged() called with: visibility = [0]
        D/ViewMainFunsView11: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073743891]
        D/ViewMainFunsView11: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073743891]
        D/ViewMainFunsView11: onSizeChanged() called with: w = [1080], h = [2067], oldw = [0], oldh = [0]
        D/ViewMainFunsView11: onLayout() called with: changed = [true], left = [0], top = [0], right = [1080], bottom = [2067]
        D/ViewMainFunsView11: onDraw() called with: canvas = [android.graphics.RecordingCanvas@2cf7ac8]
        D/ViewMainFunsView11: onWindowFocusChanged() called with: hasWindowFocus = [true]

// 22 创建完成

        D/ViewMainFunsView22: ViewMainFunsView() called with: context = [com.mlj.practicesrep.viewmainfun.TestViewMainFunsActivity@1cb4b24]
        D/ViewMainFunsView22: onAttachedToWindow() called
        D/ViewMainFunsView22: onWindowVisibilityChanged() called with: visibility = [0]   // 没有执行 onWindowFocusChanged
        D/ViewMainFunsView22: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073744035]
        D/ViewMainFunsView11: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073741824]
        D/ViewMainFunsView11: onSizeChanged() called with: w = [1080], h = [0], oldw = [1080], oldh = [2067]
        D/ViewMainFunsView11: onLayout() called with: changed = [true], left = [0], top = [-144], right = [1080], bottom = [-144]
        D/ViewMainFunsView22: onSizeChanged() called with: w = [1080], h = [2211], oldw = [0], oldh = [0]
        D/ViewMainFunsView22: onLayout() called with: changed = [true], left = [0], top = [0], right = [1080], bottom = [2211]
        D/ViewMainFunsView22: onDraw() called with: canvas = [android.graphics.RecordingCanvas@2cf7ac8]
        D/ViewMainFunsView11: onDraw() called with: canvas = [android.graphics.RecordingCanvas@40c50c]
        D/ViewMainFunsView11: onDraw() called with: canvas = [android.graphics.RecordingCanvas@40c50c] //这里注意

// home一下

        D/ViewMainFunsView11: onWindowFocusChanged() called with: hasWindowFocus = [false]
        D/ViewMainFunsView22: onWindowFocusChanged() called with: hasWindowFocus = [false]
        D/ViewMainFunsView11: onWindowVisibilityChanged() called with: visibility = [8]
        D/ViewMainFunsView22: onWindowVisibilityChanged() called with: visibility = [8]

//home 回来

        D/ViewMainFunsView11: onWindowVisibilityChanged() called with: visibility = [0]
        D/ViewMainFunsView22: onWindowVisibilityChanged() called with: visibility = [0]
        D/ViewMainFunsView22: onDraw() called with: canvas = [android.graphics.RecordingCanvas@2cf7ac8] // 最上面的绘制一下 若22不是全部盖住 则11也会执行onDraw
        D/ViewMainFunsView11: onWindowFocusChanged() called with: hasWindowFocus = [true]
        D/ViewMainFunsView22: onWindowFocusChanged() called with: hasWindowFocus = [true]

// 销毁页面
        D/ViewMainFunsView11: onWindowFocusChanged() called with: hasWindowFocus = [false]
        D/ViewMainFunsView22: onWindowFocusChanged() called with: hasWindowFocus = [false]
        D/ViewMainFunsView11: onWindowVisibilityChanged() called with: visibility = [8]
        D/ViewMainFunsView22: onWindowVisibilityChanged() called with: visibility = [8]
        D/ViewMainFunsView11: onDetachedFromWindow() called
        D/ViewMainFunsView22: onDetachedFromWindow() called

// 执行remove22 看看生命周期的变化。
D/ViewMainFunsView22: onWindowVisibilityChanged() called with: visibility = [8] //removeView都会执行这里
D/ViewMainFunsView22: onDetachedFromWindow() called //removeView都会执行这里
D/ViewMainFunsView11: onMeasure() called with: widthMeasureSpec = [1073742904], heightMeasureSpec = [1073743939]
D/ViewMainFunsView11: onSizeChanged() called with: w = [1080], h = [2115], oldw = [1080], oldh = [1715]
D/ViewMainFunsView11: onLayout() called with: changed = [true], left = [0], top = [0], right = [1080], bottom = [2115]
D/ViewMainFunsView11: onDraw() called with: canvas = [android.graphics.RecordingCanvas@336eeb1]
D/ViewMainFunsView11: onDraw() called with: canvas = [android.graphics.RecordingCanvas@2753764]
*/
