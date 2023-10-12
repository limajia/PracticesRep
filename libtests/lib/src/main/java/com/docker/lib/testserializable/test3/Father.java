package com.docker.lib.testserializable.test3;

import java.io.Serializable;

public class Father implements Serializable {

    private transient String parentName;

    protected static String parentAlias = "daidi戴迪";

    public Father(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }
}
