package com.mlj.practicesrep.sonparnesviewtest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class SonParentViewTestActivity extends AppCompatActivity {

    private LinearLayout mParentView;
    private LinearLayout mSonView;
    private LinearLayout mRootView;
    private LinearLayout mScreenWidthSize;

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