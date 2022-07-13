package com.docker.lib2.service;

// 查询服务实现类
public class QueryOrderImpl implements IQueryOrder {
    @Override
    public String query(String orderId) {
        //todo 具体的查询业务逻辑
        System.out.println("开始查询orderId");
        return "查询结果";
    }
}
