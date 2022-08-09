package com.docker.lib;

import java.util.Collections;
import java.util.List;

public class TestEmptyList {
    public static void main(String[] args) {
        List<Object> objects = Collections.emptyList();//immutable
        //objects = new ArrayList<>();//对拷贝出来的List的引用重新赋值了，不是里面final修饰的那个地址，不是以.的形式调用的
        try {
            objects.add("fffff");
        } catch (Exception e) {
            e.printStackTrace();
           // java.lang.UnsupportedOperationException，是不支持添加的
        }
        System.out.println("结束了");
    }
}
