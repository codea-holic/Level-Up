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
    

}