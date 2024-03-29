package com.docker.lib.testserializable.test4;

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

//com.docker.lib.testserializable.test4.Son; no valid constructor
//理解了这句话的真谛了 不需要序列化 需要提供一个无参数的构造方法
//serializable 和 Externalizable