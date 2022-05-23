// package DP;

import java.util.Arrays;

public class TargetSet {

    public static void display(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display(int[][] arr) {
        for (int[] ar : arr) {
            display(ar);
        }
    }

    public static int coinChangePermutation_Recr(int tar, int[] coins) {
        if (tar == 0) {
            return 1;
        }

        int count = 0;
        for (int ele : coins) {
            if (tar - ele >= 0) {
                count += coinChangePermutation_Recr(tar - ele, coins);
            }
        }

        return count;
    }

    public static int coinChangePermutation_memo(int tar, int[] coins, int[] dp) {
        if (tar == 0)
            return dp[tar] = 1;

        if (dp[tar] != 0)
            return dp[tar];

        int count = 0;
        for (int ele : coins) {
            if (tar - ele >= 0) {
                count += coinChangePermutation_memo(tar - ele, coins, dp);
            }
        }

        return dp[tar] = count;
    }

    // 377
    public static int coinChangePermutation_DP(int Tar, int[] coins, int[] dp) {

        for (int tar = 0; tar <= Tar; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }

            int count = 0;
            for (int ele : coins) {
                if (tar - ele >= 0) {
                    count += dp[tar - ele];
                }
            }

            dp[tar] = count;
        }

        return dp[Tar];
    }

    public static int coinChangeCombination_Recr(int[] coins, int tar, int i) {
        if (tar == 0)
            return 1;
        if (i == coins.length)
            return 0;
        int count = 0;
        if (tar - coins[i] >= 0) {
            count += coinChangeCombination_Recr(coins, tar - coins[i], i);
        }
        count += coinChangeCombination_Recr(coins, tar, i + 1);

        return count;
    }

    // Convert above function into memoization

    /**
     * Giving Wrong ans : Figure out Why?
     * public static int coinChangeCombination_memo(int tar, int idx, int [] coins,
     * int [] dp){
     * if(tar == 0) {
     * return dp[tar] = 1;
     * }
     * 
     * int count = 0;
     * for(int t = idx; t < coins.length; t++){
     * if(tar - coins[t] >= 0){
     * count += coinChangeCombination_memo(tar-coins[t], t, coins, dp); // t can be
     * used more than once;
     * }
     * }
     * 
     * return dp[tar] += count;
     *
     */

    // tabulation ...
    public static int coinChangeCombination_1DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

     public static int coinChangeCombination_memo(int[] arr, int tar, int li, int[][] dp) {
        if (tar == 0) {
            return dp[li][tar] = 1;
        }

        if(dp[li][tar] != -1) return dp[li][tar];

        int count = 0;
        for (int i = li; i >= 0; i--)
            if (tar - arr[i] >= 0) {
                count += coinChangeCombination_memo(arr, tar - arr[i], i, dp);
            }

        return dp[li][tar] = count;
    }

    public static int coinChangeCombination_2DP(int [] arr, int Tar, int LI, int [][] dp){
        
        for (int li = 0; li <= LI; li++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0) {
                    dp[li][tar] = 1;
                    continue;
                }

                for (int i = li; i >= 0; i--)
                    if (tar - arr[i] >= 0) {
                        dp[li][tar] += dp[i][tar - arr[i]];
                    }
            }
        }

        return dp[LI][Tar];
    }

    public static int coinChange(int tar, int[] coins) {
        int[] dp = new int[tar + 1];
        // int ans = coinChangePermutation_memo(tar, coins, dp);
        int ans = coinChangeCombination_1DP(coins, tar, dp);
        display(dp);
        return ans;
    }

    public static void coinChange() {
        int[] arr = { 1, 2, 3 };
        int tar = 7;

        int[][] dp = new int[arr.length][tar + 1];
        for(int [] d : dp){
            Arrays.fill(d, -1);
        }

        System.out.println(coinChangeCombination_memo(arr, tar, arr.length - 1, dp));
        // System.out.println(coinChangeCombination_2DP(arr, tar, arr.length - 1, dp));

        display(dp);
    }

    
    public static void main(String[] args) {
        int[] coins = { 3, 2, 1 };
        // coinChange(5, coins);
        coinChange();
    }

}
