package Trees;

// From Video-4
public class ConstructionSet {

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

    private static TreeNode constructFromInOrder(int[] inorder, int si, int ei) {

        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(inorder[mid]);

        root.left = constructFromInOrder(inorder, si, mid - 1);
        root.right = constructFromInOrder(inorder, mid + 1, ei);

        return root;
    }

    public static TreeNode constructFromInOrder(int[] inorder) {
        return constructFromInOrder(inorder, 0, inorder.length - 1);
    }

    // Question:- Convert Sorted Doubly Linked List to BST (inPlace)
    private static TreeNode getMidNode(TreeNode head) {
        if (head == null || head.right == null)
            return head;
        TreeNode slow = head, fast = head;
        while (fast.right != null && fast.right.right != null) {
            slow = slow.right;
            fast = fast.right.right;
        }

        return slow;
    }

    public static TreeNode DoublyLLToBST(TreeNode head) {
        // Base Case
        if(head == null || head.right == null) return head;

        TreeNode midNode = getMidNode(head);
        TreeNode prev = midNode.left, forw = midNode.right;
        midNode.right = midNode.left = forw.left = null;
        if (prev != null)
            prev.right = null;
        TreeNode root = midNode;
        // This line is very very important. Why ?? Video-4 @ 1:23:00
        TreeNode leftHead = (prev != null ? head : null), rightHead = forw;

        root.left = DoublyLLToBST(leftHead);
        root.right = DoublyLLToBST(rightHead);
        return root;
    }

}