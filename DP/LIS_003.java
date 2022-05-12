// package DP;

public class LIS_003 {

    private static int LIS_Rec(int[] arr, int idx, int[] dp) {
        if (dp[idx] != 0)
            return dp[idx];

        int maxLen = 1;
        for (int i = idx - 1; i >= 0; i--) {
            if (arr[i] < arr[idx]) {
                int recAns = LIS_Rec(arr, i, dp);
                maxLen = Math.max(recAns + 1, maxLen);
            }
        }

        return dp[idx] = maxLen;
    }

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    public static void LIS_Rec() {
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        // int [] arr = {5, 6, 4, 3, 7, 2, 4};
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];

        LDS_DP(arr, dp);
        display(dp);
    }

    public static int LIS_DP(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int LDS_DP(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1/
    public int LongestBitonicSequence(int[] nums) {
        int n = nums.length;

        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_DP(nums, LIS);
        LDS_DP(nums, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }
        return maxLen;
    }

    public static int LIS_SumDP(int[] arr, int[] dp) {
        int maxSum = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static int LDS_SumDP(int[] arr, int[] dp) {
        int maxSum = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    // https://geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/


    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1/
    public static int maxSumBS(int arr[], int n) {

        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_SumDP(arr, LIS);
        LDS_SumDP(arr, LDS);

        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, LIS[i] + LDS[i] - arr[i]);
        }
        return maxSum;
    }

    // reverse_longestBitonicSequnece \/

    public static int reverseLIS_DP(int [] arr, int [] dp){

        int n = arr.length, maxLen = 0;
        for(int i = n - 1; i >= 0; i--){
            dp[i] = 1;
            for(int j = i + 1; j < n; j++){
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }

    public static int reverseLDS_DP(int [] arr, int [] dp){
        int n = arr.length, maxLen = 0;
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    public static int reverse_longestBitonicSequence(int [] arr){
        int n = arr.length, maxLen = 0;
        int [] rLis = new int[n];
        int [] rLds = new int[n];
        reverseLDS_DP(arr, rLds);
        reverseLIS_DP(arr, rLis);
        display(rLis);
        display(rLds);
        
        for(int i = 0; i < n; i++){
            maxLen = Math.max(rLis[i] + rLds[i] - 1, maxLen);
        }
        return maxLen;
    }

    // 354: https://leetcode.com/problems/russian-doll-envelopes/

    public static void main(String[] args) {
        // LIS_Rec();
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        System.out.println(reverse_longestBitonicSequence(arr));
    }
}
