package com.jinbiao.dataStruct_study.binaryTree;
/**
 * @author com.jinbiao
 * @date 2021/12/28
 * @apiNote
 */
public class Solution2 {
    public class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
  }
    /**
     * 1359 · 有序数组转换为二叉搜索树
     * 描述:给定一个数组，其中元素按升序排序，将其转换为高度平衡的BST。
     * 对于这个问题，高度平衡的二叉树被定义为二叉树，其中每个节点的两个子树的深度从不相差超过1。
     * 解题思路(取中间节点为当前root，这样平衡);取中间节点为root;左右分治返回
     */
    public TreeNode convertSortedArraytoBinarySearchTree(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }

        return buildUtil(nums, 0, nums.length - 1);  //nums,0,4
    }
    private TreeNode buildUtil(int[] nums, int start, int end) {        //nums:{0,1,2,3,4}
        if(start > end){
            return null;  //如果节点为null,一层一层返回,return：从被调用函数返回到主调函数中继续执行，并非一遇到return整个递归结束
        }

        if(start == end){
            return new TreeNode(nums[start]);
        }

        // 当前级别根位于范围的中间
        int mid = start + (end - start) / 2;           //mid:4/2=2, 1/2=0
        TreeNode curr = new TreeNode(nums[mid]);
        // left
        curr.left = buildUtil(nums, start, mid - 1);  //构造-10的left
        // right
        curr.right = buildUtil(nums, mid + 1, end);

        System.out.println(curr);
        return curr;
    }
        public static void main(String[] args) {
            int[] arr={0,1,2,3,4};
            new Solution2().convertSortedArraytoBinarySearchTree(arr);
    }
}
