package android.os;

public class Environment {
}

// stub作用 stub，存根是什么

// j2ee里面的stub是这样说的..为屏蔽客户调用远程主机上的对象，必须提供某种方式来模拟本地对象,
// 这种本地对象称为存根(stub),存根负责接收本地方法调用,并将它们委派给各自的具体实现对象.
// 就是一个占位，代码可以骗过编译器，运行过程中直接使用已经加载的其他包的类。

/*如： 自己写一个同包的类
import android.util.Printer;

public class Looper {
    public static Looper getMainLooper() {
        throw new RuntimeException("Stub");
    }

    public Thread getThread() {
        throw new RuntimeException("Stub");
    }

    public void dump(Printer pw, String prefix) {
        throw new RuntimeException("Stub");
    }
}*/
