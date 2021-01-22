package com.mlj.practicesrep.exceptiontest;

import org.junit.Test;

public class TestMain {
    @Test
    public void testException() {
        try {
            fun1();
        } catch (Exception e) {
            System.out.println("testException 抓住了"); // 外层方法 catch 是可以捕获住内存的异常的
        }
    }

    void fun1() {
//        try {
        fun2();
//        } catch (Exception e) {
//            System.out.println("fun1抓住了");
//        }
    }

    void fun2() {
//        try {

       /* int c = 0;
        int a = 10;*/ //这里外层可以捕捉到异常

//        } catch (Exception e) {
//            System.out.println("fun2抓住了");
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                int c = 0;
                int a = 10 / 0;
                // 这里是不可以捕捉到的
                // Exception in thread "Thread-0" java.lang.ArithmeticException: / by zero
                //	at com.mlj.practicesrep.exceptiontest.TestMain$1.run(TestMain.java:38)
                //	at java.lang.Thread.run(Thread.java:748)
            }
        }).start();
    }
}
