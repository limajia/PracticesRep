package com.docker.libalgorithm.doalgorithm.tree;

//tree相关的算法
//计算高度和深度都是从下往上 计算 +1的
public class TreeAlgorithm {
    //1.求二叉树的最大层数(最大深度)
    //如果二叉树为空，二叉树的深度为0
    //如果二叉树不为空，二叉树的深度 = max(左子树深度， 右子树深度) + 1
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }

    //2.二叉树的最小深度 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
    //对于一个节点，如果左子树或者右子树为空，那么最小深度为 left + right + 1  //这其中必定有一个为空，要么left、要么right。
    //如果左子树和右子树都不为空，那么最小深度为 Math.min(left,right) + 1
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1; //递归
    }

    //3.判断是不是平衡二叉树，且计算出最大高度
    // 平衡树左右子树高度差都小于等于 1
    private boolean result = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (Math.abs(l - r) > 1) result = false;//左右高度大于1就不是平衡树
        return 1 + Math.max(l, r);
    }
}
