package com.docker.libalgorithm.sort;

/**
 * 基数排序
 * 依次按照个位、十位、百位、千位 进行（稳定的计数排序【使用记录数组保证稳定】）
 */
public class RadixSort {

    /**
     * 基数排序
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 从个位开始，对数组arr按"指数"进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp); //依次按照个位、十位、百位、千位 进行（稳定的计数排序【使用记录数组保证稳定】）
        }
    }

    /**
     * 计数排序-对数组按照"某个位数"进行排序
     *
     * @param arr
     * @param exp 指数
     */
    public static void countingSort(int[] arr, int exp) {
        if (arr.length <= 1) {
            return;
        }

        // 计算每个元素的个数
        int[] c = new int[10];
        for (int i = 0; i < arr.length; i++) {
            c[(arr[i] / exp) % 10]++;
            //使用计数排序记录
            // 1.【个位】每一个同"桶"的个数
            // 2.【十位】 每一个同"桶"的个数
            // 3.【百位】  每一个同"桶"的个数
        }

        // 计算排序后的位置 （用于稳定性 记录数组）
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[arr.length]; // 这里使用的稳定计数排序进行排序
        for (int i = arr.length - 1; i >= 0; i--) { //从待排序最后一个开始找位置
            r[c[(arr[i] / exp) % 10] - 1] = arr[i];
            c[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }
    }
}
