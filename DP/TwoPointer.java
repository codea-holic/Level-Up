// package DP;
public class TwoPointer {

    public static void display(int[] dp) {

        for (int val : dp)
            System.out.print(val + " ");
        System.out.println();
    }

    public static void display(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    // 509.
    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (dp[n] != 0)
            return dp[n];

        return dp[n] = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
    }

    public static int fibo_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2];// fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        }
        return dp[N];
    }

    public static int fibo_opti(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void fibo() {
        int n = 12;
        int[] dp = new int[n + 1];
        // System.out.println(fibo_memo(n, dp));
        System.out.println(fibo_tabu(n, dp));
        System.out.println(fibo_opti(n));
        display(dp);
    }

    // 1137.
    public static int tribonacci(int n) {
        if (n <= 2) {
            if (n <= 1)
                return n;
            return 1;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    public static int tribonacci_memo(int n, int[] dp) {
        if (n <= 2) {
            if (n <= 1)
                return dp[n] = n;
            return dp[n] = 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = tribonacci_memo(n - 1, dp) + tribonacci_memo(n - 2, dp) + tribonacci_memo(n - 3, dp);
    }

    public static int tribonacci_tabu(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                if (n <= 1)
                    dp[n] = n;
                else
                    dp[n] = 1;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3];
        }
        return dp[N];
    }

    public static int tribonacci_opti(int N) {
        int a = 0, b = 1, c = 1;
        for (int n = 0; n < N - 2; n++) {
            int sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }
        return c;
    }

    public static void tribonacci() {
        int n = 12;
        int[] dp = new int[n + 1];
        System.out.println(tribonacci(n));
        System.out.println(tribonacci_memo(n, dp));
        display(dp);
        System.out.println(tribonacci_tabu(n, dp));
        System.out.println(tribonacci_opti(n));
    }

    // 70.
    public static int climbStairs(int n) {
        if (n <= 1)
            return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static int climbStairs_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    public static int climbStairs_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2];
        }
        return dp[N];
    }

    public static int climbStairs_opti(int N) {
        int a = 0, b = 1;
        for (int n = 0; n <= N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void climbStairs() {
        int n = 7;
        int[] dp = new int[n + 1];
        System.out.println(climbStairs(n));
        System.out.println(climbStairs_memo(n, dp));
        System.out.println(climbStairs_tabu(n, dp));
        System.out.println(climbStairs_opti(n));
        display(dp);
    }

    // 746.

    // ?? WRONG
    public static int minCostClimbingStairsRecursion(int n, int[] cost) {
        if (n <= 1) {
            return cost[n];
        }

        int fCall = minCostClimbingStairsRecursion(n - 1, cost);
        int sCall = minCostClimbingStairsRecursion(n - 2, cost);
        return Math.min(fCall, sCall) + cost[n];
    }

    // ?? WRONG
    public static int minCostClimbingStairs_memo(int n, int[] cost, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = Math.min(minCostClimbingStairs_memo(n - 1, cost, dp),
                minCostClimbingStairs_memo(n - 2, cost, dp)) + cost[n];
    }

    public static void main(String[] args) {
        // tribonacci();
        // climbStairs();
        int[] cost = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
        System.out.println(minCostClimbingStairsRecursion(9, cost));

    }

}
