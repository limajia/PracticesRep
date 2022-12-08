package com.docker.libalgorithm.search;

/**
 * 二叉（排序）搜索树 binary search tree （BST）
 *
 * 二叉排序树建立（插入）出现相同值的处理
 * 什么是二叉排序树(二叉搜索树）
 * 二叉排序树(二叉搜索树）（Binary Sort Tree）或者是一颗空树；或者是具有下列性质的二叉树：
 * （1）若左子树不为空，则左子树上所有结点的值均小于它的根节点的值
 * （2）若右子树不为空，则右子树上所有结点的值均大于它的根节点的值
 * （3）左右子树自己也是二叉排序树
 * （4）没有键值相等的节点
 *
 * 出现了两个相同的值该如何处理呢？
 * 在查找资料后得到：
 * “二叉树是一种动态查找表。特点是，树的结构不是一次生成的，而是在查找过程中，当树中【不存在】关键字等于给定值的结点时【再进行插入】。
 * 新插入的结点一定是一个新添加的叶子结点，并且是查找不成功时查找路径上访问的最后一个结点的左孩子或右孩子结点。”
 * 所以根据上面所得，第二个36在树中存在，所以在建立时不需要再次插入。
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3 6
 * / \
 * 2 4
 * /
 * 1
 *
 * output：3
 */
public class KthSmallest {  // 该题目查找二叉排序树 上最k小的值是？？  kth== 第k个值

    public static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }

        int partition = partition(arr, 0, arr.length - 1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }

        return arr[partition];
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];

        int i = p;
        for (int j = p; j < r; j++) {
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
