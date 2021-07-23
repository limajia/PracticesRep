package com.mlj.practicesrep.blockqueue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockQueue {
    @Test
    public void testMain() {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1进入了");
                    String poll = blockingQueue.poll(10,TimeUnit.SECONDS); //有锁 会阻塞其他的线程
                    System.out.println("1正常执行完了 poll="+poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("1中断了");
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2进入了");
                try {
                    String poll = blockingQueue.poll(15, TimeUnit.SECONDS); //有锁 会阻塞其他的线程
                    System.out.println("2正常执行完了 poll=" + poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("2中断了");
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(6000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (;;){

        }
    }
}
//blockingQueue.poll(); // 为空立即返回了 null //不会抛出InterruptedException
//blockingQueue.poll(10, TimeUnit.SECONDS) // 为空 则上锁 阻塞等待 //会抛出InterruptedException
//多个线程poll(time,unit) 会按等待时间长短排对

//如果主线程结束了 这个await的线程会抛弃了 不执行了
//log为:
//1进入了
//2进入了
