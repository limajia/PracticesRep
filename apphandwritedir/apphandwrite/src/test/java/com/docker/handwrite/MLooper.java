package com.docker.handwrite;

// looper 和 线程关系绑定 系统Looper就类似一个工具类  私有的构造方法 在perpaer的时候创建对象
public class MLooper {
    // 一个Looper 只有一个消息队列
    private MMessageQueue mMessageQueue;

    // 线程存储器
    private static final ThreadLocal<MLooper> mThreadLocal = new ThreadLocal<>();

    private MLooper() {
        mMessageQueue = new MMessageQueue();
    }

    // looper 和线程进行绑定
    public static void prepare() {
        if (mThreadLocal.get() != null) {
            //一个线程只能创建一个Looper
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        mThreadLocal.set(new MLooper());
    }

    public MMessageQueue getMessageQueue() {
        return mMessageQueue;
    }

    public static void loop() {
        MLooper mLooper = myLooper();
        MMessageQueue queue = mLooper.mMessageQueue;
        while (true) { // 自己在main线程中操作 这里会 全部阻塞主线程 向下执行 如果使用管道机制的话
            System.out.println("kaishi" + System.currentTimeMillis());
            MMessage next = queue.next();
            System.out.println("jieshu" + System.currentTimeMillis());
            if (next != null) {
                // 发送给handler
                next.mTarget.dispatchMessage(next);
            }
        }
    }

    /**
     * 获取指定线程的Looper实例
     */
    public static MLooper myLooper() {
        return mThreadLocal.get();
    }
}
