package com;

public class GenericTest {
    public static void main(String[] args) {
        //==============================1================================
        GenericClass genericClass = new GenericClass();
        genericClass.setValue(1212);
        //先调用 void setValue(int intValue)
        //没有的话 会调用void setValue(Object value)

        //==============================2================================
        MyList<Integer> intList = new MyList<>(42);
        MyList<String> stringList = new MyList<>("Hello");

        // 编译器会生成桥接方法，确保类型安全
        Object intValue = intList.getValue();
        Object stringValue = stringList.getValue();

        System.out.println("Integer value: " + intValue);
        System.out.println("String value: " + stringValue);
    }

    //==============================1================================
    public static class GenericClass {

        private Object value;
        private int intValue;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public void setValue(int intValue) {
            this.intValue = intValue;
        }
    }

    //==============================2================================

    static class MyList<T> {
        private T value;

        public MyList(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}
