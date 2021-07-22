package com.mlj.practicesrep.blockqueue;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
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
                    String poll = blockingQueue.poll(2, TimeUnit.SECONDS); //有锁 会阻塞其他的线程
                    System.out.println("1结束了 poll="+poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("yichangle");
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2进入了");
                try {
                    String poll = blockingQueue.poll(1, TimeUnit.SECONDS); //有锁 会阻塞其他的线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2执行了");
            }
        }).start();

        for (;;){

        }
    }
}
//blockingQueue.poll(); // 为空立即返回了 null
//blockingQueue.poll(10, TimeUnit.SECONDS) // 为空 则上锁 阻塞等待
//多个线程poll 会按等待时间长短排对
