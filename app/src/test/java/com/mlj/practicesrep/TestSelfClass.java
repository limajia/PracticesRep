package com.mlj.practicesrep;

import org.junit.Test;

public class TestSelfClass {
    TestSelfClass innerClass;

    // Test class should have exactly one public zero-argument constructor
    public TestSelfClass() {
        innerClass = this;
    }

    @Test
    public void addition_isCorrect() {
        TestSelfClass testSelfClassOuter = new TestSelfClass();
        System.out.println("outer= " + testSelfClassOuter.toString());
        System.out.println("inner= " + testSelfClassOuter.innerClass.toString());
        // 两个地址是一样的 区分开 类和对象 不会有死循环的问题
    }
}
