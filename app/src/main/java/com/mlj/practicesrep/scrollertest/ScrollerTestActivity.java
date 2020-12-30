package com.mlj.practicesrep.scrollertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mlj.practicesrep.R;

/**
 * @author docker
 * 滚动测试
 */
public class ScrollerTestActivity extends AppCompatActivity {
    private View mCustomView;
    private Runnable runnable = new Runnable() {
        int origin = 0;
//        float origin = 1.0f;

        @Override
        public void run() {
            origin += 5;
//            origin *= 1.1f;
            mCustomView.scrollBy(10,0);
            mCustomView.postDelayed(this, 1000);
        }
    };
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_test);
        mCustomView = findViewById(R.id.custom_view);
        rootView = findViewById(R.id.root_view);
        mCustomView.post(runnable);
    }
}