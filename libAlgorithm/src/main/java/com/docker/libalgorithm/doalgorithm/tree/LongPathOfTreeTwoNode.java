package com.docker.libalgorithm.doalgorithm.tree;

//两节点的最长路径（值）
/*
Input:
        1
        / \
        2  3
        / \
       4   5
   Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
*/

//解题思路
//1.因为最长直径不一定过根节点，所以需要分别以每一个节点为中心计算最长路径。
//2.用一个全局变量max来维护最长路径（左子树的深度+右子树的深度）。
//3.为了计算每个子树的深度，使用深度优先遍历计算树中每一个节点的深度（max（左子树深度，右子树深度））
public class LongPathOfTreeTwoNode {
    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        max = Math.max(max, leftDepth + rightDepth); //找到每一个结点的左边深度和右边深度的和 与 全局记录max值的大小
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
