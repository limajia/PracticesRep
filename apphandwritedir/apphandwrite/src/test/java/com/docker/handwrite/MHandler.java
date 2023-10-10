package com.docker.handwrite;

public class MHandler {
    private MLooper mLooper;
    private MMessageQueue mMessageQueue;

    public MHandler() {
        // 获得 成员变量
        mLooper = MLooper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Thread=" + Thread.currentThread().getName() + "has not called Looper.prepare()");
        }
        mMessageQueue = mLooper.getMessageQueue();
    }

    protected void handleMessage(MMessage message) {
        // 子类覆盖处理
    }

    public void dispatchMessage(MMessage message) {
        // 转发过来 让子类处理
        handleMessage(message);
    }

    public void sendMessage(MMessage message) {
        // 放入消息队列
        message.mTarget = this;
        mMessageQueue.enqueueMessage(message);
    }

}
