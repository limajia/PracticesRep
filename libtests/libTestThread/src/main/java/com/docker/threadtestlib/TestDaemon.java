package com.docker.threadtestlib;

public class TestDaemon {
    public static void main(String[] args) {
        Thread mDaemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("这是守护进程输出的");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
//        mDaemonThread.setPriority();
        mDaemonThread.setDaemon(true);//所有非守护线程退出后 jvm才会退出
        mDaemonThread.start();//
        System.out.println("这是主线程输出的");
        //    守护线程：
        //    线程对象.setDaemon(true)必须在start（）方法之前调用
        //    在守护线程中产生的新线程也是守护线程
        //    不是所有任务都可以分配给守护线程，比如读写操作、计算逻辑
        //    其他线程结束执行后，守护线程会立即结束

    }
}
