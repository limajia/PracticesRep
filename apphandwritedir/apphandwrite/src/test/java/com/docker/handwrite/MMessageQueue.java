package com.docker.handwrite;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MMessageQueue {

    // 阻塞队列
    private BlockingQueue<MMessage> mMessageQueue = new ArrayBlockingQueue<>(50);

    //消息入队
    public void enqueueMessage(MMessage message) {
        try {
            mMessageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //取消息
    public MMessage next() {
        try {
            // 看看会不会阻塞当前进程
            return mMessageQueue.poll(3, TimeUnit.SECONDS);
//          return mMessageQueue.take(); // 完全阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
