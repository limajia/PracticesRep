package com;

import java.util.ArrayList;
import java.util.List;

public class ClassEquals {
    public static void main(String[] args) {
        List<String> aaa = new ArrayList<>();
        ArrayList<Integer> iList = new ArrayList<>();
        System.out.println(aaa.getClass() == iList.getClass());
        System.out.println(aaa.getClass().getName());
        System.out.println(iList.getClass().getName());
//        System.out.println(List.class == ArrayList.class); 不能比较
        System.out.println(List.class.equals(ArrayList.class));

        Class<?> listClass = List.class;
        Class<?> arrayListClass = ArrayList.class;

        if (listClass == arrayListClass) {
            System.out.println("List.class and ArrayList.class reference the same class.");
        } else {
            System.out.println("List.class and ArrayList.class reference different classes.");
        }
        //我理解你的疑问。虽然在概念上，`List.class`和`ArrayList.class`都是`Class`对象，但它们实际上是不同的`Class`实例，因为它们代表了不同的类。因此，使用`==`直接比较它们不会产生预期的结果。
        //
        //`==`运算符用于比较两个对象的引用是否相等，而不是比较对象的内容。在Java中，每个类都有一个唯一的`Class`对象，因此不同的类具有不同的`Class`实例。
        //
        //如果要比较两个类是否是同一类型，可以使用`Class`类的`equals()`方法。例如：
        //
        //```java
        //Class<?> listClass = List.class;
        //Class<?> arrayListClass = ArrayList.class;
        //
        //if (listClass.equals(arrayListClass)) {
        //    System.out.println("List.class and ArrayList.class represent the same class.");
        //} else {
        //    System.out.println("List.class and ArrayList.class represent different classes.");
        //}
        //```
        //
        //这样会根据类的全限定名和类加载器进行比较，从而判断它们是否代表同一类型的类。在这个例子中，输出应该是 "List.class and ArrayList.class represent different classes."。
    }
}
