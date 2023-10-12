package com.docker.lib.testleak;

/*成员变量按照其声明的顺序会被初始化，并且立刻被初始化为二进制的0，这个动作发生在所有事件之前，
也就是编译器会立刻将分配给对象的空间初始化。
        最后就是调用类的构造方法了。
        执行顺序:
        执行父类静态代码 执行子类静态代码
        初始化父类成员变量（我们常说的赋值语句）
        初始化父类构造函数
        初始化子类成员变量
        初始化子类构造函数
*/

public class TestLeak {

    //    public static TestLeak sTestLeak = new TestLeak(); //连接时初始化 属于类
//    创建一次后 就不执行了 而下面的会重复执行【死循环了】  先成员变量 后 构造方法
    public TestLeak testLeak = new TestLeak(); //构造函数时初始化 重复上面 进入死循环


    public TestLeak() {
        System.out.println("构造函数");
    }

    void doAA() {
        System.out.println("doAA");
    }

    public static void main(String[] args) {
        //0.------------------------------------------------
        new TestLeak(); // 会进入死循环

        //1.------------------------------------------------

        //        System.out.println("11111111111111");
        //        sTestLeak.doAA();
        //        System.out.println("22222222222222");

        //构造函数
        //11111111111111
        //doAA
        //22222222222222


        //2.------------------------------------------------

        /* 11.
        final TempClass tempClass = new TempClass();
        System.out.println("kaishi");
        new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       Thread.sleep(5000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println(tempClass.a);
                   System.out.println("结束");
               }
           }).start();
        tempClass = null; // final 可以避免 人为的 设置为空
        */
    }
}

class TempClass {
    public int a = 100;
}