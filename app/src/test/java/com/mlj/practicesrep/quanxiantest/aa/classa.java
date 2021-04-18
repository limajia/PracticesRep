package com.mlj.practicesrep.quanxiantest.aa;

import com.mlj.practicesrep.quanxiantest.bb.classb;

// 外部类只能有 default 和public的访问权限
// 内部类可以四个权限和static修饰【看成类的一个方法 好理解】
public class classa {

    //1.外部类修饰符
    private classb assss; //如果classb 默认修饰 跨包了 则不可以访问

    private static int aaa = 100;
    static int bbb = 100;
    protected static int ccc = 100;
    public static int ddd = 100;

    void fun() {
        // assss.privateval // 不可以访问
        // assss.defaultval // 挎包了 不可以访问  挎包访问就是通过对象 或 静态方法 [default 同包其他类访问，同包子类访问]
        // assss.protectedtval // 不可访问  protectd 是同包的其他类访问  和 不同包的子类访问
    }
}

//测试protectd 不同包子类访问
class protecttets1 extends classb{
    String s = protectedtval;//访问到了
}
class protecttets2 extends protecttets1{
    String s = protectedtval;//访问到了
}

//测试static1
class mmm extends classa {
    //    public static int ddd = 1000;
//    private static int bbb = 8888;
// 这里不是重写 重新定义了子类中的static变量
    static void aaaa() {

    }
}

//测试static2
class nnn {
    void mmm() {
//        mmm.bbb
//        mmm.ccc
//        mmm.ddd
//        可以访问 父类的static
    }
}