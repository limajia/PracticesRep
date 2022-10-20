package com.docker.threadtestlib;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        // 内部也是同步的
        final CountDownLatch countDownLatch = new CountDownLatch(10);

        new Thread("custom") {
            @Override
            public void run() {
                super.run();
                countDownLatch.countDown();
                System.out.println("子线程执行完" + countDownLatch.getCount());
            }
        }.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 注意区分object.wait 和此处的await
        //1.Object monitor methods (wait, notify and notifyAll) {用来成对通信}
        //2.此处的await()，等待
        //3.ReentrantLock中 lock.newCondition()的 await，signal，signalAll{用来成对通信}
        System.out.println("main 执行结束");
    }
}
