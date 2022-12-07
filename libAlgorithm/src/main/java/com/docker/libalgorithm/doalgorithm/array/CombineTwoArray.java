package com.docker.libalgorithm.doalgorithm.array;

import java.util.Arrays;

// 合并两个有序数组 -- 这就是归并排序中的一个子步骤
public class CombineTwoArray {
    public static void main(String[] args) {
        int[] a = {2, 5, 7, 8, 17};
        int[] b = {1, 3, 6, 9, 13, 18};
        //doCombine1(a, b);
        doCombine2(a, b);
    }

    // 工具算法
    private static void doCombine1(int[] a, int[] b) {
        int[] combine = new int[a.length + b.length];
        // 先将a放入array
        for (int i = 0; i < a.length; i++) {
            combine[i] = a[i];
        }
        //再将b放入array
        for (int j = 0; j < b.length; j++) {
            combine[a.length + j - 1] = b[j];
        }
        //工具类最后对array进行排序
        Arrays.sort(combine);
        //输出
        System.out.println(Arrays.toString(combine));
    }

    // while循环判断算法   对比当合并有序链表的时候，可以添加两个链表是不是尾小于头 直接拼接就可以
    private static void doCombine2(int[] a, int[] b) {
        int[] combine = new int[a.length + b.length];
        int combineIndex = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < a.length && bIndex < b.length) {
            if (a[aIndex] > b[bIndex]) {
                combine[combineIndex] = b[bIndex];
                combineIndex++;
                bIndex++;
            } else {
                // <=
                combine[combineIndex] = a[aIndex];
                combineIndex++;
                aIndex++;
            }
        }
        if (aIndex < a.length) {//剩下a有 没有合入的
            for (int i = aIndex; i < a.length; i++, combineIndex++) {
                combine[combineIndex] = a[i];
            }
        }
        if (bIndex < b.length) {//剩下b有 没有合入的
            for (int i = bIndex; i < b.length; i++, combineIndex++) {
                combine[combineIndex] = b[i];
            }
        }
        System.out.println(Arrays.toString(combine));
    }
}
