package com.docker.lib2.serviceCenter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

// 寻址、处理请求、返回结果 任务
public class ServerTask implements Runnable {
    // 客户端请求socket
    private final Socket clientSocket;

    public ServerTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        //这里的顺序就是客户端和服务端商定好的协议。
        //ObjectInputStream读取字符串 和存储的顺序是对应的
        // 1.顺序发送数据：类名、方法名、参数类型、参数值,
        // 反序列化
        try {
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            String serviceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();
            Class<?>[] paramsTypes = (Class<?>[]) inputStream.readObject();//基本类型：数组
            Object[] paramsValues = (Object[]) inputStream.readObject();
            // 2.服务寻址
            Class serviceClass = RegisterCenter.serviceRegistry.get(serviceName);
            // 3.使用反射调用服务方法
            Method method = serviceClass.getMethod(methodName, paramsTypes);
            // 4.拿到结果 这里创建的服务Impl的实例
            Object result = method.invoke(serviceClass.newInstance(), paramsValues);
            // 5.将结果序列化，返回给客户端
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(result);
        } catch (Exception ignore) {
            System.out.println("服务器处理异常");
        } finally {
            //todo 关闭流
        }
    }
}
