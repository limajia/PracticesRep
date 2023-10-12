package com.docker.lib.testgotolabel;

public class TestBreak {
    public static void main(String[] args) {
        testBreak();
    }

    private static void testBreak() {
        int i = 0;
        while (i < 10) {//两层循环判断
            i++;
            switch (i) { //两层循环
                case 4:
                    break;
                case 5:
                    continue;
            }
            System.out.println("i = " + i);
        }
    }
}
