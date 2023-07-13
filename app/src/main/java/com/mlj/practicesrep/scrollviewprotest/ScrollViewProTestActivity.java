package com.mlj.practicesrep.scrollviewprotest;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class ScrollViewProTestActivity extends AppCompatActivity {

    private LinearLayout wrapper;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private ScrollView scrollView;

    Handler handler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_pro_test);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        wrapper = (LinearLayout) findViewById(R.id.wrapper);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        view3 = (View) findViewById(R.id.view3);
        view4 = (View) findViewById(R.id.view4);
        handler.postDelayed(() -> {
            int[] window = new int[2];
            int[] screen = new int[2];
            wrapper.getLocationInWindow(window);
            wrapper.getLocationOnScreen(screen);
            System.out.println("wrapper = " + wrapper.getLeft() + "-- " + wrapper.getTop() +
                    "--" + wrapper.getRight() + "--" + wrapper.getBottom() +
                    "--getwindowpos=" + window[0] + "/" + window[1] +
                    "--getScreenpos=" + screen[0] + "/" + screen[1]);
            view1.getLocationInWindow(window);
            view1.getLocationOnScreen(screen);
            System.out.println("view1.getLeft() = " + view1.getLeft() + "--view1.getTop() = " + view1.getTop() + "--view1.getRight() = " +
                    view1.getRight() + "--view1.getBottom() = " + view1.getBottom() +
                    "--view1.getwindowpos() = " + window[0] + "/" + window[1] +
                    "--getScreenpos=" + screen[0] + "/" + screen[1]
            );
        }, 200);

        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                handler.postDelayed(() -> {
                    int[] window = new int[2];
                    int[] screen = new int[2];
                    wrapper.getLocationInWindow(window);
                    wrapper.getLocationOnScreen(screen);
                    System.out.println("wrapper = " + wrapper.getLeft() + "-- " + wrapper.getTop() +
                            "--" + wrapper.getRight() + "--" + wrapper.getBottom() +
                            "--getwindowpos=" + window[0] + "/" + window[1] +
                            "--getScreenpos=" + screen[0] + "/" + screen[1]);
                    view1.getLocationInWindow(window);
                    view1.getLocationInWindow(screen);
                    System.out.println("view1.getLeft() = " + view1.getLeft() + "--view1.getTop() = " + view1.getTop() + "--view1.getRight() = " +
                            view1.getRight() + "--view1.getBottom() = " + view1.getBottom() +
                            "--view1.getwindowpos() = " + window[0] + "/" + window[1] +
                            "--getScreenpos=" + screen[0] + "/" + screen[1]
                    );
                }, 200);
            }
        });
    }
}