package com.google;

import java.util.concurrent.atomic.AtomicBoolean;

//https://qa.1r1g.com/sf/ask/711339541/  在java中改变类函数中的布尔值
//https://blog.csdn.net/qq_44142408/article/details/104359267 无法直接修改包装类的值
// 可用atomicBoolean 修改内部值的形式进行替换
public class TestNull {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        testAitomc(atomicBoolean);
        System.out.println(atomicBoolean.get());
    }

    private static void testAitomc(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(true);
    }

    private static void testInteger(Integer bo) {
        bo = 1000;
    }

    public static void testBoolean(Boolean outcvalye) {
        outcvalye = false;//这种是重新赋值了，不是outcvalye.的形式
    }
}
