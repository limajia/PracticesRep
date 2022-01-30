package com.docker.lib2;

import com.docker.lib2.client.RpcClient;
import com.docker.lib2.service.IQueryOrder;

public class TestRPC {
    public static void main(String[] args) {
        //1.serviceinit的开始在另一端开始
        //2.client调用
        try {
            IQueryOrder queryOrder = RpcClient.getRemoteProxyObj(IQueryOrder.class);//注意这里的接口，如果服务器和客户端是分离的话，需要建立一个同包名、同类名的类，用来和服务端进行匹配。
            queryOrder.query("queryId");// 进行远程调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
