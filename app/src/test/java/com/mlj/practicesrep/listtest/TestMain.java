package com.mlj.practicesrep.listtest;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class TestMain {
    @Test
    public void abcd() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        //1.
        //  List<Object> integers1 = (List<Object>) integers; 注意：不能这样进行内部泛型的转换

        // 可以直接转换  但是初始【实例化】是子类new ArrayList  是可以通过父类强转为子类的
        ArrayList<Integer> integers1 = (ArrayList<Integer>) integers;
        for (Integer integer : integers1) {
            System.out.println(integer);
        }

        //2.
        Object[] objects1 = integers1.toArray(); //重新生成了初始【实例化】 Object[] 强转为Integer[] 会报错。
        Integer[] integers2 = integers1.toArray(new Integer[0]); //按照传递的类型 创建了对应类型的数组
        /*
        把子類的值轉回子類才是合法的
        把Object[]的值cast成String[], 這個行為和(Integer)(new Object())同類

        String[] y = (String[]) list.toArray()
        這樣依舊是不合法的。
        因為它返回的是Object[] 【初始的就是object】不是String[]

        Object[] toArray()对应的是      (T[]) new Object[newLength]，这里T就是Object。
        <T> T[] toArray(T[] a)对应的是  (T[]) Array.newInstance(newType.getComponentType(), newLength)，这里T就是String。
        */

        //3.
        Object[] objects = new Object[10];
        String[] strings = new String[10];

        boolean b = strings instanceof Object[];
        System.out.println("strings instanceof Object[]=" + (strings instanceof Object[])); //true
        //数组类型和普通的对象的类型的比较是一样的

        ArrayList<String> stringArrayList = new ArrayList<>();
        ArrayList<Integer> integerArrayList = new ArrayList<>();

        System.out.println("stringArrayList.getClass()=" + stringArrayList.getClass());
        System.out.println("integerArrayList.getClass()=" + integerArrayList.getClass());

        //stringArrayList.getClass()=class java.util.ArrayList
        //integerArrayList.getClass()=class java.util.ArrayList
        //泛型内部都是object进行强转实现的，得到的类型都是集合类型  有集合类的子类 没有泛型的子类父类一说

        //4.
        // Java中的8个基本类型 在哪里 和包装类型【9个 VOID】的区别

        /*
        基本类型的定义

        这些内容, 属于 java 语言规范
        定义的位置, 应该是在 jvm 里
        有很多开源的 jvm 实现, 比如 hotspot
        定义的语言, 通常是 C++
        其他

        除了 基本数据类型 之外, 也有其他东西, 定义在 jvm 里
        运算符
        关键字
        数组
        其他
        */
        // These are created by the Java
        //     * Virtual Machine, and have the same names as the primitive types that
        //     * they represent, namely {@code boolean}, {@code byte},
        //     * {@code char}, {@code short}, {@code int},
        //     * {@code long}, {@code float}, and {@code double}.
        System.out.println("boolean.class == Boolean.TYPE 结果=" + (boolean.class == Boolean.TYPE));//true
        System.out.println("boolean.class.isPrimitive() 结果=" + (boolean.class.isPrimitive())); // true
        System.out.println("Boolean.class.isPrimitive(); 结果=" + (Boolean.class.isPrimitive())); // false

        // 5. Class的强转
        Class<Son> sonClass = Son.class;
        Class<Father> tesClass = (Class) sonClass; //都是伪泛型 会被擦除的 单纯的接收参数Class<Father> tesClass 不能决定是哪个类型 还是son的Class //FIXME 在声明一次不能对泛型进行强转。么有泛型子类
        System.out.println("tesClass.getClass()=" + tesClass.getClass()); //class java.lang.Class
        Field[] declaredFields = tesClass.getDeclaredFields();
        System.out.println("declaredFields.length=" + declaredFields.length); //1 如果类在内部非静态声明值为2  有一个外部类的引用
        Field[] declaredFields1 = sonClass.getDeclaredFields();
        System.out.println("declaredFields1.length=" + declaredFields1.length);//1 如果类在内部非静态声明值为2  有一个外部类的引用
        Field[] declaredFields2 = Father.class.getDeclaredFields();
        System.out.println("declaredFields2.length=" + declaredFields2.length);//1 如果类在内部非静态声明值为2  有一个外部类的引用

        // public class Test {
        //    public static void main(String[] args) {
        //        Object o = new int[]{16};
        //        System.out.println(int[].class.cast(o)[0]);
        //    }
        //}
        //核心之处就在于xxx.class.cast(xxx)，xxx代表的就是未知变量。上面的这段代码先将一个整型数组赋给object声明的变量
        //然后将这个对象再通过int[].class转回int数组类型


        // 引用：
        // getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
        // getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
    }

}

class Father {
    public int father;
}

class Son extends Father {
    public int son;
}