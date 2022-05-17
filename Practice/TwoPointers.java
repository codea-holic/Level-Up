// package Practice;

public class TwoPointers {

    public static void display(int[] dp) {

        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display(int[][] dp) {

        for (int[] d : dp) {
            display(d);
        }
    }

    // 509
    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
    }

    public static int fibonacci(int n) {
        int[] dp = new int[n + 1];
        return fibo_memo(n, dp);
    }

    // 1137
    public int tribonacci_memo(int n, int[] dp) {
        if (n <= 2)
            return dp[n] = n == 0 ? 0 : 1;

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = tribonacci_memo(n - 1, dp) + tribonacci_memo(n - 2, dp) + tribonacci_memo(n - 3, dp);
    }

    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        return tribonacci_memo(n, dp);
    }

    // 746
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        return minCostClimbingStairs_memo(cost, n, dp);
    }

    private static int minCostClimbingStairs_memo(int[] cost, int n, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];

        if (dp[n] != 0)
            return dp[n];

        int step1 = minCostClimbingStairs_memo(cost, n - 1, dp);
        int step2 = minCostClimbingStairs_memo(cost, n - 2, dp);
        return dp[n] = Math.min(step1, step2) + (n == cost.length ? 0 : cost[n]);
    }

    

    public static void main(String[] args) {

        int[] arr = { 1, 3, 4, 6, 8 };
        // System.out.println(fibonacci(10));
        System.out.println(minCostClimbingStairs(arr));
    }
}
