package com.mlj.practicesrep;

import org.junit.Test;

import java.util.SortedMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    @Test
    public void testMain() {

        Callable<String> myCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "结束了";
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start(); // 开启一个Worker线程

        for (int i = 10000; i > 0; i--) {
            i--;
            if (i==1){
                Thread.currentThread().interrupt();
            }
        }

        try {
            String s = futureTask.get(); //阻塞了外面所在的线程
            System.out.println(s);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main=--------");
        /*输出结果为*/
        //结束了
        //main=--------
    }

}
