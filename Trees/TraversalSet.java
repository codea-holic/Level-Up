package Trees;

import java.util.ArrayList;
import java.util.LinkedList;

class TraversalSet {

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

    private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public void morrisInorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root, leftNode = null;

        while (curr != null) {
            leftNode = curr.left;
            if (leftNode == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(leftNode, curr);
                if (rightMost.right == curr) { // Thread destroy block
                    rightMost.right = null; // Thread destroyed
                    ans.add(curr.val); // In Pre order this line moves to else section
                    curr = curr.right;
                } else { // Thread Creation block
                    rightMost.right = curr;
                    curr = curr.left;
                }
            }
        }
    }

    public void morrisPreorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root, leftNode = null;
        while (curr != null) {
            leftNode = curr.left;
            if (leftNode == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(leftNode, curr);
                if (rightMost.right == curr) { // Thread destroy block
                    rightMost.right = null; // Thread destroyed
                    curr = curr.right;
                } else { // Thread Creation block
                    rightMost.right = curr;
                    ans.add(curr.val);
                    curr = curr.left;
                }
            }
        }
    }

    public boolean isValidBST(TreeNode root) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        return isValidBST(root, min, max);
    }

    // 98. Using Recursion
    // ======================================================================
    private boolean isValidBST(TreeNode root, long lower, long higher) {
        if (root == null)
            return true;

        if (lower < root.val && higher > root.val)
            return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, higher);
        return false;
    }

    // 98. Using Morris Traversal
    // ===============================================================
    public boolean isValidBSTMorris(TreeNode root) {

        long min = Long.MIN_VALUE;
        TreeNode curr = root, leftNode = null;
        while (curr != null) {
            leftNode = curr.left;
            if (leftNode == null) {
                if (min < curr.val)
                    min = curr.val;
                else
                    return false;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(leftNode, curr);
                if (rightMost.right == null) { // Thread Creation block
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    if (min < curr.val)
                        min = curr.val;
                    else
                        return false;
                    curr = curr.right;
                }
            }
        }
        return true;
    }

    // 98. Alternate Way 2:35:00(3)
    // =============================================================
    private void insertAllLeft(TreeNode node, LinkedList<TreeNode> st) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    public boolean isValidBST2(TreeNode root) {
        LinkedList<TreeNode> st = new LinkedList<>();
        insertAllLeft(root, st);
        long prev = (int) 1e13;
        while (st.size() > 0) {
            TreeNode rNode = st.removeFirst();
            if (prev >= rNode.val)
                return false;
            prev = rNode.val;

            insertAllLeft(rNode.right, st);
        }
        return true;
    }

    // 173.
    class BSTIterator {
        private LinkedList<TreeNode> st;

        public BSTIterator(TreeNode root) {
            st = new LinkedList<>();
            insertAllLeft(root, st);
        }

        public int next() {
            TreeNode rNode = st.removeFirst();
            insertAllLeft(rNode.right, st);
            return rNode.val;
        }

        public boolean hasNext() {
            return st.size() > 0;
        }
    }

    // 173. Alternative Way
    // =====================================================================
    class BSTIteratorMorris {
        private TreeNode curr;

        public BSTIterator(TreeNode root) {
            curr = root;
        }

        public int next() {
            int ans = -1;
            while (curr != null) {
                TreeNode leftNode = curr.left;
                if (leftNode == null) {
                    ans = curr.val;
                    curr = curr.right;
                    break;
                } else {
                    TreeNode rightMost = getRightMostNode(leftNode, curr);
                    if (rightMost.right == null) {
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        rightMost.right = null;
                        ans = curr.val;
                        curr = curr.right;
                        break;
                    }
                }
            }
            return ans;
        }

        private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr)
                node = node.right;
            return node;
        }

        public boolean hasNext() {
            return curr != null;
        }
    }

    // 230.
    public int kthSmallest(TreeNode root, int k) {

        TreeNode curr = root;
        int count = 0, ans = -1;
        while (curr != null && count < k) {
            TreeNode leftNode = curr.left;
            if (leftNode == null) {
                ans = curr.val;
                count++;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(leftNode, curr);
                if (rightMost.right == null) {
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    ans = curr.val;
                    count++;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }
}