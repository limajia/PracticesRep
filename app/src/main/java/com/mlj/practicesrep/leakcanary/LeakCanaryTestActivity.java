package com.mlj.practicesrep.leakcanary;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;


// 截图源码参考：https://github.com/limajia/readLeakCanary
public class LeakCanaryTestActivity extends AppCompatActivity {

    private static final String TAG = "docker_leak";

    private Button mLeakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary_test);
        mLeakBtn = findViewById(R.id.leak_btn);
        mLeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object cde = this; // 这里的this 是代表LeakCanaryTestActivity$1 代表一个内部类
                //这里的this 持有一个对象成员变量this$0 就是外部类LeakCanaryTestActivity
                doLeak();
            }
        });
    }

    private void doLeak() {
        //this 代表这个方法 是谁的成员  从哪里谁调用时不靠谱的如这里的调用
        // 这里的this 代表LeakCanaryTestActivity
        Object abc = this;
        new LeakObject(this).leak(); //点击按钮 申请内存时候 oom 达到了内存极限 不知道哪里出的问题内存没有释放 很难定位
        finish();

        //leakcanary 分debug。release依赖（空实现），应为修改了代码逻辑，都必须使用这些方法
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(TAG + "finalize");// 这个方法调没有调用，用来最简单的判断对象是否释放或泄漏的方法。
        super.finalize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doGc();// 对象方法中执行gc 该对象一定不会释放 开一个新的线程 进行释放gc操作
    }

    private void doGc() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                Runtime.getRuntime().gc(); //没有延迟 导致gc没有回收当前的activity对象
            }
        }.start();
    }
}