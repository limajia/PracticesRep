package com.docker.lib.testinitorder;


public class TestMain {
    private OtherClass otherClass = new OtherClass();
    void doSome(){
        System.out.println("执行doSome()");
    }
    // main方法不同于类中的static 不会加载类
    public static void main(String[] args) {
        // otherClass初始化坑定在前
        new TestMain().doSome();
    }
}
