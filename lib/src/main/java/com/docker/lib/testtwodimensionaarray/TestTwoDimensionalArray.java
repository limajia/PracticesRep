package com.docker.lib.testtwodimensionaarray;

/*
数组的声明方式：1.使用new
                  1.1 若指定了数组 行或列 就不能赋初值了 如 = new int[]{1,1,2}
                  1.2 若没有指定 行和列 可以new的时候赋初值 如 = new int[]{1,1,2}
                2.直接复制 = {}, = {{},{}}

        int[][] apples1 = new int[][]{{1}, {3}, {4}};//二维数组赋值

        int[][] apples2 = new int[3][]; //二维数组赋值
        apples2[0] = new int[]{1, 3, 4, 5};

        int ab[] = new int[]{1, 1};//一维数组赋值
        int ab1[] = new int[5];

        或者不使用new 直接赋值
        int ab[] = {1,2,3}
 */
public class TestTwoDimensionalArray {
    public static void main(String[] args) {
        int[][] apples = new int[3][];
        apples[0] = new int[]{1};
        apples[1] = new int[]{2, 3};
        apples[2] = new int[]{4, 5, 6};
        // 二维数组以上形式不可以在进行初始化了

        for (int i = 0; i < apples.length; i++) {//打印的行数
            int[] apple = apples[i];
            for (int i1 : apple) {
                System.out.println("i1 = " + i1);
            }
        }
    }
}
