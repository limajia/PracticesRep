package com.docker.lib.testserializable.test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 父类实现serializable 的情况
public class TestMain {
    public static void main(String[] args) {
        Son son = new Son("儿子son", "父亲father");
        System.out.println("son对象地址为=" + son.toString());
        //序列化
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/lijia19/test.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(son);
            objectOutputStream.close();
            /*¬í sr #com.docker.lib.testserializable.SonEéñVzít L sonNamet Ljava/lang/String;xr &com.docker.lib.testserializable.Father{àúîg L 
                parentNameq ~ xpt 	å¿å­sont ç¶äº²father*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        //反序列化
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/lijia19/test.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Son sonResult = (Son) objectInputStream.readObject();
            // 结果肯定不是一个对象，只是传递了数据，想想跨进程的场景。
            System.out.println("son反序列化对象地址为=" + sonResult.toString());
            System.out.println(sonResult.printString());
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
父类实现了Serializable，子类不需要实现Serializable
        相关注意事项
        a）序列化时，只对对象的状态进行保存，而不管对象的方法；
        b）当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口；
        c）当一个对象的实例变量引用其他对象，序列化该对象时也需要把引用对象进行序列化；
        d）并非所有的对象都可以序列化，至于为什么不可以，有很多原因了,比如：
            1.安全方面的原因，比如一个对象拥有private，public等field，对于一个要传输的对象，比如写到文件，或者进行rmi传输等等，在序列化进行传输的过程中，这个对象的private等域是不受保护的。
            2.资源分配方面的原因，比如socket，thread类，如果可以序列化，进行传输或者保存，也无法对他们进行重新的资源分配，而且，也是没有必要这样实现。*/
