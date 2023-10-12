package com.docker.threadtestlib;

// 测试可重入性
/*
一个线程持有锁时，当其他线程尝试获取该锁时，会被阻塞；
而这个线程尝试获取自己持有锁时，如果成功说明该锁是可重入的，反之则不可重入
*/

/*虽然一个线程是按顺序执行的，但是可以如下面的例子那样，递归调用执行*/
public class TestSynchronized {
    public static void main(String[] args) {
        MyReent reent = new MyReent();
        new Thread(reent).start();
    }
}

class MyReent implements Runnable {
    //方式1 static final Object sLock = new Object();
    //方式2 synchronized 修饰run()、修复function();
    //方式3 ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void run() {
        //方式3 reentrantLock.lock();
        //方式1 synchronized (sLock) {
        System.out.println(Thread.currentThread().getName() + " run");
        function();
        //方式3 reentrantLock.unlock();
    }

    public void function() {
        //方式3 reentrantLock.lock();
        //方式1 synchronized (sLock) {
        System.out.println(Thread.currentThread().getName() + " function");
        //方式3 reentrantLock.unlock();
    }
}

/*
2 synchronized如何实现可重入性
  synchronized关键字经过编译之后，会在同步块的前后分别形成monitorenter
  和monitorexit这两个字节码指令。每个锁对象内部维护一个计数器，该计数器初始值为0，
  表示任何线程都可以获取该锁并执行相应的方法。根据虚拟机规范的要求，在执行monitorenter指令时，
  首先要尝试获取对象的锁。如果这个对象没被锁定，或者当前线程已经拥有了那个对象的锁，
  把锁的计数器加1，相应的，在执行monitorexit指令时会将锁计数器减1，当计数器为0时，锁就被释放。
  如果获取对象锁失败，那当前线程就要阻塞等待，直到对象锁被另外一个线程释放为止。

3 ReentrantLock如何实现可重入性
  ReentrantLock在内部使用了内部类Sync来管理锁，所以真正的获取锁是由Sync的实现类控制的。
  Sync有两个实现，分别为NonfairSync（非公平锁）和FairSync（公平锁）。Sync通过继承AQS实现，
  在AQS中维护了一个private volatile int state来计数【重入次数】，
  避免了频繁的持有释放操作带来效率问题。

  // state == 0 此时此刻没有线程持有锁
  // 如果重入了，需要操作：state=state+1
  // 则值可能为 0，1，   -- 2，3，……

  https://www.jianshu.com/p/ca7df6c1110f
* */