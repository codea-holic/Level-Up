// package DP;

import java.util.Arrays;
import java.util.*;

public class StringSetDP {

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + "\t");
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

        if (dp[i][j] != -1)
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

        // int ans1 = longestCommonSubsequence_memo(str1, str2, n, m, dp);
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

    public static int minDistance_tabu_withDiAfferentValues(String s1, String s2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n == 0 ? m * cost[1] : n * cost[2];
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
        int ans = minDistance_tabu_withDiAfferentValues("saturday", "sunday", n, m, dp);
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

    // 115
    // ========================================================================================
    public static int numDistinct(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = numDistinct_memo(s1, s2, n, m, dp);
        display2D(dp);
        return ans;
    }

    public static int numDistinct_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = m == 0 ? 1 : 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct_memo(s1, s2, n - 1, m - 1, dp);
        int b = numDistinct_memo(s1, s2, n - 1, m, dp);
        // int c = numDistinct_memo(s1, s2, n, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a + b;
        } else {
            return dp[n][m] = b;
        }
    }

    public int numDistinct_tabu(String s1, String s2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = m == 0 ? 1 : 0;
                    continue;
                }

                int a = dp[n - 1][m - 1];
                int b = dp[n - 1][m];
                // int c = dp[n][m-1];

                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = a + b;
                } else {
                    dp[n][m] = b;
                }
            }
        }
        return dp[N][M];
    }

    // 583
    // ========================================================================================
    public int minDelete(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return minDelete_memo(s1, s2, n, m, dp);
        // int ans = longestCommonSubsequence_memo(s1, s2, n, m, dp);
        // return m + n - 2 * ans;
    }

    public int minDelete_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = n == 0 ? m : n;

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = minDelete_memo(s1, s2, n - 1, m - 1, dp);
        int b = minDelete_memo(s1, s2, n - 1, m, dp);
        int c = minDelete_memo(s1, s2, n, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a;
        } else {
            return dp[n][m] = Math.min(b, c) + 1;
        }
    }

    public int minDelete_tabu(String s1, String s2, int n, int m, int[][] dp) {
        // TODO : Complete this function
        return 0;
    }

    // 1034
    // =============================================================================================
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return maxUncrossedLines_memo(nums1, nums2, n, m, dp);
    }

    public int maxUncrossedLines_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];
        int a = maxUncrossedLines_memo(nums1, nums2, n - 1, m - 1, dp);
        int b = maxUncrossedLines_memo(nums1, nums2, n, m - 1, dp);
        int c = maxUncrossedLines_memo(nums1, nums2, n - 1, m, dp);

        if (nums1[n - 1] == nums2[m - 1]) {
            return dp[n][m] = a + 1;
        } else {
            return dp[n][m] = Math.max(b, c);
        }
    }

    public int maxUncrossedLines_DP(int[] nums1, int[] nums2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                int a = dp[n - 1][m - 1]; // maxUncrossedLines_memo(nums1, nums2, n - 1, m - 1, dp);
                int b = dp[n][m - 1]; // maxUncrossedLines_memo(nums1, nums2, n, m - 1, dp);
                int c = dp[n - 1][m]; // maxUncrossedLines_memo(nums1, nums2, n - 1, m, dp);

                if (nums1[n - 1] == nums2[m - 1]) {
                    dp[n][m] = a + 1;
                } else {
                    dp[n][m] = Math.max(b, c);
                }
            }
        }

        return dp[N][M];
    }

    // 005
    // ============================================================================================
    public static String longestPalindromeSubstring(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        longestPalindromeSubstring_memo(str, 0, n - 1, dp);
        display2D(dp);
        return str.substring(stIdx, stIdx + max);
    }

    public static String longestPalindromeSubstring_memo() {
        // This function is not work here because I) we have to return particular
        // String,
        // II) at every step there is a possible ans
        // that's why we use tabulation here
        // TODO : Try to do with memoization : (Believe it's easy)
        return "";
    }

    private static int max = 0;
    private static int stIdx = 0;

    public static int longestPalindromeSubstring_memo(String s, int i, int j, int[][] dp) {
        if (i >= j) {
            dp[i][j] = i == j ? 1 : 0;
            if (dp[i][j] > max) {
                max = dp[i][j];
                stIdx = i;
            }
            return dp[i][j];
        }

        if (dp[i][j] != -1)
            return dp[i][j];
        int a = longestPalindromeSubstring_memo(s, i + 1, j - 1, dp);
        int b = longestPalindromeSubstring_memo(s, i + 1, j, dp);
        int c = longestPalindromeSubstring_memo(s, i, j - 1, dp);

        if (s.charAt(i) == s.charAt(j)) {
            if (i + 1 == j) { // jab "ee" same hai lakin i+1, j-1 pe to 0 hoga to
                // iswale case me kabhi 2 print hi nhi hoga
                dp[i][j] = 2;
            } else {
                dp[i][j] = a > 0 ? a + 2 : 0;
            }

            if (dp[i][j] > max) {
                max = dp[i][j];
                stIdx = i;
            }
            return dp[i][j];
        } else {
            return dp[i][j] = 0;
        }
    }

    public String longestPlaindromicSubstring_tabu(String s) {
        int n = s.length(), len = 0;
        int[][] dp = new int[n][n];
        int count = 0;
        int I = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = 1;
                    count++;
                }

                else if (gap == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2;
                    count++;
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                        count++;
                    } else {
                        dp[i][j] = 0;
                    }
                }

                if (dp[i][j] > len) {
                    len = dp[i][j];
                    I = i;
                }
            }
        }
        String str = s.substring(I, I + len);
        return str;
    }

    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/

    // https://practice.geeksforgeeks.org/problems/longest-common-substring1452/
    private static int longestCommonSubString_tabu(String s1, String s2, int N, int M, int[][] dp) {
        int max = 0, ei = 0;
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                    if(dp[n][m] > max){
                        max = dp[n][m];
                        ei = n - 1;
                    }
                }
            }
        }
        System.out.println(s1.substring(ei - max  + 1, ei + 1));
        return max;
    }

    public static int longestCommonSubstr(String s1, String s2, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        int ans = longestCommonSubString_tabu(s1, s2, n, m, dp);
        return ans;
    }

    // ============================================================================================
    // 44. WildCard Pattern Matching
    private static String removeStars(String str) {
        if (str.length() == 0)
            return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public static boolean isMatch(String s, String p) {
        String newStr = removeStars(p);
        int n = s.length(), m = newStr.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(isMatch_DP(s, n, newStr, m, dp));
        return dp[n][m] == 1 ? true : false;
    }

    public static int isMatch(String s, int n, String p, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];
        int a = isMatch(s, n - 1, p, m - 1, dp);
        int b = isMatch(s, n, p, m - 1, dp);
        int c = isMatch(s, n - 1, p, m, dp);

        if (p.charAt(m - 1) == '?' || s.charAt(n - 1) == p.charAt(m - 1)) {
            return dp[n][m] = a;
        } else if (p.charAt(m - 1) == '*') {
            return dp[n][m] = b == 1 ? 1 : c;
        }
        return dp[n][m] = 0;
    }

    public static int isMatch_DP(String s, int N, String p, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    if (n == 0 && m == 0)
                        dp[n][m] = 1;
                    else if (m == 1 && p.charAt(m - 1) == '*')
                        dp[n][m] = 1;
                    else
                        dp[n][m] = 0;
                    continue;
                }

                int a = dp[n - 1][m - 1];
                int b = dp[n][m - 1];
                int c = dp[n - 1][m];

                if (p.charAt(m - 1) == '?' || s.charAt(n - 1) == p.charAt(m - 1)) {
                    dp[n][m] = a;
                } else if (p.charAt(m - 1) == '*') {
                    dp[n][m] = b == 1 ? 1 : c;
                } else {
                    dp[n][m] = 0;
                }
            }
        }

        return dp[N][M];
    }

    // ============================================================================================
    // 1458. Max Dot Product of Subsequence
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int [][] dp = new int[n+1][m+1];
        int max = maxDotProduct_memo(nums1, nums2, n, m, dp);
        return max;
    }

    private int maximum(int... arr){
        int val = arr[0];
        for(int ele : arr) val = Math.max(val, ele);
        return val;
    }
    
    public int maxDotProduct_memo(int [] nums1, int [] nums2, int n, int m, int [][] dp){

        if(n == 0 || m == 0){
            return dp[n][m] = -(int)1e8;
        }

        if(dp[n][m] != -(int)1e9) return dp[n][m];

        int val = nums1[n-1] * nums2[m-1];
        int acceptBoth = maxDotProduct_memo(nums1, nums2, n-1, m-1, dp) + val;
        int a = maxDotProduct_memo(nums1, nums2, n-1, m, dp);
        int b = maxDotProduct_memo(nums1, nums2, n, m-1, dp);

        return dp[n][m] = maximum(val, acceptBoth, a, b);
    }
    
    public int maxDotProduct_DP(int [] nums1, int [] nums2, int N, int M, int [][] dp){

        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = -(int)1e8;
                    continue;
                }
        
                int val = nums1[n-1] * nums2[m-1];
                int acceptBoth = maxDotProduct_memo(nums1, nums2, n-1, m-1, dp) + val;
                int a = maxDotProduct_memo(nums1, nums2, n-1, m, dp);
                int b = maxDotProduct_memo(nums1, nums2, n, m-1, dp);
        
                dp[n][m] = maximum(val, acceptBoth, a, b);
            }
        }

        return dp[N][M];
    }
    
    // ============================================================================================
    // 
    public static void main(String[] args) {
        // longestPlaindromicSubsequence();
        // longestCommonSubsequence();
        // minDistance("", "");
        // minOperations("heap", "pea");
        // minDelete("leetcode", "etco");
        // System.out.println(numDistinct("gekseks", "geks"));
        // int[] nums1 = { 1, 4, 2 };
        // int[] nums2 = { 1, 2, 4 };
        // maxUncrossedLines(nums1, nums2);
        // longestCommonSubstr("abc", "ac", 3, 2);
        // System.out.println(removeStars("*b?*c*d***a*****"));
        // System.out.println(longestCommonSubstr("ABCDGH", "ACDGHR", 6, 6));
        System.out.println(longestCommonSubstr("ABCDGH", "ACDGHR", 6, 6));
        // System.out.println(isMatch("", ""));
    }
}