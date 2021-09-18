package com.mlj.practicesrep.leakcanary;

import android.os.SystemClock;

public class LeakObject extends Thread {
    private static final String TAG = "docker";

    LeakCanaryTestActivity mle;

    public LeakObject(LeakCanaryTestActivity leakCanaryTestActivity) {
        mle = leakCanaryTestActivity;
    }

    @Override
    public void run() {
        super.run();
        //      LeakCanaryTestActivity local = mle;
        //      mle = null;//确保成员变量不再引用对象 ？？？ 作用？？ 直接使用mle貌似效果是一样
        SystemClock.sleep(10000);
        System.out.println(TAG + "local =" + mle.toString());//没有释放
    }

    public void leak() {
        start();
    }
}
