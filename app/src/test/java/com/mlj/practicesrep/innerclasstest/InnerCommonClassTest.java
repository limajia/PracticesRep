package com.mlj.practicesrep.innerclasstest;

import org.junit.Test;

// 非静态内部类
public class InnerCommonClassTest {
    @Test
    public void testMain() {

    }

    private int outValue;

    public void outerFun() {
    }

    private static int outStaticValue;

    public static void outStaticFun() {
    }

    private class InnerClass {

//        private stati c short ss;

        void testA() {

        }

//        public static void testB() {}


        // 不可以【创建】静态的成员

        // 可以【访问】外部的静态和非静态成员
    }
}

