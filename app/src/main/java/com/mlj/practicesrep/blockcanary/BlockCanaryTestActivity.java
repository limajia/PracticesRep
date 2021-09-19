package com.mlj.practicesrep.blockcanary;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.Trace;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

import hugo.weaving.DebugLog;

//https://zhuanlan.zhihu.com/p/82520887?from_voters_page=true 掉帧原理
//查看trace view 文件 chrome:tracing  load文件即可
//生命周期 不doframe  查看frame 中断的位置，就是耗时的位置，看cpu运行时间，和整个观察端的时间

//Android性能优化—TraceView的使用 1.profier 2.代码systrace
@DebugLog
public class BlockCanaryTestActivity extends AppCompatActivity {

    private Button mDoBlock;
    private long durations = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        durations = System.currentTimeMillis();

        //4. sdcard/android/包/file
//        Debug.startMethodTracing("sample");

        //5.
        Trace.beginSection("abcdedg");
        setContentView(R.layout.activity_block_canary_test);
        mDoBlock = findViewById(R.id.do_block);
        mDoBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1/
                //SystemClock.sleep(2000);
                // 这里是使用looper的传入priter日志
                //2 80%的阈值时间去取stack信息 可能定位不准 不是完全准确 大多时候 可以解决大多数的问题
                doA();
                doB();//可能会定位到B这个方法里面去
                doC();

                //framework是通过反射 调用点击事件的

                //3 解决2的问题，每个方法都加一个统计耗时的时间， 差值到每个方法的前面和后面
                // 使用hugo库，使用aspectj【注解，生成额外方法增加字节数】，插桩到方法里面统计方法耗时 只能logcat查看 不能满足

                //4.看到整个应用运行时间 traceView 开销特别大 10ms 可能100ms google不推荐使用
                // thread time 休眠时间不会计入
                // wall clock time 全部系统进行时间

                //5.Systrace 在framework层 系统关键路径位置的时间 开销比较小 或者直接使用profiler
                //如 looper中的 Trace.traceBegin  traceEnd
                //如 viewrootimpl performmeasure中
                //如 activityThread中  都有Trace.traceBegin类标记

                // 应用层使用的话
                // sdk/platform/systrace 工具包完善
                // python 安装好
                // api18 以上 越高越好 设备连接
                // 代码：Trace.beginSection("");
                // 使用python命令获取标识信息 python systrace.py -a 包名 --time -t  开启跟踪模式 和profile和功能一样
                // 产生trace.html文件

                // 最后 systrace.py 可以分析电量 硬件等等 查看功能支持 ./systrace.py -l

                // api28 以后 可以在模拟器终端上 打开system tracing 模拟器上手动生成trace文件，不用输入命令
                // adb pull data/local/traces/  [/data 目录data app local]
                // 文件以.perfetto-trace结尾 使用网址查看perfetto.dev/#/

                // 块状是方法 耗时信息等  条状是线程时间段 可以看到线程状态
                // frame一帧： 绿色 16ms内 黄色红色 则表示超出了  cpu大小核 大的频率高
                // 绿色线程状态 runing 有onCpu执行了  蓝色runnable oncpu为null 没有分配到核执行 灰色sleeping 状态  橘红色Uninterceptible Sleep�状态 表示正在io等操作

                // 该方法还是侵入性，所以使用asm进行插桩。
                // 更好的考虑使用腾讯开源库traceCanary
            }
        });
        //4.
//        Debug.stopMethodTracing();

        //5.
        Trace.endSection();

        //6.测试anr 和 掉帧问题  只要阻塞主线程 就会丢帧，丢帧的严重了，就会anr
        System.out.println("abcdefg=" + (System.currentTimeMillis() - durations));
        SystemClock.sleep(10000);
    }

    @DebugLog
    void doA() {
        SystemClock.sleep(1780);
    }

    void doB() {
        SystemClock.sleep(25);
    }

    void doC() {
        SystemClock.sleep(1100);
    }
}

