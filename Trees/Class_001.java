package Trees;
import java.util.ArrayList;
import java.util.List;

public class Class_001 {

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
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root) {
        return root == null ? (int) 1e9 : Math.max(root.val, Math.max(minimum(root.left), minimum(root.right)));
    }

    // Important function
    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data); // isse hame node to root path bhi mil jata hai...
    }

    private boolean nodeToRootPath(TreeNode root, int data, List<TreeNode> path){
        if(root == null) return false;
        
        if(root.val == data){
            path.add(root);
            return true;
        }
        
        boolean res = nodeToRootPath(root.left, data, path) || nodeToRootPath(root.right, data, path);
        
        if(res){
            path.add(root);
            return true;
        }
        return false;
    }

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data) {

        if (root == null)
            return new ArrayList<>();

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<TreeNode> left = nodeToRootPath(root.left, data);
        if (left.size() > 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = nodeToRootPath(root.right, data);
        if (right.size() > 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();

        rootToAllLeafPath(root, ans, smallAns);
        return ans;
    }

    private static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> smallAns) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val); // is wale node ko bhi to jodenge...
            ans.add(base);
            return;
        }

        smallAns.add(root.val);
        rootToAllLeafPath(root.left, ans, smallAns);
        rootToAllLeafPath(root.right, ans, smallAns);
        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root, ans);
        return ans;
    }

    private static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.left == null || root.right == null) {
            ans.add(root.val);
        }

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static int countExactlyOneChild(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return 0;

        int leftSe = countExactlyOneChild(root.left);
        int rightSe = countExactlyOneChild(root.right);

        int ans = leftSe + rightSe;
        if (root.left == null || root.right == null)
            ans++;

        return ans;
    }

    // 863 =============================================================================================
    public void kDown(TreeNode root, TreeNode blocker, int k, List<Integer> ans) {

        if (root == null || k < 0 || root == blocker)
            return;

        if (k == 0)
            ans.add(root.val);

        kDown(root.left, blocker, k-1, ans);
        kDown(root.right, blocker, k-1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        
        List<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root, target.val, path);
        // for(TreeNode node : path){
        //     System.out.print(node.val + " ");
        // }
        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for(int i = 0; i < path.size(); i++){
            kDown(path.get(i), block, k - i, ans);
            block = path.get(i);
        }
        return ans;
    }

    // Altenative way 863 ==============================================================================
    public int distanceK(TreeNode root, int target, int k, List<Integer> ans){
        if(root == null || k < 0) return -1;
        if(root.val == target){
            printKDown(root, null, k, ans);
            return 1;
        }
        int ld = distanceK(root.left, target, k, ans);
        if(ld != -1){
            printKDown(root, root.left, k - ld, ans);
            return ld + 1;
        }
        int rd = distanceK(root.right, target, k, ans);
        if(rd != -1){
            printKDown(root, root.right, k - rd, ans);
            return rd + 1;
        }
        return -1;
    }

    public void printKDown(TreeNode root, TreeNode blocker, int k, List<Integer> ans){
        if(root == null || k < 0 || root == blocker) return;
        
        if(k == 0){
            ans.add(root.val);
            return;
        }
        
        printKDown(root.left, blocker, k - 1, ans);
        printKDown(root.right, blocker, k - 1, ans);
    }

    
}
