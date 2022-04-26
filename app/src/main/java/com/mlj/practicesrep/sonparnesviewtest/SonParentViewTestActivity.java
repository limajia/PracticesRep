package com.mlj.practicesrep.sonparnesviewtest;

import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class SonParentViewTestActivity extends AppCompatActivity {

    private LinearLayout mParentView;
    private LinearLayout mSonView;
    private LinearLayout mRootView;
    private SurfaceView mScreenWidthSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_parent_view_test);
        initView();
    }

    private void initView() {
        mParentView = findViewById(R.id.parentView);
        mSonView = findViewById(R.id.sonView);
        mRootView = findViewById(R.id.rootView);
        mScreenWidthSize = findViewById(R.id.screen_width_size);
        mScreenWidthSize.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                // 控件换成surfaceVIew的话，surface的宽度也是可以超过屏幕宽度的,但是鸿蒙的不可以超过屏幕宽度
                // 如果通过setFixedSize，修改surface的大小后，会通过strength拉伸的形式，缩放到surfaceView大小范围内
                // 如果surface的大小和surfaceView的大小相同，那该多大就多大，该超出屏幕就超出屏幕
                System.out.println("docker13:surfaceChanged width=" + width);//1300
                System.out.println("docker13:surfaceChanged height=" + height);//200
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
        mRootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("docker13:p" + mParentView.getWidth());
                System.out.println("docker13:p" + mParentView.getMeasuredWidth());
                System.out.println("docker13:s" + mSonView.getWidth());
                System.out.println("docker13:s" + mSonView.getMeasuredWidth());
                //
                System.out.println("docker13:r" + mRootView.getWidth());
                System.out.println("docker13:r" + mRootView.getMeasuredWidth());
                System.out.println("docker13:screen" + mScreenWidthSize.getWidth());
                System.out.println("docker13:screen" + mScreenWidthSize.getMeasuredWidth());
                //
                View decorView = getWindow().getDecorView();
                ViewGroup.LayoutParams layoutParams = decorView.getLayoutParams();
                System.out.println("docker13:decorView w" + layoutParams.width);
                System.out.println("docker13:decorView h" + layoutParams.height);
                // I/System.out: docker13:decorView w-1
                // I/System.out: docker13:decorView h-1

                //I/System.out: docker13:r1080 mRootView包裹/match 结果都是一样的 ，docerview-1 matchparent
                //I/System.out: docker13:r1080
                //I/System.out: docker13:screen1300
                //I/System.out: docker13:screen1300
            }
        }, 1000);

    }

    //I/System.out: docker13:p600
    //I/System.out: docker13:p600
    //I/System.out: docker13:s700
    //I/System.out: docker13:s700

}