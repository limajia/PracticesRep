package com.docker.lib.testgotolabel;

public class TestGotoLabel {
    public static void main(String[] args) {
        testFun();
    }

    private static void testFun() {
        int a = 10;
        goHere:
        switch (a) {
            case 10:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
            case 9:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
            case 8:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
            case 7:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
        }

        goHere:
        switch (a) {
            case 10:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
            case 9:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
            case 8:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
            case 7:
                System.out.println("a=【" + a + "】走了标签");
                a--;
                break goHere;//和break公用一个;
        }

        System.out.println("a最后=【" + a + "】");

        /*a=【10】走了标签
        a=【9】走了标签
        a最后=【8】*/

        //1. goHere会走到最近的一个label，不管重复不重复
        //2. 标签会退出循环不执行
        //3. 好像只能用在switch的场景下

        if (a==8){
            a--;
            System.out.println("if减了一个1");
        }
    }
}
