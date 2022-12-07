package com.docker.libalgorithm.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) return;
        int partitionPos = findPartition(array, left, right);//找到分割位置
        quickSort(array, left, partitionPos - 1);
        quickSort(array, partitionPos + 1, right);
    }

    // 快排的思想
    /*
     * 1.先决定pivot在左边还是右边
     *   2.若在左边，先从右-->左找，且右边遇到左边可以不停下，直到pivot.
     *      3.暂停交换pivot有两种：a.左边主动找 动碰到 右边，b.右边找一直走到了头
     *      3.暂停交换左右是：左边找到大（小）值且 右边找到小（大）值且 左边索引<右边索引
     *   2.若在右边，先从左-->右找，且左边遇到右边可以不停下，直到pivot.
     *      3.暂停交换pivot有两种：a.右边主动找 动碰到 左边，b.左边找一直走到了头
     *      3.暂停交换左右是：左边找到大（小）值且 右边找到小（大）值且 左边索引<右边索引
     *
     * 总结：pivot左，则右先找，左主动碰到右交换pivot
     * */
    private static int findPartition(int[] array, int left, int right) {
        int pivotIndex = left;//保留基准位置，用来pivot暂停交换
        int pivot = array[pivotIndex];//左边的数据 作为基准
        while (left < right)//找的临界最终都是left=right
        {
            // >=pivot的不是暂停点，移动游标  从右往左找比节点小的数
            while (array[right] >= pivot && left < right) {
                right--;
            }

            // <= 才会跳过自己为pivot的情况 从左往右找比节点大的数
            while (array[left] <= pivot && left < right) {
                left++;
            }

            // 符合暂停交换左右
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        // 跳出循环一定是 left==right，符合交换pivot的情况
        array[pivotIndex] = array[left];
        array[left] = pivot;
        return left;
    }
}