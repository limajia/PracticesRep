package com.docker.threadtestlib.lock;

// 读写锁、条件变量 其实都是为了同步通信，前者主同步，后者主通信（前提是获得自己的monitor）
// 自己的锁-->「自己的锁范围内，（临界条件满足后）执行自己的await， 在临界条件不满足时候执行其他的notify」。

import java.util.concurrent.locks.ReentrantReadWriteLock;

// wait，一般在方法的入口处，直接将当前线程 丢锁放去等待池。
// 锁，在锁池中排队的线程，获取锁后都会在获得锁处继续执行。
public class TestReentrantLockExt2 {//读锁是防止读到写的中间值。

    public static void main(String[] args) {

        //例子1 不使用锁，先设置了1但是get的还是0
       /* final ShareContent shareContent = new ShareContent();
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 先写
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                shareContent.setData();
                while (true) {
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                shareContent.getData();
                while (true) {
                }
            }
        }.start();*/

        //例子2，使用读写锁
        // 读写锁之间也是互斥的。
        // 一个写锁会阻止其他的写锁和读锁。一个读锁不会影响读锁，但是会阻止写锁。
        // 为了防止多个读会延迟写的操作，系统内部会提高写的优先级。。。
        final ShareContent2 shareContent2 = new ShareContent2();
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 先写
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                shareContent2.setData();
                while (true) {
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                // 后读
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                shareContent2.getData();
                while (true) {
                }
            }
        }.start();

    }
    // 主线程会等着其他线程结束
}


// 例子1 使用的共同访问对象
/*class ShareContent {
    private int data = 0;

    public int getData() {
        System.out.println("docker222: get=" + data);
        return data;
    }

    public void setData() {
        data++;
        System.out.println("docker222: set=" + data);
    }
}*/

// 例子2 使用的共同访问对象
class ShareContent2 {
    private final ReentrantReadWriteLock reentrantReadWriteLock;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;
    private int data = 0;
    // 没有什么临界条件和通信，直接使用Lock

    public ShareContent2() {
        reentrantReadWriteLock = new ReentrantReadWriteLock();
        readLock = reentrantReadWriteLock.readLock();
        writeLock = reentrantReadWriteLock.writeLock();
    }

    public int getData() {
        System.out.println("docker222: 开始读取---");
        readLock.lock();
        System.out.println("docker222: get=" + data);
        //readLock.unlock();
        return data;
    }

    public void setData() {
        System.out.println("docker222: 开始设置--");
        writeLock.lock();
        data++;
        System.out.println("docker222: set=" + data);
        writeLock.unlock();
    }
}