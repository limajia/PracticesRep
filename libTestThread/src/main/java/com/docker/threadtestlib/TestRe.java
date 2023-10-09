package com.docker.threadtestlib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestRe {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
//        final Condition condition = lock.newCondition(); //不同的condition也维护着一个双向链表 继承了aqs
//        System.out.println(lock.getHoldCount());//the number of holds on this lock by the current thread[//当前现车个重入的次数 ], or zero if this lock is not held by the current thread
        System.out.println(lock.getQueueLength());


        new Thread() {
            @Override
            public void run() {
                super.run();
                lock.lock();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                lock.lock();
            }
        }.start();

        Thread.sleep(2000);
        System.out.println(lock.getQueueLength()+"_main"); //结果：1 后面的没有获取锁的进入双向链表了
    }
}
