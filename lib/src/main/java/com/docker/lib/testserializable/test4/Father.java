package com.docker.lib.testserializable.test4;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Father implements Externalizable {

    private transient String parentName;

    public Father() { //必须有无参的构造函数
    }

    public Father(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(parentName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        parentName = (String) in.readObject();
        //顺序对应上
    }
}
