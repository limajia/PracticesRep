package com.docker.threadtestlib;

public class TestThreadGroup {
    private static Object sLock = new Object();

    /*Java中用ThreadGroup来表示线程组，我们可以使用线程组对线程进行批量控制。
    每个Thread必然存在于一个ThreadGroup中，Thread不能独立于ThreadGroup存在。执行main()方法线程的名字是main，
    如果在new Thread 时没有显式指定，那么默认父线程(当前执行new Thread的线程)线程组设置为自己的线程组。*/
    public static void main(String[] args) {
        String mainFunThreadGroupName = Thread.currentThread().getThreadGroup().getName();
        System.out.println("mainFunThreadGroupName = " + mainFunThreadGroupName);//main
        String mainFunThreadName = Thread.currentThread().getName();
        System.out.println("mainFunThreadName = " + mainFunThreadName); //main

        /*ThreadGroup是一个标准的向下引用的树状结构，这样设计的原因是防止“上级”线程被“下级”线程引用而无法有效的被GC回收。【如图 线程组树状结构.webp】
        这个上级和下级的层次关系；和Foundation和高级模块的层次关系是相反的。*/

       /*java中线程的优先级默认值是5，可指定范围为：1~10。在这里 java知识给操作系统一个优先级的参考值，线程最终在操作系统的优先级是多少还是由操作系统决定，也就是cpu对线程的调度，不完全是按优先级来执行的。
        使用方法 setPriority() 来设定线程的优先级。*/

        //1.
        Thread myThread1 = new Thread("我的线程名称1") {
            @Override
            public void run() {
                super.run();
                String myThreadGroupName = Thread.currentThread().getThreadGroup().getName();
                System.out.println("myThreadGroupName = " + myThreadGroupName);//main
                String myThreadName = Thread.currentThread().getName();
                System.out.println("myThreadName = " + myThreadName);//我的线程名称1
            }
        };
        myThread1.setPriority(1);
        myThread1.start();

        //2.
        Thread myThread2 = new Thread() {
            @Override
            public void run() {
                super.run();
                String myThreadGroupName = Thread.currentThread().getThreadGroup().getName();
                System.out.println("myThreadGroupName2 = " + myThreadGroupName);//Thread-0, Thread-1, Thread-2 …… 依次往后加数值
                String myThreadName = Thread.currentThread().getName();
                System.out.println("myThreadName2 = " + myThreadName);//我的线程名称1
            }
        };
        myThread2.setPriority(1);
        myThread2.start();

        //3.
        Thread myThread3 = new Thread() { // 当线程组中的所有线程执行完毕之后，才会都结束
            @Override
            public void run() {
                super.run();
                /*运行下面例子 进行注释 System.out.println("myThread3 开始睡眠");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("myThread3 结束睡眠");*/
            }
        };
        myThread3.start();
        System.out.println("main-测试主线程结束了");

        //4. 线程组中的线程崩溃后，不影响main主线程
        // 但是主线程异常结束后，（会等一会其他线程），由于main finished with non-zero exit value 1 而崩溃，如果是新建的一个线程组呢？？ 5.试试
        Thread myThread4 = new Thread() {
            @Override
            public void run() {
                super.run();
                /* 运行下面例子 进行注释 try {
                    System.out.println("myThread4 线程5秒后抛出异常");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
                throw new RuntimeException("抛出异常");*/
            }
        };
        myThread4.start();
        /* 运行下面例子 进行注释
        try {
            System.out.println("main 线程开始睡眠");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("main 线程睡眠结束");
         */

        /*myThread4 线程5秒后抛出异常
        myThreadName2 = Thread-0
        myThread3 开始睡眠
        myThread3 结束睡眠
        Exception in thread "Thread-2" java.lang.RuntimeException: 抛出异常
        at com.docker.threadtestlib.TestThreadGroup$4.run(TestThreadGroup.java:76)
        main 线程睡眠结束*/

        //5. 主线程也会等这个组内的线程执行完毕之后，再退出
        // 但是主线程异常结束后，由于main finished with non-zero exit value 1 而崩溃.
        ThreadGroup myThreadGroup1 = new ThreadGroup("myThreadGroup") {
            // 统一处理线程组中的异常
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                super.uncaughtException(t, e);
            }
        };
        /*myThreadGroup1.setDaemon();
        myThreadGroup1.setMaxPriority();
        线程组可以统一设置 里面所有的线程的一些属性
        */
        Thread myThread5 = new Thread(myThreadGroup1, "新线程组线程1") {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String myThreadGroupName = Thread.currentThread().getThreadGroup().getName();
                System.out.println("myThreadGroupNameNew1 = " + myThreadGroupName);//myThreadGroup
                String myThreadName = Thread.currentThread().getName();
                System.out.println("myThreadNameNew1 = " + myThreadName);//新线程组线程1
            }
        };
        myThread5.start();

        //6.测试一下Daemon线程中开Daemon线程，也是Daemo线程
        Thread myThread6 = new Thread(Thread.currentThread().getThreadGroup(), "新线程组daemon线程") {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(10000); //线程组中另外的一个线程休眠5000后，退出，这里不会输出，已经结束了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("新线程组daemon线程 输出了");
            }
        };
        myThread6.setDaemon(true);
        myThread6.start();
    }
}
