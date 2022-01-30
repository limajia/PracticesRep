package com.docker.lib2.serviceCenter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 服务注册中心(使用ServerSocket为中心指定一个port端口)
public class RegisterCenter {
    // 服务存储容器 这里一个class就是一个服务
    public static final HashMap<String, Class> serviceRegistry = new HashMap<>();

    //中心配置 port端口
    private int port;
    public RegisterCenter(int port) {
        this.port = port;
    }

    //开一个处理客户端请求接收、寻址的任务的线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    //注册服务(此demo 服务中心和服务在同一个内存区域，可以直接调用，否则也需要通过socket进行注册)
    public void register(Class serviceInterface, Class interfaceImpl) {
        serviceRegistry.put(serviceInterface.getName(), interfaceImpl);//全限定name/实现类
    }

    // 启动服务注册中心，监听客户端的请求，并进行Service寻址
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        // 绑定本机的 port端口
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        serverSocket.bind(socketAddress);
        while (true) {
            //阻塞监听请求
            Socket socket = serverSocket.accept();
            //需要放到工作线程中去 寻址服务任务
            ServerTask serverTask = new ServerTask(socket);
            executorService.execute(serverTask);
        }
        //todo 异常的话，注意处理serverSocket.close;
    }
}
