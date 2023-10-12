package com.docker.lib.testinitorder;

public class OtherClass {
    private String str;
    static {
        System.out.println("OtherClass-Static代码块");
    }

    {
        str = "XXXX";
        System.out.println("OtherClass-构造代码块");
    }

    public OtherClass() {
        System.out.println("OtherClass-构造方法 str = "+str);
    }
}
