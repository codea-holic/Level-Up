// package DP;

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
            if (tar == 0){
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

    public static int coinChange(int tar, int [] coins) {
        int[] dp = new int[tar + 1];
        // int ans = coinChangePermutation_memo(tar, coins, dp);
        int ans = coinChangePermutation_DP(tar, coins, dp);
        display(dp);
        return ans;
    }


    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        coinChange(11, coins);
    }

}
