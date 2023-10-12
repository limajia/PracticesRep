package com.docker.libalgorithm;

import java.util.Arrays;

//硬币找零 贪心算法、回溯思想
public class Algorithm1 {
    public static void main(String[] args) {
        int total = 11;
        int[] list = {5, 3};
        int computeCount = getComputeCount(total, list);
        System.out.println(computeCount);
    }

    //过于贪心导致查找失败
    private static int getComputeCount(int total, int[] list) {
        Arrays.sort(list);//双轴快速排序，从小到大
        int result = 0;
        int length = list.length;
        for (int i = length - 1; i >= 0; i--) {
            int offsetCount = total / list[i];
            result += offsetCount;
            total -= offsetCount * list[i];
            if (total == 0) { //fix 这里的缺点是没有使用局部方法内局部变量，修改后无法记录原始的值，最好建立一个temp
                return result;
            }
        }
        return -1;
    }
}
