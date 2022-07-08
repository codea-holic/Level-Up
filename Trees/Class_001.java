package Trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Find Set
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
        return root == null ? -(int) 1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root) {
        return root == null ? (int) 1e9 : Math.max(root.val, Math.max(minimum(root.left), minimum(root.right)));
    }

    // Important function
    public boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data); // isse hame node to root path bhi mil jata hai...
    }

    private boolean nodeToRootPath(TreeNode root, int data, List<TreeNode> path) {
        if (root == null)
            return false;

        if (root.val == data) {
            path.add(root);
            return true;
        }

        boolean res = nodeToRootPath(root.left, data, path) || nodeToRootPath(root.right, data, path);

        if (res) {
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

    // 863
    // =============================================================================================
    public void kDown(TreeNode root, TreeNode blocker, int k, List<Integer> ans) {

        if (root == null || k < 0 || root == blocker)
            return;

        if (k == 0)
            ans.add(root.val);

        kDown(root.left, blocker, k - 1, ans);
        kDown(root.right, blocker, k - 1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        List<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root, target.val, path);
        // for(TreeNode node : path){
        // System.out.print(node.val + " ");
        // }
        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kDown(path.get(i), block, k - i, ans);
            block = path.get(i);
        }
        return ans;
    }

    // Altenative way 863
    // ==============================================================================
    public int distanceK(TreeNode root, int target, int k, List<Integer> ans) {
        if (root == null || k < 0)
            return -1;
        if (root.val == target) {
            printKDown(root, null, k, ans);
            return 1;
        }
        int ld = distanceK(root.left, target, k, ans);
        if (ld != -1) {
            printKDown(root, root.left, k - ld, ans);
            return ld + 1;
        }
        int rd = distanceK(root.right, target, k, ans);
        if (rd != -1) {
            printKDown(root, root.right, k - rd, ans);
            return rd + 1;
        }
        return -1;
    }

    public void printKDown(TreeNode root, TreeNode blocker, int k, List<Integer> ans) {
        if (root == null || k < 0 || root == blocker)
            return;

        if (k == 0) {
            ans.add(root.val);
            return;
        }

        printKDown(root.left, blocker, k - 1, ans);
        printKDown(root.right, blocker, k - 1, ans);
    }

    // View Set
    // Diameter and Sum set
    // Construction Set
    // Traversal Set

    // =================================================================================================
    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
    public List<List<Integer>> burnTree(TreeNode root, TreeNode fireNode) {

        List<List<Integer>> ans = new ArrayList<>();
        int k = 0;
        while (true) {
            List<Integer> smallAns = new ArrayList<>();
            distanceK(root, fireNode.val, k, smallAns);
            if (smallAns.size() >= 0) {
                ans.add(smallAns);
                k++;
            } else
                break;
        }

        return ans;
    }

    // Alternate Way: IMPORTANT...
    private void burningTreeNode(TreeNode root, TreeNode block, int time, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == block)
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burningTreeNode(root.left, block, time + 1, ans);
        burningTreeNode(root.right, block, time + 1, ans);
    }

    public int burningTree(TreeNode root, TreeNode fireNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;

        if (root == fireNode) {
            burningTreeNode(root, null, 0, ans);
            return 1;
        }

        int lt = burningTree(root.left, fireNode, ans);
        if (lt != -1) {
            burningTreeNode(root, root.left, lt, ans);
            return lt + 1;
        }

        int rt = burningTree(root.right, fireNode, ans);
        if (rt != -1) {
            burningTreeNode(root, root.right, rt, ans);
            return rt + 1;
        }

        return -1;
    }

    /**
     * Discussion on Burning Trees
     * <p>
     * Time Complexity: - O(2N) where N is timeComplexity of PreOrder Traversal
     * <p>
     * Worst Test Case: - When our fireNode is rightMost in the tree
     */
    public void burningTree(TreeNode root, TreeNode fireNode) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, fireNode, ans);
    }

    private void burningTreeNodeWithWater(TreeNode root, TreeNode block, int time, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> waterSet) {
        if (root == null || root == block || waterSet.contains(root.val))
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burningTreeNodeWithWater(root.left, block, time + 1, ans, waterSet);
        burningTreeNodeWithWater(root.right, block, time + 1, ans, waterSet);
    }

    public int burningTreeWithWater(TreeNode root, TreeNode fireNode, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> waterSet) {
        if (root == null)
            return -1;

        if (root == fireNode) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, null, 0, ans, waterSet);
                return 1;
            }
            return -2; // fire node is present but it have water;
        }

        int lt = burningTreeWithWater(root.left, fireNode, ans, waterSet);
        if (lt > 0) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, root.left, lt, ans, waterSet);
                return lt + 1;
            }
            return -2;
        }
        if (lt == -2)
            return -2;

        int rt = burningTree(root.right, fireNode, ans);
        if (rt > 0) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, root.right, rt, ans, waterSet);
                return rt + 1;
            }
            return -2;
        }

        if (rt == -2)
            return -2;

        return -1;
    }

    /**
     * <b> {@code Modified Burning Tree} </b>
     * <p>
     * {@code Question}
     * <p>
     * At some nodes of trees water is also present, When fire reaches on
     * any node which contain water, fire will stop there.
     * <p>
     * With all previous param, You are also given with list of nodes that contain
     * water at the node.
     * 
     * @param root     : Root of the tree.
     * @param fireNode : Starting Fired Node.
     * @param waterSet : HashSet that contain nodes which have water.
     */
    public void burningTreeWithWater(TreeNode root, TreeNode fireNode, HashSet<Integer> waterSet) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTreeWithWater(root, fireNode, ans, waterSet);
    }

    // =================================================================================================

    // 236. Lowest common Ancestor of a binary Tree (15ms)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        ArrayList<TreeNode> pathP = new ArrayList<>();
        ArrayList<TreeNode> pathQ = new ArrayList<>();
        // if P and Q may exists
        nodeToRootPath(root, p, pathP);
        nodeToRootPath(root, q, pathQ);
        if (pathP.size() == 0 || pathQ.size() == 0)
            return null;
        // System.out.println(pathP.size() + " " + pathQ.size());
        // for(TreeNode n : pathP){
        // System.out.println(n.val);
        // }
        int i = pathP.size() - 1, j = pathQ.size() - 1;
        TreeNode pAns = null;
        while (i >= 0 && j >= 0) {
            TreeNode n1 = pathP.get(i);
            TreeNode n2 = pathQ.get(j);
            if (n1 != n2) {
                return pAns;
            }
            pAns = n1;
            i--;
            j--;
        }
        return pAns;
    }

    private boolean nodeToRootPath(TreeNode root, TreeNode target, ArrayList<TreeNode> ans) {
        if (root == null || target == null)
            return false;

        if (root == target) {
            ans.add(root);
            return true;
        }

        boolean res = nodeToRootPath(root.left, target, ans) || nodeToRootPath(root.right, target, ans);
        if (res) {
            ans.add(root);
            return true;
        }
        return false;
    }

    // 236 Alternate Way: This is brute force way, time on Leetcode: 2024ms
    // =================================================================================================
    public TreeNode lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q)
            return root;

        boolean resPLeft = find(root.left, p.val);
        boolean resPRight = find(root.right, p.val);
        boolean resQLeft = find(root.left, q.val);
        boolean resQRight = find(root.right, q.val);

        if (!resPLeft && !resPRight || !resQLeft && !resQRight)
            return null;

        if (resPLeft && resQRight || resPRight && resQLeft)
            return root;

        TreeNode ans = null;
        if (resPLeft && resQLeft)
            ans = lowestCommonAncestor_(root.left, p, q);
        if (ans != null)
            return ans;

        else
            ans = lowestCommonAncestor_(root.right, p, q);
        if (ans != null)
            return ans;

        return ans;
    }

    // 236 Another Way:
    public TreeNode LCANode = null;

    public boolean LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;

        boolean selfPresent = (root == p) || (root == q);

        boolean leftPresent = LCA(root.left, p, q);
        if(LCANode != null) return true;
        boolean rightPresent = LCA(root.right, p, q);
        if(LCANode != null) return true;

        if ((leftPresent && rightPresent) || (leftPresent && selfPresent) || (rightPresent && selfPresent))
            LCANode = root;

        return leftPresent || rightPresent || selfPresent;
    }
    // Find Set Done
}
