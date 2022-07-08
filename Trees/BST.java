package Trees;

import java.util.ArrayList;

public class BST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // Constructor chaining...
        TreeNode(int val) {
            this(val, null, null);
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode() {
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null)
            curr = curr.right;

        return curr.val;
    }

    public static int minimum(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null)
            curr = curr.left;

        return curr.val;
    }

    public boolean find(TreeNode root, int data) {

        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == data)
                return true;
            else if (data > curr.val)
                curr = curr.right;
            else
                curr = curr.left;
        }
        return false;
    }

    public boolean nodeToRootPath(TreeNode root, int data, ArrayList<Integer> ans) {

        TreeNode curr = root;
        while (curr != null) {
            ans.add(curr.val);
            if (curr.val == data)
                return true;
            else if (data > curr.val)
                curr = curr.right;
            else
                curr = curr.left;
        }
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int data1, int data2) {
        if (root == null)
            return root;

        // data1 and data2 will be present in the tree.
        TreeNode curr = root;
        while (curr != null) {
            if (data1 < curr.val && data2 < curr.val)
                curr = curr.left;
            else if (data1 > curr.val && data2 > curr.val)
                curr = curr.right;
            else
                break;
        }

        // if data1 and data2 may be present in the tree.
        return (curr != null && find(curr, data1) && find(curr, data2)) ? curr : null;
    }
}
