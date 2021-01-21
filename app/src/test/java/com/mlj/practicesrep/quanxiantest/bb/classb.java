package com.mlj.practicesrep.quanxiantest.bb;

import com.mlj.practicesrep.quanxiantest.aa.classa;

public class classb {
    private String privateval;
    String defaultval;
    protected String protectedtval;

    void test(){
//        classa.ddd //    static 跨包访问 只能访问public修饰的
    }
}

class mmm {
    classb dddd;
    String a = dddd.protectedtval;// protectd 是同包的其他类访问  和 不同包的子类访问
}