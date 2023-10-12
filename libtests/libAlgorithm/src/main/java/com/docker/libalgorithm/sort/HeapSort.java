package com.docker.libalgorithm.sort;

import java.util.Arrays;

/**
 * 堆排序 索引从0开始。左子= x叉树*index+1，…… 右子= x叉树*index+2
 * 如二叉树 可类比成数组依次比较自己的index位置和 2*index+1左子树 2*index+2左子树
 * <p>
 * 建堆的时候：直接填充一个堆，是一个完全二叉树，若结点个数为n, 则最后一个非叶子结点就为第 n/2个，索引为n/2-1.
 * 直接从n/2-1 ---> 0 位置，进行堆调整就可以
 * <p>
 * 因为使用堆排序，都是从堆顶部拿出排好序的数值。
 *
 *
 * 建立大堆，循环将根放到最后一个位置，排序，这样出来的就是升序[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * 建立小堆，循环将根放到最后一个位置，排序，这样出来的就是将
 *
 * 堆结构对左、右子节点的大小没有要求，只规定叶节点要和子节点（左、右）的数据满足大小关系；区分二叉搜索树
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 5, 6, 7, 1, 4, 2, 3};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        // 1.把无序数组直接就是完全二叉堆
        // 2.进行排序，创建大堆或者小堆
        for (int i = (arr.length / 2) - 1; i >= 0; i--) { //从第n/2个开始
            adjust(arr, i, arr.length);
        }
        // 这样走一趟，只是确定了大堆或者小堆，只确定了根节点的值，还不是有序的结果。

        // 循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶
        for (int i = arr.length - 1; i > 0; i--) {
            // 最后一个元素与第一个元素交换，最后一个元素 不参与调堆操作了
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjust(arr, 0, i);
        }
    }

    // 建立了大堆，左右子树大值 和父节点换，若换了，子节点为父结点，继续
    private static void adjust(int[] arr, int parentIndex, int length) {
        // 孩子节点下标，暂时定为左孩子节点下标
        int childIndex;

        while ((childIndex = (2 * parentIndex + 1)) < length) {
            int parentValue = arr[parentIndex];
            // 当存在右孩子节点，且右孩子节点的值大于左孩子节点的值，childIndex记录为右孩子节点的下标
            // childIndex实际记录的是[最大]的孩子节点的下标
            if (childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }

            // 如果父节点的值比孩子节点的值都大，则调整结束
            if (parentValue >= arr[childIndex]) {
                break;
            }

            //如果父结点小于子节点  将最大的孩子节点的值赋值给父节点
            arr[parentIndex] = arr[childIndex];
            arr[childIndex] = parentValue;
            parentIndex = childIndex;
            // 都是向下子树进行调整，交换的子节点 成为父结点 会一直调节到符合
        }
        // 没有左子节点，直接结束了
    }
}