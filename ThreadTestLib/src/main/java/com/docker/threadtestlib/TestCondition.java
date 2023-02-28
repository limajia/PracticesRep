package com.docker.threadtestlib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//生产和消费定义同一个condition 和定义两个condition https://blog.csdn.net/a718515028/article/details/108224749
//JAVA多线程进阶篇-Condition和Signal 实现线程顺序执行 https://blog.csdn.net/qq_33479841/article/details/127050756
public class TestCondition {

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition conditionRead = reentrantLock.newCondition();
    Condition conditionWrite = reentrantLock.newCondition(); //可以用两个condition 也可以用一个[但是一个的话，会有问题可能生产者唤起不需要的生产者，消费者唤起不需要的消费者]

    int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final TestCondition testCondition = new TestCondition();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (true){
                        testCondition.sonWrite();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while (true) {
            testCondition.mainRead();
            Thread.sleep(1000);
        }
    }

    void mainRead() throws InterruptedException {
        reentrantLock.lock();
        while (i == 0) { //while判断
            conditionWrite.signalAll();
            System.out.println("await之前执行");
            conditionRead.await();
            System.out.println("await后续执行");
        }
        i--;
        System.out.println("进行了读操作");
        conditionWrite.signalAll();
        reentrantLock.unlock();
    }

    void sonWrite() throws InterruptedException {
        reentrantLock.lock();
        while (i == 1) {
            conditionRead.signalAll();
            conditionWrite.await();
        }
        i++;
        System.out.println("进行了-写-操作");
        conditionRead.signalAll();
        reentrantLock.unlock();
    }
}
