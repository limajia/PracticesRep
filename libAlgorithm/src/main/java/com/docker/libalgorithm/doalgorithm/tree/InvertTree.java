package com.docker.libalgorithm.doalgorithm.tree;

// 翻转树，左右替换
// 思路，递归，只考虑一颗简单树
// 我们从根节点开始，递归地对树进行遍历，并从叶子结点先开始翻转。
// 如果当前遍历到的节点 root 的左右两棵子树都已经翻转，那么我们只需要交换两棵子树的位置，即可完成以 root 为根节点的整棵子树的翻转。
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null; //这就是递归返回的结束条件，这就是一个递归的赋值返回。
        TreeNode left = root.left;  // 后面的操作会改变 left 指针，因此先保存下来
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
    // 技巧：看递归的跳出条件是用来哪里，这里明显是给invertTree返回,然后赋值，从这个角度考虑，可能更好理解上一层的递归函数情况。
}
