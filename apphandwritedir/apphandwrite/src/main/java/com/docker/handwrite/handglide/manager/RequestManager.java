package com.docker.handwrite.handglide.manager;

import android.content.Context;

import com.docker.handwrite.handglide.request.BitmapRequest;
import com.docker.handwrite.handglide.diapatcher.BitmapDispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

//2.管理 (+转发 没有一个类是单独负责独立且自己功能的) 取号器 单例
// 这个是所有请求分配的管理者，   负责分配到哪个请求去处理
public class RequestManager {

    private static RequestManager requestManager;

    // 上下文
    private Context context;
    private ExecutorService executorService;

    // 管理者需要分配多少个处理器来处理请求，而处理器的多少是由手机的内存线程数量来分配的
    private BitmapDispatcher[] bitmapDispatchers; //每个dispatch 是一个线程

    // 让每个线程分发器去持有
    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();

    public static RequestManager getInstance(Context context) {
        if (requestManager == null) {
            synchronized (RequestManager.class) {
                if (requestManager == null) {
                    requestManager = new RequestManager(context);
                }
            }
        }
        return requestManager;
    }

    // 私有构造函数
    private RequestManager(Context context) {
        this.context = context;
        // 初始化线程池
        initThreadExecutor();
        // 只有一个管理者，所有在这里启动最合适
        start();
    }

    private void start() {
        // 启动之前 先停止 。。。。。  没有结束之前先启动的逻辑 -----
        // 停止所有的线程
        stop();
        startAllDispatcher();
    }

    // 处理并开始所有的线程 每个线程就是一个分发器 持有了所有请求的的集合 使用安全集合
    private void startAllDispatcher() {
        // 获取线程最大数量
        final int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        if (bitmapDispatchers.length > 0) {
            for (int i = 0; i < threadCount; i++) {
                // 线程数量开辟的请求分发去抢请求资源对象，谁抢到了，就由谁去处理
                BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue, context);
                executorService.execute(bitmapDispatcher);
                // 将每个dispatcher放到数组中，方便统一处理
                bitmapDispatchers[i] = bitmapDispatcher;
            }
        }
    }

    private void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if (!bitmapDispatcher.isInterrupted()) {
                    bitmapDispatcher.interrupt(); // 中断
                }
            }
        }
    }

    private void initThreadExecutor() {
        int size = Runtime.getRuntime().availableProcessors();
        if (size <= 0) {
            size = 1;
        }
        size *= 2; //两倍核数的线程
        executorService = Executors.newFixedThreadPool(size);
    }

    // 这里收集所有请求
    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest); // 将请求加入队列
        }
    }
}
