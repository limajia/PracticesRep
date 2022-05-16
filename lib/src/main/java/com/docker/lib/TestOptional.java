package com.docker.lib;

public class TestOptional {
    // main中不能创建一个非静态内部类对象，因为非静态内部类对象需要先创建外部类的对象
    public static void main(String[] args) {
        //1.TestInner t = new TestInner(); //这是不可以的
        TestOptional testOptional = new TestOptional();
        TestInner testInner = testOptional.new TestInner();
        TestOptional.TestInner testInner1 = testOptional.new TestInner();
        TestOptional.TestInner testInner2 = new TestOptional().new TestInner();
        // --以上是内部类的对象的创建方法---

        //2.
        OptionAL<String> str = new OptionAL<>("str");
        OptionAL<Object> objectOptionAL = new OptionAL<>();//注意这里 构造方法不穿参数，默认就是object
        OptionAL<String> str1 = OptionAL.of("str");
    }


    //1.
    class TestInner {
    }

    public static class OptionAL<T> {
        T value;

        public OptionAL() {
            value = null;
        }

        public OptionAL(T t) {
            value = t;
        }

        // 泛型方法声明在返回值前面进行声明
        public static <OP> OptionAL<OP> of(OP op) {
            return new OptionAL<>(op);
        }
    }
}

