package com.docker.libalgorithm.doalgorithm.tree;

//DFS（深度优先搜索）[深度优先搜索算法（Depth-First-Search）] 和 BFS(广度优先搜索)[广度优先搜索算法（Breadth-First-Search）]

/*
说到树的四种遍历方式，可能大家第一时间都会想到它的四种遍历方式，并快速说了它的特点。
    1.先序(先根)遍历：即先访问根节点，再访问左孩子和右孩子
    2.中序遍历：先访问左孩子，再访问根节点和右孩子
    3.后序遍历：先访问左孩子，再访问右孩子，再访问根节点
    4.层次遍历：按照所在层数，从下往上遍历

    接着当你要手动写代码的时候，你写得出来嘛？
    1.递归实现二叉树的前序，中序，后续遍历
    2.非递归二叉树的实现前序，中序，后续遍历
    3.实现二叉树的层序遍历

    假如有以下树
        1
       / \
      2   3
     / \   \
    4   5   6
它的前中后续遍历分别如下
层次遍历顺序：[1 2 3 4 5 6]
前序遍历顺序：[1 2 4 5 3 6]
中序遍历顺序：[4 2 5 1 3 6]
后序遍历顺序：[4 5 2 6 3 1]
层次遍历使用 BFS 实现，利用的就是 BFS 一层一层遍历的特性；而前序、中序、后序遍历利用了 DFS 实现。


前中后序遍历方式主要借助栈的特征，层序遍历的方式主要借助队列的方式。

二叉搜索树：
左子树小于根，根小于右子树。 但是存在一条线的树的情况，引入平衡二叉树，进行平衡，
平衡二叉树：
又称 AVL 树。平衡二叉树是二叉搜索树的进化版，所谓平衡二叉树指的是，左右两个子树的高度差的绝对值不超过 1。
红黑树：
红黑树是每个节点都带颜色的树，节点颜色或是红色或是黑色，红黑树是一种查找树。红黑树有一个重要的性质，
----从根节点到叶子节点的最长的路径不多于最短的路径的长度的两倍----。对于红黑树，插入，删除，查找的复杂度都是O（log N）。
哈夫曼树：
给定n个权值作为n的叶子结点，构造一棵二叉树，若带权路径长度达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman tree)。

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    /*递归 可以绘制访问图，每一个结点都会访问三次，当每个点作为Root的时候进行输出*/
    void preDo(TreeNode root) {//先根
        if (root == null) return;
        System.out.println(root.value); // 都是Visit(Root)的时候，输出需要的值，其他的都在递归的栈帧中，等待返回执行。
        preDo(root.left);
        preDo(root.right);
    }

    void middleDo(TreeNode root) {//中根
        if (root == null) return;
        middleDo(root.left);
        System.out.println(root.value); // 都是Visit(Root)的时候，输出需要的值，其他的都在递归的栈帧中，等待返回执行。
        middleDo(root.right);
    }

    void lastDo(TreeNode root) {//后根
        if (root == null) return;
        lastDo(root.left);
        lastDo(root.right);
        System.out.println(root.value); // 都是Visit(Root)的时候，输出需要的值，其他的都在递归的栈帧中，等待返回执行。
    }

    /*非递归*/
    /*1.非递归实现二叉树的前序遍历*/
    // 思路：递归调用里面都是递归调用，递归调用都是以栈的形式进行存储的，所有也使用stack进行存储。
    // a.将root和左子树，全部进栈，并记录结果 b.当root为空，退出一个stack,找到右子树，执行进栈。c.root不为空且stack不为空为前提
    static List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                result.add(root.value); //先将节点加入结果队列。注意这里：---- 这里相当于递归的访问函数执行。
                stack.push(root);  //不断将该节点左子树入栈
                root = root.left;
            }
            root = stack.pop(); //栈顶节点出栈
            root = root.right; //转向该节点右子树的左子树（下一个循环）
        }
        return result;
    }

    /*2.非递归实现二叉树的中序遍历*/
    //思路：和先序是一致的，就是访问数据的顺序靠后了
    static List<Integer> midOrder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);  //不断将该节点左子树入栈
                root = root.left;
            }
            root = stack.pop(); //栈顶节点出栈
            res.add(root.value); //将节点加入结果队列   注意这里：---- 这里相当于递归的访问函数执行。
            root = root.right; //转向该节点右子树的左子树（下一个循环）
        }
        return res;
    }

    /*3.非递归实现二叉树的后序遍历*/
    //思路：前序遍历为 root -> left -> right，后序遍历为 left -> right -> root。 --这里交换左右
    //可以修改前序遍历成为 root -> right -> left，那么这个顺序就和后序遍历正好相反。--这里颠倒结果顺序，使用linkList，放到前面，而不是后面追加。
    static List<Integer> postOrder(TreeNode root) {
        LinkedList res = new LinkedList();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.empty()) {
            while (root != null) {
                res.addFirst(root.value); //插入队首, 1.这里相当于转换了结果顺序
                stack.push(root);
                root = root.right; //先右后左 2.这里交换了左右的顺序
            }
            root = stack.pop();
            root = root.left;
        }
        return res;
    }

    /*4.非递归实现二叉树的层次遍历*/
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();//实现了queue的功能
        if (root == null)
            return result;
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < count; i++) { // 遍历这一层的所有结点
                TreeNode node = queue.poll(); //将一层的结点 全部清空
                temp.add(node.value);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            //  每次都添加到第一个位置
            result.add(0, temp); //这是每一层的结点 集合 // 这一行可以不用
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        root1.left = root2;
        root1.right = root3;
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        root2.left = root4;
        root2.right = root5;
        TreeNode root6 = new TreeNode(6);
        root3.right = root6;
        ArrayList<Integer> integers = (ArrayList<Integer>) preOrder(root1);
        System.out.println(integers.toString());
        levelOrder(root1);
    }
}
