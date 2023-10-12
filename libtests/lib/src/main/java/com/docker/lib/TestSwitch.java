package com.docker.lib;

public class TestSwitch {
    public static void main(String[] args) {
        testA("AA"); //输出 default  a //注意这里
        testA("a"); //输出 a
    }

    private static void testA(String a) {
        switch (a){
            default:
                System.out.println("defaut");
            case "a":
                System.out.println("a"); break;
            case "b":
                System.out.println("b");break;
        }
    }

}

