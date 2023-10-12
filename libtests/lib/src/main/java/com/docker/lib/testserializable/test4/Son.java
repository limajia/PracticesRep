package com.docker.lib.testserializable.test4;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Son extends Father {
    String sonName;

    public Son() { //子类必须有无参的构造函数（所以决定父类也必须有无参的构造函数）
    }

    public Son(String parentName, String sonName) {
        super(parentName);
        this.sonName = sonName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(sonName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        sonName = (String) in.readObject();
    }

    public String printString() {
        return "Son{" +
                "parentName='" + getParentName() + '\'' +
                ", sonName='" + sonName + '\'' +
                '}';
    }
}
