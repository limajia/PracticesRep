package com.docker.threadtestlib;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestGet {

    public static void main(String[] args) throws TimeoutException {
        Callable<Integer> callable1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return new Random().nextInt(100);
            }
        };
        Callable<Integer> callable2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future1 = new FutureTask<>(callable1);
        FutureTask<Integer> future2 = new FutureTask<>(callable2);

//            new Thread(future1).start();
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(future1);
        service.submit(future2);
        try {
//                Thread.sleep(1000);// 可能做一些事情
            try {
                Integer integer = future1.get(3000, TimeUnit.MILLISECONDS);
                System.out.println(integer);
            } catch (TimeoutException e) {
                System.out.println("time out");
            }
            System.out.println(future2.get(4000, TimeUnit.MILLISECONDS));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
