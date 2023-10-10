package com.docker.handwrite.handeventbus.core;

import java.lang.reflect.Method;

//注册类中的注册方法 信息类
public class SubscribleMethod {
    // 注册的方法
    private Method method;

    // 线程类型
    private DoThreadMode threadMode;

    // 参数类型 这里参数是一个对象类型
    private Class<?> eventType;

    public SubscribleMethod(Method method, DoThreadMode threadMode, Class<?> eventType) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public DoThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(DoThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }
}
