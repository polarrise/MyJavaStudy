package jinbiao.dataStruct_study.binaryTree;

/**
 描述:
 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 二叉树的大小范围在 2 到 100。
 二叉树总是有效的，每个节点的值都是整数，且不重复。
 */
public class Solution {
    public static class TreeNode {
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
    Integer res = Integer.MAX_VALUE, pre = null;
    /**lintCode:1746
     二叉搜索树，根据树的性质，知道root的右子树都是大于它的，左子树都是小于它的。
     那么如果做中序遍历，标准的做法是得到一个递增的序列。
     我们就先遍历左根，节点，右根，这样就会得到一个递增的序列。
     然后对这个序列相邻相减，取最小值即可。 实现时，可以优化掉这个序列。
     在遍历时记录上一个访问的节点值，和当前节点相减，记录下最小值即可。
     */
    public int minDiffInBST(TreeNode root) {   //root:{4,2,6,1,3}。
        // 首先根据(root.left!=null)递归到1节点,pre为1,res为MAX_VALUE整型的最大值:2147483647;  1不比 ->res:2147483647
        // 递归回来到2节点,和MAX_VALUE比,res为1;pre为2;   2147483647和2-1比,取小的那个,所以res为1
        // 在49行递归3节点,res为1,pre为3;                1和3-2比,取小的那个,所以res为1
        // 递归回来到4节点,res为1,pre为4;                1和4-3比,取小的那个,所以res为1
        // 递归6节点res为1,pre为6                        1和6-4比,取小的那个,所以res为1   最终res为1也就是最小值
        if(root.left!=null){           // 先遍历左节点
            minDiffInBST(root.left);
        }
        if (pre != null) {
            res = Math.min(res, root.val - pre);  //返回两个数里面较小的一个
        }
        pre = root.val;
        if (root.right != null) {       //遍历右节点
            int i = minDiffInBST(root.right);   //到2节点时,2节点.rigth!=null,3节点进行递归。3节点递归完,回到2节点
            System.out.println("i:"+i);
        }
        System.out.println("res:"+res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(4);
        TreeNode root_left=new TreeNode(2);
        TreeNode root_right=new TreeNode(6);
        root.left=root_left;
        root.right=root_right;
        TreeNode root_left_left=new TreeNode(1);
        TreeNode root_left_rigth=new TreeNode(3);
        root_left.left=root_left_left;
        root_left.right=root_left_rigth;
        int i = new Solution().minDiffInBST(root);
        System.out.println(i);
    }
}

