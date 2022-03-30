package com.mlj.practicesrep.viewpagesetcurrent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mlj.practicesrep.R;

public class ViewPagerCurrentPageTestActivity extends AppCompatActivity {

    private ViewPager mTestViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_current_page_test);
        mTestViewPager = findViewById(R.id.test_viewPager);
        mTestViewPager.setOffscreenPageLimit(1);
        mTestViewPager.setAdapter(new TestAdapter());
        mTestViewPager.setCurrentItem(5);

        // 鸿蒙 会创建012 然后跳到5页面

        // android 这里直接跳转的5页面
        //I/System.out: docker11  instantiateItem position =5
        //I/System.out: docker11  instantiateItem position =4
        //I/System.out: docker11  instantiateItem position =6
        // 如果不设置setCurrentItem的话
        //I/System.out: docker11  instantiateItem position =0
        //I/System.out: docker11  instantiateItem position =1
        // 如果对setCurrentItem添加一个delay的话 先建01（没有2）
        // I/System.out: docker11  instantiateItem position =0
        // I/System.out: docker11  instantiateItem position =1
        // I/System.out: docker11  instantiateItem position =5
        // I/System.out: docker11  instantiateItem position =4
        // I/System.out: docker11  instantiateItem position =6
        // I/System.out: docker11  destroyItem position =1
        // I/System.out: docker11  destroyItem position =0

    }

}