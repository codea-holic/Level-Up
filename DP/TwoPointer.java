import java.util.Arrays;

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

    public static int friendsPairing_opti(int N) {

        int a = 0, b = 0;
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                b = a;
                a = 1;
                continue;
            }
            int ans = a + b * (n - 1);
            b = a;
            a = ans;
        }

        return a;
    }

    // Maze Path Counts

    public static int mazePath_memo(int er, int ec, int[][] dir, int[][] dp) {

        if (er == 0 && ec == 0)
            return dp[er][ec] = 1;

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];

            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length)
                count += mazePath_memo(r, c, dir, dp);
        }

        return dp[er][ec] = count;
    }

    public static int mazePath_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public static int mazePathWithJumps_memo(int er, int ec, int[][] dir, int[][] dp) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }
        if (dp[er][ec] != 0) {
            return dp[er][ec];
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];

            while (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += mazePathWithJumps_memo(r, c, dir, dp);
                r += dir[d][0];
                c += dir[d][1];
            }
        }
        return dp[er][ec] = count;
    }

    public static int mazePathWithJumps_tabu(int ER, int EC, int[][] dir, int[][] dp) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];

                    while (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += mazePathWithJumps_memo(r, c, dir, dp);
                        r += dir[d][0];
                        c += dir[d][1];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public static void mazePath() {
        int[][] dir = { { 0, -1 }, { -1, 0 }, { -1, -1 } };
        String[] dirS = { "h", "v", "d" };
        int r = 3;
        int c = 3;
        int[][] dp = new int[r][c];
        System.out.println(mazePathWithJumps_memo(2, 2, dir, dp));
        System.out.println(mazePathWithJumps_tabu(2, 2, dir, dp));
        display(dp);
    }

    // 62
    public static int uniquePaths() {
        int m = 3;
        int n = 7;
        int[][] dir = { { 0, -1 }, { -1, 0 } }; // left or up
        int[][] dp = new int[m + 1][n + 1];
        int count = uniquePaths_memo(m, n, dir, dp);
        System.out.println(count);
        display(dp);
        return count;
    }

    public static int uniquePaths_memo(int er, int ec, int[][] dir, int[][] dp) {
        if (er == 0 && ec == 0) {
            return 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];

            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += uniquePaths_tabu(r, c, dir, dp);
            }
        }

        return dp[er][ec] = count;
    }

    public static int uniquePaths_tabu(int ER, int EC, int[][] dir, int[][] dp) {

        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];

                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // TODO : complete this function and dry run
    public static int uniquePaths_opti() {
        return 0;
    }

    // 63
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        int[][] dp = new int[m][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        int[][] dir = { { 0, -1 }, { -1, 0 } };
        System.out.println(uniquePathsWithObstacles_memo(m - 1, n - 1, obstacleGrid, dir, dp));
    }

    private static int uniquePathsWithObstacles_memo(int er, int ec, int[][] obstacleGrid, int[][] dir, int[][] dp) {
        if (er == 0 && ec == 0)
            return dp[er][ec] = 1;

        if (dp[er][ec] != -1)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];

            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length && obstacleGrid[r][c] != 1) {
                count += uniquePathsWithObstacles_memo(r, c, obstacleGrid, dir, dp);
            }
        }
        return dp[er][ec] = count;
    }

    // 64
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int[][] dir = { { -1, 0 }, { 0, -1 } }; // up or left;
        return minPathSum_memo(m - 1, n - 1, dir, grid, dp);
    }

    public int minPathSum_memo(int er, int ec, int[][] dir, int[][] grid, int[][] dp) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = grid[er][ec];
        }
        if (dp[er][ec] != 0)
            return dp[er][ec];

        int min = (int) (1e9) + 8;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];

            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                min = Math.min(minPathSum_memo(r, c, dir, grid, dp), min);
            }
        }
        return dp[er][ec] = min + grid[er][ec];
    }

    private int minPathSum_tabu(int[][] grid, int[][] dir, int[][] dp) {
        int m = grid.length, n = grid[0].length;
        int ER = m - 1, EC = n - 1;
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = grid[er][ec];
                    continue;
                }

                int min = (int) 1e9;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        min = Math.min(min, dp[r][c]);
                    }
                }
                dp[er][ec] = min + grid[er][ec];
            }
        }
        return dp[ER][EC];
    }

    // 396 -> Trick Question
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, prd = 0, sum = 0;
        for(int i = 0; i < n; i++){
            prd += i * nums[i];
            sum += nums[i];
        }

        max = Math.max(prd, max);
        for(int i = 0; i < n; i++){
            prd = prd - sum + n * nums[i];
            max = Math.max(prd, max);
        }
        
        return max;
    }

    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1
    // public static int goldmine()

    public static void main(String[] args) {
        // tribonacci();
        // climbStairs();
        // int n = 1;
        // int[] dp = new int[n + 1];
        // System.out.println(friendsPairing_tabu(n, dp));
        // System.out.println(friendsPairing_opti(n));
        // display(dp);
        mazePath();
        // uniquePaths();
        // display(dp);
    }

}