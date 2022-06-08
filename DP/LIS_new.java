// package DP;

public class LIS_new {

    public static void display(int[] dp) {

        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static int LIS(int[] arr, int idx, int[] dp) {

        if (dp[idx] != 0)
            return dp[idx];

        int maxLen = 1;
        for (int i = idx - 1; i >= 0; i--) {
            if (arr[i] < arr[idx]) {
                int recAns = LIS(arr, i, dp);
                maxLen = Math.max(maxLen, recAns + 1);
            }
        }

        return dp[idx] = maxLen;
    }

    public static void run() {
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        // int [] arr = {5, 6, 4, 3, 7, 2, 4};
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];

        // maxLen = LIS(arr, n-1, dp);
        maxLen = LDS_LR(arr, dp);
        display(dp);
        // display(arr);
        System.out.println(maxLen);
    }

    public static int LIS_LR(int[] arr, int[] dp) {

        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }

    public static void LIS_BackEng(int[] arr, int[] dp) {

        int n = arr.length, maxLen = 0, mIdx = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                mIdx = i;
            }
        }

        printLIS(arr, dp, mIdx, "");
    }

    public static void printLIS(int[] arr, int[] dp, int mIdx, String asf) {

    }

    public static int LDS_LR(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int LDS_RL(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1/
    public int LongestBitonicSequence(int[] nums){
        int n = nums.length;
        int [] lisDp = new int[n];
        int [] ldsDp = new int[n];
        LIS_LR(nums, lisDp);
        LDS_RL(nums, ldsDp);
        // display(lisDp);
        // display(ldsDp);
        int maxLen = 0;
        for(int i = 0; i < n; i++){
            maxLen = Math.max(maxLen, lisDp[i] + ldsDp[i] - 1);
        }
        return maxLen;
    }
    
    public int reverseLBS(int [] nums){
        
        int n = nums.length;
        int [] ldsRL = new int[n];
        int [] ldsLR = new int[n];
        LDS_LR(nums, ldsLR);
        LDS_RL(nums, ldsRL);
        // display(lisDp);
        // display(ldsDp);
        int maxLen = 0;
        for(int i = 0; i < n; i++){
            maxLen = Math.max(maxLen, ldsRL[i] + ldsLR[i] - 1);
        }
        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/
    public int LISsum(int [] arr, int [] dp){
	    int n = arr.length, maxSum = 0;
	    for(int i = n - 1; i >= 0; i--){
	        dp[i] = arr[i];
	        for(int j = i + 1; j < n; j++){
	            if(arr[j] > arr[i]){
	                dp[i] = Math.max(dp[i], dp[j] + arr[i]);
	            }
	        }
	        maxSum = Math.max(maxSum, dp[i]);
	    }
	    return maxSum;
	}

    // 673
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int [] dp = new int[n];
        int [] count = new int[n];
        int maxLen = 0, maxCount = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1; count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if(dp[j] + 1 == dp[i]){
                        count[i] += count[j];
                    } else if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            
            if(maxLen < dp[i]){
                maxLen = dp[i];
                maxCount = count[i];
            } else if (maxLen == dp[i]){
                maxCount += count[i];
            }
        }
        
        display(count);
        

        return maxCount;
    }

    
    public static void main(String[] args) {
        run();
    }
}

// Ctrl + K -> Select a line