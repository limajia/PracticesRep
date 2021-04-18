package com.mlj.practicesrep.quanxiantest.bb;

public class testDefault {
    void test() {
        classb classb = new classb();
        String defaultval = classb.defaultval;
        // [default 同包其他类访问，同包子类访问]
//        理解 权限是在上一级的控制权限中 添加权限的 就是下一级会拥有上一级的权限
        // default 包权限  protected添加了其他类子类权限 public 则全部放开了权限
    }
}
