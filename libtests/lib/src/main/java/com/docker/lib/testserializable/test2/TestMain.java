package com.docker.lib.testserializable.test2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 子类实现serializable 的情况
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
            //  parentName这个字段没有序列化到里面去
//            ¬í sr )com.docker.lib.testserializable.test2.SonRâM âA L sonNamet Ljava/lang/String;xpt ç¶äº²father
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
            //Son{parentName='null', sonName='父亲father'}
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//父类未实现Serializable，子类实现了，序列化子类实例的时候，父类的属性值丢失。
/*
要为一个没有实现Serializable接口的父类，编写一个能够序列化的子类要做两件事情：
        　　其一、父类要有一个无参的constructor； 会在运行时候检测【想：父类不需要序列化,所以留一个空参构造方法用来初始化】
        　　其二、子类要负责序列化（反序列化）父类的域。 */

/*
域就是范围的意思
        例｛｝之间，称为一块域，用来描述变量适用范围，全局变量的域是整个类，局部变量只适用于他所在的｛｝之间。
        类的域就是指 class 类名｛｝括号之间的范围
*/
