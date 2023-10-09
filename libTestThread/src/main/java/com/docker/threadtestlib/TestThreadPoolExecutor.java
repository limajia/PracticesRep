package com.docker.threadtestlib;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPoolExecutor {
    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                0,
                5,
                5L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(10),
                new ThreadFactory() {
                    AtomicInteger atomicInteger = new AtomicInteger();

                    @Override
                    public Thread newThread(Runnable runnable) {
                        int index = atomicInteger.incrementAndGet();
                        //命名 标识等
                        return new Thread(runnable, "线程名称-" + index);
                    }
                }
                ,
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + "被丢弃");
                    }
                }
        );
        executorService.allowCoreThreadTimeOut(true);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(myTask);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
