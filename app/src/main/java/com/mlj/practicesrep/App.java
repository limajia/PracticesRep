package com.mlj.practicesrep;

import android.app.Application;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //1.6 老版本 进程检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
    }

// 2.7无需初始化 自动完成初始化
}
