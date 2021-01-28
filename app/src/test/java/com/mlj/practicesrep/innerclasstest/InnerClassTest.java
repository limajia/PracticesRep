package com.mlj.practicesrep.innerclasstest;

import org.junit.Test;

// 静态内部类
public class InnerClassTest {
    @Test
    public void testMain() {

    }

    private int outValue;

    public void outerFun() {
    }

    private static int outStaticValue;

    public static void outStaticFun() {
    }

    private static class InnerClass {

        private static short ss;

        void testA() {

        }

        public static void testB() {

        }
        // 不可以【访问】外部的非静态的成员
        // private int outValue;
        // public void outerFun(){}


        // 可以【访问】外部的静态的成员
        // private static int outStaticValue;
        // public static void outStaticFun(){}

        // 可以【创建】静态和非静态的成员   还有一个是创建外部类对象 不等同 创建成员

    }
}

