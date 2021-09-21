package com.mlj.practicesrep;

import android.app.Application;
import android.os.Handler;
import android.view.Choreographer;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;


public class App extends Application {

    private static Application context;

    public static Application getApp() {
        return context;
    }

    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        handler = new Handler(getMainLooper());

        //1.6 老版本 进程检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);

        // 2.7无需初始化 自动完成初始化


        BlockCanary.install(this, new BlockCanaryContext()).start();
        //伪代码
//        monitor.start()
//        dispatchMessage(msg)
//        monitor.end;

        //1.简单实现
       /* Looper.getMainLooper().setMessageLogging(new Printer() {
            boolean start = true;
            long startTime = 0;

            @Override
            public void println(String x) {
                if (start) {
                    start = false;
                    startTime = System.currentTimeMillis();
                } else {
                    start = true;
                    System.out.println("monitor" + "耗时=" + (System.currentTimeMillis() - startTime));
                }
            }
        });*/


        // android p(不包括)之前，可以通过looperde log打印中的msg的 target 和 what，过滤activityThread
        //中的H handler 和生命周期case 监听生命周期；
        // 后面版本修改了case值，生命周期自己类实现，无法通过上面的方法 来监听了

//        final Printer logging = me.mLogging;
//        if (logging != null) {
//            logging.println(">>>>> Dispatching to " + msg.target + " " +
//                    msg.callback + ": " + msg.what);  //滑动列表时，产生更多的对象创建，更加的影响性能
//        }

        final int[] framesPerSecond = {0};

        //2.android对简单实现的 优化 避免创建多个string对象等 编舞者两者绘制的时间
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                // frameTimeNanos 去拿堆栈信息
                // 再次抛进去
                framesPerSecond[0]++;
                Choreographer.getInstance().postFrameCallback(this);
            }
        });

        // 在onCeate中延迟 阻止了绘制 Choreographer绘制变慢 或停止，达到边界 anr了
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("dockerCanary: 帧率=" + framesPerSecond[0]);
                framesPerSecond[0] = 0;
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    //问题 使用lambda表达式的话，this 代表外面的类 这里是app
//()->
//    {
//  System.out.println("dockerCanary: 帧率=" + framesPerSecond[0]);
//                framesPerSecond[0] = 0;
//                handler.postDelayed(this, 1000);
//    }
}
