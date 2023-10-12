package com.docker.threadtestlib;

public class testFor {
    public static void main(String[] args) {
        int a =10;
        if ((++a) < 15){
            System.out.println("111111-"+a);
        }else if ((++a) >10) {
            System.out.println("2222222-"+a);
        }else {
            System.out.println("3333-"+a);
        }
        // 只会走一个分支 不会走其他分支的判断
    }
}
