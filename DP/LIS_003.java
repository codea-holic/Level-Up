
// package DP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS_003 {

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    // ==================================================================================================
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

    public static void LIS_Rec() {
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        // int [] arr = {5, 6, 4, 3, 7, 2, 4};
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(LIS_Rec(arr, i, dp), maxLen);
        }
        // LDS_DP(arr, dp);
        display(dp);
        System.out.println(maxLen);
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

    // ==================================================================================================

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

    // ==================================================================================================

    // https://geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/

    public int maxSumIS(int arr[], int n) {
        // code here.
        int[] dp = new int[n];

        int ans = maxSumIS(arr, dp);
        return ans;
    }

    private int maxSumIS(int[] arr, int[] dp) {
        int n = arr.length, maxSum = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            maxSum = Math.max(dp[i], maxSum);
        }
        return maxSum;
    }

    // ==================================================================================================

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

    // ==================================================================================================

    // reverse_longestBitonicSequnece \/
    public static int reverseLIS_DP(int[] arr, int[] dp) {

        int n = arr.length, maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }

    public static int reverseLDS_DP(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    public static int reverse_longestBitonicSequence(int[] arr) {
        int n = arr.length, maxLen = 0;
        int[] rLis = new int[n];
        int[] rLds = new int[n];
        reverseLDS_DP(arr, rLds);
        reverseLIS_DP(arr, rLis);
        display(rLis);
        display(rLds);

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(rLis[i] + rLds[i] - 1, maxLen);
        }
        return maxLen;
    }

    // ==================================================================================================

    // 354: https://leetcode.com/problems/russian-doll-envelopes/
    public int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int n = envelopes.length, maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] != envelopes[j][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    public int maxEnvelopes_optimized() {
        return 0;
    }

    // 673
    // ==================================================================================================
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0, maxCount = 0;
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[i] == dp[j] + 1)
                        count[i] += count[j];
                    else if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            }

            if (maxLen == dp[i])
                maxCount += count[i];
            else if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            }
        }
        return maxCount;
    }

    // ==================================================================================================
    // Minimum deletion required to make array sorted
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // This is wrong, we also have to check for equal element (Means we have to find
        // longest non-decreasing subsequence)
        // return n - (nums, dp);
        return 0;
    }

    // ==================================================================================================

    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    public static int buildingBridges(int[][] arr) {

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });
        int n = arr.length;
        int[] dp = new int[n];
        int maxBridges = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] < arr[i][1] && arr[j][0] < arr[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxBridges = Math.max(maxBridges, dp[i]);
        }
        return maxBridges;
    }

    public static int buildingBridges_opti(int[][] arr) {
        return 0;
    }

    public static int LIS_optimized(int[] arr) {
        int n = arr.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int locOfNum = binarySearch(list, arr[i]);
            if (locOfNum == list.size())
                list.add(arr[i]);
            else {
                list.set(locOfNum, arr[i]);
            }
        }
        return list.size();
    }

    private static int binarySearch(List<Integer> list, int num) {
        int li = 0, ri = list.size();
        while (li < ri) {
            int mid = (li + ri) / 2;
            if (num <= list.get(mid))
                ri = mid;
            else
                li = mid + 1;
        }

        return li;
    }

    public static void main(String[] args) {
        LIS_Rec();
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        // int [][] bridges = {{8, 1}, {1, 2}, {4, 3}, {3, 4}, {5, 5}, {2, 6}, {6, 7},
        // {7, 8}};
        int[][] bridges2 = { { 6, 2 }, { 4, 3 }, { 2, 6 }, { 1, 5 } };
        // System.out.println(reverse_longestBitonicSequence(arr));
        // System.out.println(buildingBridges(bridges2));
        // int ans = LIS_optimized(arr);
        // System.out.println(ans);
    }
}