package com.mlj.customviews.verticaldrawerlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mlj.customviews.R;

public class VerticalDrawerLayoutActivity extends AppCompatActivity {

    VerticalDrawerLayout mDrawerLayout;
    ImageView mArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_drawer_layout);

        mDrawerLayout = (VerticalDrawerLayout) findViewById(R.id.vertical);
        mArrow = (ImageView) findViewById(R.id.img);


        mDrawerLayout.setDrawerListener(new VerticalDrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mArrow.setRotation(slideOffset * 180);
            }
        });
    }

    public void onClick(View v) {
        if (mDrawerLayout.isDrawerOpen()) {
            mDrawerLayout.closeDrawer();
        } else {
            mDrawerLayout.openDrawerView();
        }
    }
}