package com.docker.handwrite;

import android.os.CountDownTimer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        //
        MLooper.prepare();
        final MHandler handler = new MHandler() {
            @Override
            protected void handleMessage(MMessage message) {
                super.handleMessage(message);
                System.out.println("收到消息了:messsgae.what=" + message.what);
            }
        };
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MMessage message = new MMessage();
                message.what = 1024;
                handler.sendMessage(message);
            }
        }.start();

        // 自己写的代码 loop中的while(true)会阻止主线程向下执行

        // 系统的生命周期函数 也是在MainLooper的中消息循环执行的。
        // new 一个Handler 得到的是也还是主looper 消息也是发送到了MainLooper的 不会阻塞
        MLooper.loop();
        //  logd

    }
}