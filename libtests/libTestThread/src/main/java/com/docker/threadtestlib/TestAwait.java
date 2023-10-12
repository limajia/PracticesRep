package com.docker.threadtestlib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestAwait {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    System.out.println(" 123456");
                }
            }
        });//执行子线程任务
        try {
            es.shutdown();
            if(!es.awaitTermination(10, TimeUnit.SECONDS)){//20S
                System.out.println(" 到达指定时间，还有线程没执行完，不再等待，关闭线程池!");
//                es.shutdownNow();
            }
            System.out.println(" 不阻塞");
        } catch (Throwable e) {
            // TODO Auto-generated catch block
//            es.shutdownNow();
            e.printStackTrace();
        }
    }
}
