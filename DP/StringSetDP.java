// package DP;

import java.util.Arrays;

public class StringSetDP {

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    public static int longestPlaindromicSubsequence_memo(String str, int i, int j, int[][] dp) {
        if (i >= j) {
            if (i == j)
                return dp[i][j] = 1;
            return dp[i][j] = 0;
        }

        if (dp[i][j] != 0)
            return dp[i][j];

        int a = longestPlaindromicSubsequence_memo(str, i + 1, j - 1, dp);
        int b = longestPlaindromicSubsequence_memo(str, i + 1, j, dp);
        int c = longestPlaindromicSubsequence_memo(str, i, j - 1, dp);

        if (str.charAt(i) == str.charAt(j)) {
            return dp[i][j] = a + 2;
        } else {
            return dp[i][j] = Math.max(b, c);
        }

    }

    public static int longestPlaindromicSubsequence_tabu(String str, int I, int J, int[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; i < n && j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = i == j ? 1 : 0;
                    continue;
                }

                int a = dp[i + 1][j - 1];
                int b = dp[i + 1][j];
                int c = dp[i][j - 1];

                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = a + 2;
                } else {
                    dp[i][j] = Math.max(b, c);
                }
            }
        }
        return dp[I][J];
    }

    public static void longestPlaindromicSubsequence() {
        String str = "geeksforgeeks";
        int n = str.length();
        int[][] dp = new int[n][n];
        int ans = longestPlaindromicSubsequence_memo(str, 0, n - 1, dp);
        // int ans = longestPlaindromicSubsequence_tabu(str, 0, n - 1, dp);
        System.out.println(ans);
        display2D(dp);
    }

    // ==========================================================================================

    // 1143
    private static int longestCommonSubsequence_memo(String str1, String str2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = longestCommonSubsequence_memo(str1, str2, n - 1, m - 1, dp);
        int b = longestCommonSubsequence_memo(str1, str2, n, m - 1, dp);
        int c = longestCommonSubsequence_memo(str1, str2, n - 1, m, dp);

        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return dp[n][m] = a + 1;
        } else {
            return dp[n][m] = Math.max(b, c);
        }
    }

    private static int longestCommonSubsequence_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                int a = dp[n - 1][m - 1];
                int b = dp[n][m - 1];
                int c = dp[n - 1][m];

                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = a + 1;
                } else {
                    dp[n][m] = Math.max(b, c);
                }
            }
        }
        return dp[N][M];
    }

    public static void longestCommonSubsequence() {
        String str1 = "abcde", str2 = "ace";
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // int ans = longestCommonSubsequence_memo(str1, str2, n, m, dp);
        int ans = longestCommonSubsequence_tabu(str1, str2, n, m, dp);
        System.out.println(ans);
        display2D(dp);
    }

    // ===========================================================================================

    // 72
    public static int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = n == 0 ? m : n;

        if (dp[n][m] != 0)
            return dp[n][m];

        int a = minDistance_memo(s1, s2, n - 1, m - 1, dp);
        int b = minDistance_memo(s1, s2, n - 1, m, dp);
        int c = minDistance_memo(s1, s2, n, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a;
        } else {
            return dp[n][m] = Math.min(Math.min(a, b), c) + 1;
        }
    }

    public static int minDistance_tabu(String s1, String s2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n == 0 ? m : n;
                    continue;
                }

                int a = dp[n - 1][m - 1];
                int b = dp[n - 1][m];
                int c = dp[n][m - 1];

                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = a;
                } else {
                    dp[n][m] = Math.min(Math.min(a, b), c) + 1;
                }
            }
        }

        return dp[N][M];
    }

    // Every operation (replace, insert, delete) has different value then find
    // minimum cost
    static int[] cost = { 2, 3, 4 };

    public static int minDistance_tabu_withDifferentValues(String s1, String s2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n == 0 ? m : n;
                    continue;
                }

                int a = dp[n - 1][m - 1];
                int b = dp[n - 1][m];
                int c = dp[n][m - 1];

                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = a;
                } else {
                    if (a <= b && a <= c) {
                        dp[n][m] = a + cost[0]; // for replace;
                    } else if (b <= a && b <= c) {
                        dp[n][m] = b + cost[1]; // for delete;
                    } else {
                        dp[n][m] = c + cost[2]; // for insert;
                    }
                }
            }
        }

        return dp[N][M];
    }

    public static void minDistance(String word1, String word2) {
        String s1 = "horse", s2 = "ros";
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        // int ans = minDistance_memo("horse", "ros", n, m, dp);
        // int ans = minDistance_tabu("saturday", "sunday", n, m, dp);
        int ans = minDistance_tabu_withDifferentValues("saturday", "sunday", n, m, dp);
        System.out.println(ans);
        display2D(dp);
    }

    // ===========================================================================================

    // https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/
    public static int minOperations(String s1, String s2) {
        // Your code goes here
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int ans = minOperations_memo(s1, s2, n, m, dp);
        System.out.println(ans);
        display2D(dp);
        return ans;
    }

    public static int minOperations_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = n == 0 ? m : n;
        }
        if (dp[n][m] != 0)
            return dp[n][m];

        int a = minOperations_memo(s1, s2, n - 1, m - 1, dp);
        int b = minOperations_memo(s1, s2, n, m - 1, dp);
        int c = minOperations_memo(s1, s2, n - 1, m, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a;
        } else {
            return dp[n][m] = Math.min(b, c) + 1;
        }
    }

    public static int minOperations_tabu(String s1, String s2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n == 0 ? m : n;
                    continue;
                }
                int a = dp[n - 1][m - 1];
                int b = dp[n][m - 1];
                int c = dp[n - 1][m];

                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = a;
                } else {
                    dp[n][m] = Math.min(b, c) + 1;
                }
            }
        }
        return dp[N][M];
    }

    public static void main(String[] args) {
        // longestPlaindromicSubsequence();
        // longestCommonSubsequence();
        // minDistance("", "");
        minOperations("heap", "pea");
    }
}
