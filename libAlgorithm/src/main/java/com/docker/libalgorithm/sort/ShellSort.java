package com.docker.libalgorithm.sort;

//https://blog.csdn.net/qq_35344198/article/details/106665126
/*
 * 希尔排序（Shell Sort）是把记录按下标的一定增量分组，对每组使用插入排序算法，随着增量逐渐减少，
 * 每组包含的元素越来越多，当增量减至1时，所有元素被分为一组，算法终止
 *
 * */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 4, 5, 1, 9, 20, 13, 16}; //步长首先为length/2, 分组插入排序；
        // 后续length/2/2分组 直到步长等于1，最后一趟插入排序（相对于冒泡排序，好处在于拷贝一次，冒泡三次）
        shellSort(arr); // 如：步长为2是时候 1，2，3，4，5，6，7   1，3，5，7 为一组，2，4，6为一组
        print(arr);
    }

    private static void shellSort(int[] arr) {
        int len = arr.length;
        if (len == 1) return;

        int step = len / 2;
        while (step >= 1) {
            for (int i = step; i < len; i++) { // 从第一个步长开始，for循环++到最后，
                int value = arr[i]; // 需要进行插入排序的值value
                int j = i - step;
                for (; j >= 0; j -= step) { // 往回--step步长的值和value进行比较 进行插入
                    if (value < arr[j]) {
                        arr[j + step] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + step] = value;
            }

            step = step / 2;
        }
    }

    private static void print(int[] arr) {
        System.out.println("Print array:");
        for (int x : arr) {
            System.out.print(x + "\t");
        }
        System.out.println("");
    }
}
