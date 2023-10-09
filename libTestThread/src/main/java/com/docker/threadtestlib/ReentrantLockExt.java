package com.docker.threadtestlib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 这也是testReentrantLock
// 单生产者/单消费者 多生产者/多消费者 notify随机-全wait死锁 显示锁
// https://www.jianshu.com/p/146312dc748c
// java 这里只体现了等待池
public class ReentrantLockExt {
    public static void main(String[] args) {
        Target t = new Target();

        Thread t1 = new Increase(t);
        t1.setName("Increase1：");
        // 添加体现锁池的线程
        Thread t11 = new Increase(t);
        t11.setName("Increase2：");
        //
        Thread t2 = new Decrease(t);
        t2.setName("Decrease1：");
        Thread t22 = new Decrease(t);
        t22.setName("Decrease2：");

        t1.start();
        t11.start();
        t2.start();
        t22.start();
    }

}

class Target {
    private int count;
    private final Lock lock;
    private final Condition deCreaseCondition;
    private final Condition inCreaseCondition;
    // 这两个Condition用来区分 不同类型（如读、写）的Thread，自己唤醒自己的类型的线程。如果Object.notify/wait,会统一使用类似Lock对象的notify/wait
    // 让所有的部分类型的线程去争取锁。

    public Target() {
        lock = new ReentrantLock();
        inCreaseCondition = lock.newCondition();
        deCreaseCondition = lock.newCondition();
    }

    // 此demo 只有一个线程操作这个函数, 添加一个t11线程 同时更改
    public void increase() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":函数前" + count);
            if (count == 2) {
                try {
                    System.out.println("count=2执行");
                    inCreaseCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("count=" + count + "执行");
            }
            count++;
            System.out.println("docker：" + Thread.currentThread().getName() + ":" + count);
            deCreaseCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
            if (count == 0) {
                try {
                    //等待，由于Decrease线程调用的该方法,
                    //所以Decrease线程进入对象t(main函数中实例化的)的等待池，并且释放对象t的锁
                    deCreaseCondition.await();//Object类的方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            System.out.println(Thread.currentThread().getName() + ":" + count);

            //唤醒线程Increase，Increase线程从等待池到锁池
            inCreaseCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Increase extends Thread {
    private Target t;

    public Increase(Target t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.increase();
        }
    }
}

class Decrease extends Thread {

    private Target t;

    public Decrease(Target t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                //随机睡眠0~500毫秒
                //sleep方法的调用，不会释放对象t的锁
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t.decrease();

        }

    }

}