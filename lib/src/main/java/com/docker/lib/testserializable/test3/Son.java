package com.docker.lib.testserializable.test3;

public class Son extends Father {
    String sonName;

    public Son(String parentName, String sonName) {
        super(parentName);
        this.sonName = sonName;
    }

    public String printString() {
        return "Son{" +
                "parentName='" + getParentName() + '\'' +
                "parentAlias='" + parentAlias + '\'' +
                ", sonName='" + sonName + '\'' +
                '}';
    }
}
