package com.docker.lib.testserializable.test2;

public class Father {
    String parentName;

    // 没有无参数的构造函数的话 报错com.docker.lib.testserializable.test2.Son; no valid constructor

    public Father() {
    }

    public Father(String parentName) {
        this.parentName = parentName;
    }
}
