package com.docker.lib.testserializable.test3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//transient 使用细节
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Son.parentAlias = "反序列化前修改了"; //不修改的话，还是之前jvm中的值。
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
// 注意的地方。
//1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
//
//        2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
//
//        3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
// 3 第三点可能有些人很迷惑，因为发现在User类中的username字段前加上static关键字后，程序运行结果依然不变。
// 若是同一个进程的话，static变量的值，是点前JVM中对应的static变量的值，不是反序列化出来的。

//transient使用细节——被transient关键字修饰的变量真的不能被序列化吗？
//transient是让系统自己处理，不序列化，但是Externalizable接口，是让人工进行自己处理。所以transient修饰的及static的还是可以序列化的

