package com.docker.threadtestlib;

// java 这里只体现了等待池 一个对象锁 Synchronize+wait+notify 只能处理两个线程的交互逻辑
// 假如多个读的进去了，都从wait处执行。 需要建立多个锁 来进行控制
// Synchronize代码块 使得可以原子性。 wait+notify 使线程通信 调用顺序及次数控制
// 需要注意的是一把锁对应一个线程池。即一个锁 用来管理一组线程
public class TestSynchronizedExt {
    public static void main(String[] args) {
        TargetExt t = new TargetExt();

        Thread t1 = new IncreaseExt(t);
        t1.setName("Increase1：");

        Thread t2 = new DecreaseExt(t);
        t2.setName("Decrease1：");

        t1.start();

        t2.start();
    }

}

class TargetExt {
    public synchronized void increase() {
        System.out.println(Thread.currentThread().getName() + ":函数前");
        try {
            System.out.println("wait前执行");
            wait();
            System.out.println("wait后执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("increase完成");
    }

    public synchronized void decrease() {
        notify();
    }
}

class IncreaseExt extends Thread {
    private TargetExt t;

    public IncreaseExt(TargetExt t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.increase();
    }
}

class DecreaseExt extends Thread {

    private TargetExt t;

    public DecreaseExt(TargetExt t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始唤醒");
        t.decrease();
    }
}

//Increase1：:函数前
//wait前执行
//开始唤醒
//wait后执行  唤醒后从这里获取锁后执行
//increase完成

//在Java对象中，有两种池
//锁池-----------------------synchronized
//等待池---------------------wait(),notify(),notifyAll(),调用之前必须后的锁(monitor)的拥有权。
//        如果一个线程调用了某个对象的wait方法，那么该线程进入到该对象的等待池中(并且已经将锁释放)，
//        如果未来的某一时刻，另外一个线程调用了相同对象的notify方法或者notifyAll方法，
//        那么该等待池中的线程就会被唤起，然后进入到对象的锁池里面去获得该对象的锁，
//        如果获得锁成功后，那么该线程就会沿着wait方法之后的路径继续执行。注意是沿着wait方法之后

//在java中，每一个对象都有两个池，锁(monitor)池和等待池
//wait() ,notifyAll(),notify() 三个方法都是Object类中的方法.
//锁池:假设线程A已经拥有了某个对象(注意:不是类)的锁，而其它的线程想要调用这个对象
//        的某个synchronized方法(或者synchronized块)，因为这些线程在进入对象的
//        synchronized方法以前必须先得到该对象的锁的拥有权，可是该对象的锁目前正被线程A拥有，
//        因此这些线程就进入了该对象的锁池中。
//等待池:假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁
//        (由于wait()方法必须出如今synchronized中，这样天然在执行wait()方法以前线程A就已经拥有了该对象的锁)，
//        同时线程A就进入到了该对象的等待池中。若是另外的一个线程调用了相同对象的notifyAll()方法，
//        那么处于该对象的等待池中的线程就会所有进入该对象的锁池中，准备争夺锁的拥有权。
//        若是另外的一个线程调用了相同对象的notify()方法，
//        那么仅仅有一个处于该对象的等待池中的线程(随机)会进入该对象的锁池.
