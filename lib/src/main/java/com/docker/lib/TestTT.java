package com.docker.lib;
// 测试继承的疑惑，子类会不会有父类的东西
public class TestTT {
}

abstract class Parent {
    public abstract void testA();
    private void testB(){};
    public void testC(){};
}

class Son extends Parent {

    @Override
    public void testA() {

    }
}

//javap 默认不显示private属性 javap -v **   使用javap -private **
// 父类的抽象的

/* 反编译 父类中的东西不会 拷贝到子类中去        //ACC_PUBLIC, ACC_ABSTRACT 都是修饰属性
[:...rc/main/java/com/docker/lib]$ javap -private Parent.class                                                       (main✱)
        Compiled from "TestTT.java"
abstract class com.docker.lib.Parent {
        com.docker.lib.Parent();
        public abstract void testA();
        private void testB();
        public void testC();
        }

[:...rc/main/java/com/docker/lib]$ javap -private Son.class                                                          (main✱)
        Compiled from "TestTT.java"
        class com.docker.lib.Son extends com.docker.lib.Parent {
        com.docker.lib.Son();
        public void testA();
        }*/


// 重写的规则 两同两小一大 （子类处理的更精细，权限更大，这才是重写的目的）
// 参数、方法相同  返回类型、throws异常 更小更具体  权限更大
// 关于两个更小的理解是 为了满足多态的使用不会出问题，如Parent parent = new Son();
// 当使用parent调用方法的时候，能够使用更大的类型接收返回值（如继承关系的对象），使用更大的异常类型进行异常捕捉。