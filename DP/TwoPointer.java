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

    public int minCostClimbingStairs_memo(int[] cost, int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }

        if (dp[n] != 0)
            return dp[n];

        int fcall = minCostClimbingStairs_memo(cost, n - 1, dp);
        int scall = minCostClimbingStairs_memo(cost, n - 2, dp);
        int ans = Math.min(fcall, scall) + (n == cost.length ? 0 : cost[n]);

        return dp[n] = ans;
    }

    public int minCostClimbingStairs_tabu(int[] cost, int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            dp[n] = Math.min(dp[n - 1], dp[n - 2]) + (n == cost.length ? 0 : cost[n]);
        }

        return dp[N];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        return minCostClimbingStairs_memo(cost, n, dp);
    }

    // HW:-
    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1

    public int friendsPairing_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }
        if (dp[n] != 0)
            return dp[n];
        int single = friendsPairing_memo(n - 1, dp);
        int pair = friendsPairing_memo(n - 2, dp) * (n - 1);
        return dp[n] = single + pair;
    }

    public static int friendsPairing_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2] * (n - 1);
        }
        return dp[N];
    }

    public static void main(String[] args) {
        // tribonacci();
        // climbStairs();
        int n = 12;
        int [] dp = new int[n+1];
        System.out.println(friendsPairing_tabu(n, dp));
        display(dp);
    }

}
