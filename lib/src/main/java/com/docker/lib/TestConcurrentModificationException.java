package com.docker.lib;

import java.util.ArrayList;
import java.util.Iterator;

public class TestConcurrentModificationException {
    public static void main(String[] args) {

        //（没有什么所谓的多线程操作）Java中的Iterator迭代器详解 https://blog.csdn.net/qq_35427589/article/details/124246866
        // https://www.cnblogs.com/zhiwenxi/p/11432065.html
        // https://blog.csdn.net/java0825/article/details/106692947
        //1.在使用迭代器的时候，时长会遇到 ConcurrentModificationException（并发修改异常）

        // 就是不能在集合访问的时候，直接删除。还是因为 modCount的值和预期的修改次数不一致。没有什么所谓的多线程操作。
        // 使用迭代器的remove会同时修改modCount的值，所以不会抛出异常
        ArrayList<String> list = new ArrayList<>();
        list.add("JAVA");
        list.add("Python");
        list.add("PHP");
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            if (it.next().equals("PHP")) {
                list.add("我全都要");
            }
        }

    }
}
