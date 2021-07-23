package com.docker.threadtestlib;

public class TestVolatile {
    static boolean isEnd = false;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isEnd) {
                }
                System.out.println("结束了");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isEnd = true;
        System.out.println("主线程结束了");
    }
}
// 无法结束 isEnd的修改不能所有操作线程可见
// volatile ObjectA a; 修饰成员变量 不能是局部
// volatile修饰非基础类型，指的是每次刷新对象的引用。非基础数据类型，建议用AtomiXX代替volatile。
