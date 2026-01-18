package leetcode.simple.p0094binarytreeinordertraversal;

import java.util.ArrayList;
import java.util.List;

class BinaryTreeInOrderTraversalApp {

    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrderRecursion(root, list);
        return list;
    }

    private static void inOrderRecursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrderRecursion(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inOrderRecursion(root.right, list);
        }
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

