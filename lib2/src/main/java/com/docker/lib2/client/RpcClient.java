package com.docker.lib2.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

// 远端服务，处理请求、序列化等
public class RpcClient {
    //注意: serviceInterface 必须和服务器端的一至，路径得相同（client使用方，需要拷贝一份同路径的接口文件。类似于aidl文件的使用）
    public static <T> T getRemoteProxyObj(Class<?> serviceInterface) throws Exception {
        // 使用动态代理获取 远程服务
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8888);
        // 将本地的接口调用转换成JDK的动态代理，在_动态代理中实现接口的远程调用_
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new ProxyDoHandler(serviceInterface, socketAddress));
    }

    // 动态代理InvocationHandler类  处理请求封装和处理返回的过程
    // 被代理类不是直接传入handler的，而是通过创建socket执行过程，在Method中进行调用
    public static class ProxyDoHandler implements InvocationHandler {

        private final Class<?> serviceInterface; //获取服务名
        private final InetSocketAddress socketAddress;//创建socket

        public ProxyDoHandler(Class<?> serviceInterface, InetSocketAddress socketAddress) {
            this.serviceInterface = serviceInterface;
            this.socketAddress = socketAddress;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                //网路增强部分
                Socket socket = null;//创建socket
                socket.connect(socketAddress);//连接远程服务
                //往远端 发送数据，按照顺序发送数据：类名、方法名、参数类型、参数值
                // 1.拿到输出的流
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                // 2. 发送 调用方法的 类名,使用UTF-8避免乱码
                objectOutputStream.writeUTF(serviceInterface.getName());
                // 3.发送 方法名
                objectOutputStream.writeUTF(method.getName());
                // 4.发送 参数类型,使用Object
                objectOutputStream.writeObject(method.getParameterTypes());
                //5 .发送 参数的值,使用UTF-8避免乱码
                objectOutputStream.writeObject(args);
                // 6.刷新缓冲区，使得数据立马发送
                objectOutputStream.flush();
                // 7.立马拿到远程执行的结果
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                // 8.调用的细节打印出来
                System.out.println("远程调用成功！" + serviceInterface.getName());
                //最后要网络的请求返回给返回
                return objectInputStream.readObject();
            } finally {
                //todo 关闭流
            }
        }
    }
}
