package com.docker.libalgorithm.sort;

import java.util.Arrays;

// 冒泡排序
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort(array, array.length);
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort2(array2, array2.length);
        System.out.println(Arrays.toString(array2));
    }

    private static void bubbleSort(int[] array, int length) {
        if (length <= 1) return;
        // length个数 需要length-1趟冒泡
        for (int i = 0; i < length - 1; i++) {
            boolean doSortFlag = false;
            // 每趟冒一个数到最后，每次比较相邻两个,再来一个循环进行移动对比
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] + array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                    doSortFlag = true;
                }
            }
            // 优化1，如何走完一趟，没有排序，则直接返回
            if (!doSortFlag) {
                return;
            }
        }
    }

    // 优化 找到最后不需要交换的位置为循环的最后临界，如后面的几个数字是有序的
    private static void bubbleSort2(int[] array, int length) {
        if (length <= 1) return;
        // length个数 需要length-1趟冒泡
        for (int i = 0; i < length - 1; i++) {
            int sortEnd = length - 1;// = length - 1 - i,此时i=0; // 只要走一趟排序了 就会更新sortEnd
            boolean doSortFlag = false;
            // 每趟冒一个数到最后，每次比较相邻两个,再来一个循环进行移动对比
            for (int j = 0; j < sortEnd; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] + array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                    doSortFlag = true;
                    sortEnd = j; //更新 排序交换的最后位置
                }
            }
            // 优化1，如何走完一趟，没有排序，则直接返回
            if (!doSortFlag) {
                return;
            }
        }
    }


    // 向下冒泡算法，暂时不看
    /**
     * 向下冒泡。可能比冒泡更易懂？
     *
     * 算法概要：
     * 从0开始，用这个元素去跟后面的所有元素比较，如果发现这个元素大于后面的某个元素，则交换。
     * 3 2 6 4 5 1
     * 第一趟是从 index=0 也就是 3， 开始跟index=1及其后面的数字比较
     *  3 大于 2，交换，变为 2 3 6 4 5 1，此时index=0的位置变为了2
     *    接下来将用2跟index=2比较
     *  2 不大于 6 不交换
     *  2 不大于 4 不交换
     *  2 不大于 5 不交换
     *  2 大于 1，交换，变为 1 3 6 4 5 2，第一趟排序完成。
     *
     * 第二趟是从 index=1 也就是 3，开始跟index=2及其后面的数字比较
     *  3 不大于 6 不交换
     *  3 不大于 4 不交换
     *  3 不大于 5 不交换
     *  3 大于 2，交换，变为 1 2 6 4 5 3，第二趟排序完成。
     *
     * 第三趟是从 index=2 也就是 6，开始跟index=3及其后面的数字比较
     *  6 大于 4，交换，变为 1 2 4 6 5 3, 此时 index = 2 的位置变为了4
     *     接下来将用4跟index=4比较
     *  4 不大于 5 不交换
     *  4 大于 3，交换，变为 1 2 3 6 5 4，第三趟排序完成。
     *
     * 第四趟是从 index=3 也就是 6，开始跟index=4及其后面的数字比较
     *  6 大于 5，交换，变为 1 2 3 5 6 4, 此时 index = 3 的位置变为了5
     *     接下来将用5跟index=5比较
     *  5 大于 4，交换，变为 1 2 3 4 6 5, 第四趟排序完成。
     *
     * 第五趟是从 index=4 也就是 6，开始跟index=5及其后面的数字比较
     *  6 大于 5，交换，变为 1 2 3 4 5 6, 此时 index = 4 的位置变为了5
     *     接下来将用5跟index=6比较
     *  index = 6 已经不满足 index < length 的条件，整个排序完成。
     */
    private static void bubbleDownSort(int[] arr) {
        int len = arr.length;
        if (len == 1) return;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

}
