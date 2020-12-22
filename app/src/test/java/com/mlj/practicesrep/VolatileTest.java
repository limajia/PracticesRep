package com.mlj.practicesrep;

import org.junit.Test;

public class VolatileTest {
    public static volatile int t = 0;

    @Test
    public void testMain() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            //每个线程对t进行1000次加1的操作
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    t = t + 1; // 这不是原子操作，              i++也不是，但是volatile能保证有序 禁止重排序
                }
            });
            threads[i].start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t + "sdfsfsfssdf");
        //最终这个t的值是1000*10=10000吗,不是的，甚至差得离谱,发生这种现象得原因也很简单.t=t+1不是原子操作。可能某个线程刚读取完t的值去做别的了，此时有另外线程读取到得t的值恰好和它一样，此时就发生了线程安全问题。
    }
}
