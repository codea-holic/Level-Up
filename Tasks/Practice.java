// package Tasks;
import java.util.Arrays;

public class Practice {

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    // 115
    // ========================================================================================
    public int numDistinct(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return numDistinct_memo(s1, s2, n, m, dp);
    }

    public int numDistinct_memo(String s1, String s2, int n, int m, int[][] dp) {
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

    private int longestCommonSubsequence_memo(String str1, String str2, int n, int m, int[][] dp) {
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
            return 0;

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

    // 005
    // ============================================================================================
    public static String longestPalindromeSubstring(String str) {
        int n = str.length();
        int [][] dp = new int[n][n];
        for(int [] d : dp){
            Arrays.fill(d, -1);
        }
        longestPalindromeSubstring_memo(str, 0, n-1, dp);
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

    public static int longestPalindromeSubstring_memo(String s, int i, int j, int [][] dp){
        if(i >= j){
            dp[i][j] = i == j ? 1 : 0;
            if(dp[i][j] > max){
                max = dp[i][j];
                stIdx = i;
            }
            return dp[i][j];
        } 

        if(dp[i][j] != -1) return dp[i][j];
        int a = longestPalindromeSubstring_memo(s, i+1, j-1, dp);
        int b = longestPalindromeSubstring_memo(s, i+1, j, dp);
        int c = longestPalindromeSubstring_memo(s, i, j-1, dp);

        if(s.charAt(i) == s.charAt(j)){
            if(i + 1 == j) {    // jab "ee" same hai lakin i+1, j-1 pe to 0 hoga to
                //  iswale case me kabhi 2 print hi nhi hoga
                dp[i][j] = 2;
            } else{
                dp[i][j] = a > 0 ? a + 2 : 0;
            }

            if(dp[i][j] > max){
                max = dp[i][j];
                stIdx = i;
            }
            return dp[i][j];
        } else{
            return dp[i][j] = 0;
        }
    }

    public static String longestPlaindromicSubstring_tabu(String s) {
        int n = s.length(), len = 0;
        int[][] dp = new int[n][n];
        int count = 0;
        int I = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0){
                    dp[i][j] = 1;
                    count++;
                }

                else if (gap == 1 && s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2;
                    count++;
                }
                else {
                    if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0){
                        dp[i][j] = dp[i+1][j-1] + 2;
                        count++;
                    } else {
                        dp[i][j] = 0;
                    }
                }

                if(dp[i][j] > len){
                    len = dp[i][j];
                    I = i;
                }
            }
        }
        String str = s.substring(I, I + len);
        return str;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubstring("ac"));
    }

}
