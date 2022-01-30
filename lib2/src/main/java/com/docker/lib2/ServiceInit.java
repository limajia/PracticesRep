package com.docker.lib2;

import com.docker.lib2.service.IQueryOrder;
import com.docker.lib2.service.QueryOrderImpl;
import com.docker.lib2.serviceCenter.RegisterCenter;

import java.io.IOException;

// 服务端启动程序
public class ServiceInit {
    public static void main(String[] args) {
        try {
            // 创建服务中心
            RegisterCenter registerCenter = new RegisterCenter(8888);
            // 向服务中心注册服务
            registerCenter.register(IQueryOrder.class, QueryOrderImpl.class);//demo通过反射进行的实例的创建
            registerCenter.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
