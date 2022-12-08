package com.docker.libalgorithm.doalgorithm.tree;

//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
//
//你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点

/*
Input:
        Tree 1                     Tree 2
        1                         2
        / \                       / \
        3   2                     1   3
        /                           \   \
        5                             4   7

        Output:
        3
        / \
        4   5
        / \   \
        5   4   7
*/

public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null; //要有递归返回值去哪里的概念
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode root = new TreeNode(t1.value + t2.value);
        root.left = mergeTrees(t1.left, t2.left); //要有子规模的概念，递归的在一次调用，就是进行了所有的以下子规模
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }

    // 计算根节点到子节点之间是否存在和为sum的一个路径
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;  //要有递归返回值去哪里的概念
        if (root.left == null && root.right == null && root.value == sum) return true;
        return hasPathSum(root.left, sum - root.value) || hasPathSum(root.right, sum - root.value);////要有子规模的概念，递归的在一次调用，就是进行了所有的以下子规模
    }
}
