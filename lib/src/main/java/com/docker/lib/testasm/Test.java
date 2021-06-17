package com.docker.lib.testasm;

public class Test {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        System.out.println("Hello In Test01!");
    }

    public static int testReturn() {
        int a = 100;
        int b = 200;
        return a + b;
        // 测试在return之前 插入test01()
    }
}