package com.docker.lib.testjsonAndType;

import com.google.gson.Gson;

public class TestJson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        /* toJsonString,只和字段名称有关系（权限无关），get/set方法无关
        Person person = new Person("docker11",20);
        String s = gson.toJson(person);
        System.out.println(s);*/

        // 新版本gson 可以直接赋值，无需构造函数，set方法
        String personJsonString = "{\"name\":\"docker1133\",\"age\":220}";
        Person person = gson.fromJson(personJsonString, Person.class);
        System.out.println("结束！");

        //
        String typeName1 = int.class.getTypeName(); //基本类型
        System.out.println(typeName1);
        String typeName2 = Integer.class.getTypeName();//引用类型
        System.out.println(typeName2);
        String typeName3 = String.class.getTypeName();//内部定义
        System.out.println(typeName3);
        String typeNameblj = (new int[3]).getClass().getTypeName();
        System.out.println(typeNameblj);
        String typeName4 = (new String[3]).getClass().getTypeName();
        System.out.println(typeName4);
        String typeName5 = (new Object[3]).getClass().getTypeName();
        System.out.println(typeName5);
        String typeName6 = (new Object[3][4][5]).getClass().getTypeName();
        System.out.println(typeName6);
        String typeName7 = Person.class.getTypeName();
        System.out.println(typeName7);
        System.out.println("=============");
       /* 结果如下：
        int //基础类型 jdk直接定义，直接输出
        java.lang.Integer // 和Person是一样的效果
        java.lang.String
        int[]
        java.lang.String[]
        java.lang.Object[]
        java.lang.Object[][][]
        com.docker.lib.testjson.Person
        */

        String typeNamea = int.class.getName(); //基本类型
        System.out.println(typeNamea);
        String typeNameb = Integer.class.getName();//引用类型
        System.out.println(typeNameb);
        String typeNamec = String.class.getName();//内部定义
        System.out.println(typeNamec);
        String typeNameq = (new int[3]).getClass().getName();
        System.out.println(typeNameq);
        String typeNamed = (new String[3]).getClass().getName();
        System.out.println(typeNamed);
        String typeNamee = (new Object[3]).getClass().getName();
        System.out.println(typeNamee);
        String typeNamef = (new Object[3][4][5]).getClass().getName();
        System.out.println(typeNamef);
        String typeNamem = Person.class.getName();
        System.out.println(typeNamem);

        /*
        int
        java.lang.Integer
        java.lang.String
        [I
        [Ljava.lang.String; // 数组层数+类型
        [Ljava.lang.Object;
        [[[Ljava.lang.Object;
        com.docker.lib.testjson.Person
        */


        /*
        Object类的解释
        Class Object is the root of the class hierarchy.Every class has Object as a superclass.
        All objects, including arrays, implement the methods of this class.

        Type扩展Class类型
        Object-->
                Type-->
                        Class // 也是一个类（抽象了其他类，起到描述作用）， 且抽象了（基础类型，包装类型及String及其他自定义类型及数组及泛型集合的引用类型）
                        TypeVariable 类型变量，比如List<T>中的T就是参数化变量
                        WildcardType 泛型表达式类型，比如List< ? extends Number>
                        ParameterizedType 参数化类型，比如List<String>
                        GenericArrayType 泛型数组类型，比如List<String>[]、T[] // 这些Type通过反射的Filed字段中可以获得这个类（因为也是起到了描述作用）

                基本类型-->
                         8个

                引用类型-->
                         包装类型
                         String
                         其他自定义类型
                         数组
        */

        /*
        class 和 Class的区别
        class是Java中的关键字，如public class Xxx 或者 class Xxx ，在声明Java类时使用。

        而Class是一个类。我们通常认为类是对象的抽象和集合，
        注意这里：！！！！ Class就相当于是对类的抽象和集合，也可以认为对象是类的实例，类是Class的实例。
        */
    }
}

class Person {
//    public Person(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public Person(){}

    private String name;
    private Integer age;

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }*/
}
