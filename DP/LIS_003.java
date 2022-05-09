// package DP;

public class LIS_003 {

    public static int LIS_Recr(int[] arr, int idx, int[] dp) {

        if(dp[idx] != 0){
            return dp[idx];
        }

        int maxLen = 1;
        for(int i = idx - 1; i >= 0; i--){
            if(arr[i] < arr[idx]){
                maxLen = Math.max(maxLen, LIS_Recr(arr, i, dp) + 1);
            }
        }
        
        return dp[idx] = maxLen;
    }

    public static int LIS() {
        int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr.length;
        int[] dp = new int[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS_Recr(arr, i, dp));
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.print(LIS());
    }

}
