package com.docker.lib.testserializable.test2;

import java.io.Serializable;

public class Son extends Father implements Serializable {
    String sonName;

    public Son(String parentName, String sonName) {
        super(parentName);
        this.sonName = sonName;
    }

    public String printString() {
        return "Son{" +
                "parentName='" + parentName + '\'' +
                ", sonName='" + sonName + '\'' +
                '}';
    }
}
