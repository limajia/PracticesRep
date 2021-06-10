package com.mlj.practicesrep.tabhosttest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.mlj.practicesrep.R;

//View默认是透明无颜色的 window 背景是白色
//TabHost可以方便的在窗口上放置多个标签页，每个标签页相当于或得了一个与外部容器相同大小的组件摆放区域
//其中控件TabWidget:代表选项卡的标签条
//      TabSpec:代表选项卡的一个Tab页面。

/*
使用TabHost的方法
1.继承TabActivity
2.继承ActivityGroup，关联tabhost
*/
public class TabHostTestActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host_test);

        //1.设置不同View
        /*TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1").setIndicator("班级").setContent(R.id.tab01);
        tabHost.addTab(tabSpec);
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tab12").setIndicator("消息").setContent(R.id.tab02);
        tabHost.addTab(tabSpec1);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab3").setIndicator("我").setContent(R.id.tab03);
        tabHost.addTab(tabSpec2);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                View viewById1 = findViewById(R.id.tab01);
                View viewById2 = findViewById(R.id.tab02);
                View viewById3 = findViewById(R.id.tab03);
                Log.d("docker", "onTabChanged: "+viewById1.getVisibility()+":"+viewById2.getVisibility()+":"+viewById3.getVisibility());
                //D/docker: onTabChanged: 8:0:8
                //D/docker: onTabChanged: 8:8:0
                //D/docker: onTabChanged: 0:8:8
                //其他的布局都gone了
            }
        });*/

        // 2.跳转activity
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1").setIndicator("班级").setContent(new Intent(this, Tab1Activity.class));
        tabHost.addTab(tabSpec);
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tab12").setIndicator("消息").setContent(new Intent(this, Tab2Activity.class));
        tabHost.addTab(tabSpec1);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab3").setIndicator("我").setContent(new Intent(this, Tab3Activity.class));
        tabHost.addTab(tabSpec2);
         /*
        // 切换
        Tab1Activity: onCreate:
        Tab1Activity: onStart:
        Tab1Activity: onResume:
        Tab1Activity: onPause:
        Tab2Activity: onCreate:
        Tab2Activity: onStart:
        Tab2Activity: onResume:

        退后台
        D/Tab2Activity: onPause:
        D/Tab1Activity: onStop:
        D/Tab2Activity: onStop:

        重新打开
        D/Tab2Activity: onRestart:
        D/Tab2Activity: onStart:
        D/Tab2Activity: onResume

        切换
        D/Tab2Activity: onPause:
        D/Tab1Activity: onRestart: //这里
        D/Tab1Activity: onStart:
        D/Tab1Activity: onResume:
        */

    }
}