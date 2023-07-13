package com.google;


public class TestFunctionInvoke {


    public static void main(String[] args) {
        final TestFunctionInvoke testFunctionInvoke = new TestFunctionInvoke();
        final InnerClass1 innerClass1 = new InnerClass1();
        innerClass1.setRunnable(new Runnable() {
            @Override
            public void run() {
                //testFunctionInvoke.abc();//testFunctionInvoke.adc()调用了外部类的方法，main方法结束后，外部类的对象还是存在的
                //回调已经将对象引用设置进去了，不是回调的时候才设置进去，只是回调的时候才会调用。
                System.out.println(testFunctionInvoke==null); //看来不调用testFunctionInvoke方法，这也算是在匿名的Runnable对象中引用了。
                //
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                innerClass1.runnable.run();
            }
        }).start();
    }

    private void abc() {
        System.out.println("abc");
    }

    private static class InnerClass1{
       private Runnable runnable;

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }
    }
}

