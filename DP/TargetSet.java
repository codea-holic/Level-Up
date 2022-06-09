package DP;

import java.util.Arrays;

public class TargetSet {

    public static void display(int[] dp) {

        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display(int[][] dp) {

        for (int[] d : dp) {
            display(d);
        }
    }

    public static void fill(int[][] dp, int val) {
        for (int[] d : dp)
            Arrays.fill(d, val);
    }

    public static int coinChangePermutation_Memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                count += coinChangePermutation_Memo(arr, tar - arr[i], dp);
            }
        }

        return dp[tar] = count;
    }

    // 377
    public static int coinChangePermutation_DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int tar = 0; tar <= Tar; tar++) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (tar - arr[i] >= 0) {
                    count += dp[tar - arr[i]];
                }
            }
            dp[tar] = count;
        }
        return dp[Tar];
    }

    // 518. COIN CHANGE II
    public static int coinChangeCombination_DP(int[] arr, int Tar, int[] dp) {
        // CAUTION:- DP shouldn't be filled with -1..
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

    /**
     * Question - Combination wale question me 2 variable change ho rhe hai to
     * hamare knowledge ke hisab se, isme 2D DP lagni chahiye, lakin duniya isme 1D
     * dp, Kyun use karti hai? WO KYA LOGIC KI HAI DUNIYA 2D SE 1D PE PAHUCH GAYI ?
     * I have solved this question using 2D array.
     * Solution:- Kyunki isme hame sum krna hota jo hum 2D ki 1D me bhi kr skte
     * hai...
     * 
     * @param arr :- no of coins
     * @param n   :- ending index of array
     * @param tar :- target
     * @param dp  :- Memoized Array
     * @return Count of no. of coins
     */
    public static int coinChangeCombination_Memo(int[] arr, int n, int tar, int[][] dp) {

        if (tar == 0) {
            return dp[n][tar] = 1;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;

        // loop isliye ulta chalayenge, kyunki ham jab tabulation karenge to loop L->R
        // chala paye, jayda samjhne ke liye tabulation wala solution dekh
        for (int i = n; i >= 0; i--) {
            if (tar - arr[i] >= 0) {
                count += coinChangeCombination_Memo(arr, i, tar - arr[i], dp);
            }
        }

        return dp[n][tar] = count;
    }

    // 322
    public int coinChangeI(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;
        for (int tar = 1; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    dp[tar] = Math.min(dp[tar - ele] + 1, dp[tar]);
                }
            }
        }
        return dp[Tar] != (int) 1e9 ? dp[Tar] : -1;
    }

    // https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/
    // -1 : not explored, 0 : false, 1 : true; SUBSEQUENCE METHOD
    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0 ? 1 : 0);
        }
        if (dp[n][tar] != -1)
            return dp[n][tar];
        boolean res = false;
        if (tar - arr[n - 1] >= 0) {
            res = res || targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1;
        }
        res = res || targetSum(arr, n - 1, tar, dp) == 1;
        return dp[n][tar] = res ? 1 : 0;
    }

    public static int targetSumDP(int[] arr, int N, int Tar, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0 ? 1 : 0);
                    continue;
                }

                boolean res = false;
                if (tar - arr[n - 1] >= 0) {
                    res = res || dp[n - 1][tar - arr[n - 1]] == 1;
                }
                res = res || dp[n - 1][tar] == 1;
                dp[n][tar] = res ? 1 : 0;
            }
        }

        return dp[N][Tar];
    }

    public static void targetSum_BackEng(int [] arr, int N, int Tar, int [][] dp){

    }
    
    public static int targetSum() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];
        // fill(dp, -1);
        // int ans = targetSum(arr, n, tar, dp);
        int ans = targetSumDP(arr, n, tar, dp);
        display(dp);
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1/
    public static int knapSack(int W, int wt[], int val[], int n) {
        // your code here
        int[][] dp = new int[n + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return knapSack_(wt, val, W, n, dp);
    }

    // TC wt = {2, 3, 5, 7}, val = {100, 10, 3, 200}, tar = 10;
    private static int knapSack_(int[] wt, int[] val, int tar, int n, int[][] dp) {

        // kabhi kabhi aisa bhi ho skta hai, ki hamara bag pura na bhare lakin hame
        // optimal ans mil jaye, us case ko bhi to handle krna hoga 🤔
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0 ? 0 : -1);
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];
        int res = 0;
        if (tar - wt[n - 1] >= 0)
            res = Math.max(res, knapSack_(wt, val, tar - wt[n - 1], n - 1, dp) + val[n - 1]);
        res = Math.max(res, knapSack_(wt, val, tar, n - 1, dp));

        return dp[n][tar] = Math.max(dp[n][tar], res);
    }

    public static int coinChange(int[] arr, int tar) {
        int n = arr.length;
        // int[][] dp = new int[n + 1][tar + 1];
        // fill(dp);
        int[] dp = new int[tar + 1];
        // Arrays.fill(dp, -1);
        // int ans = coinChangePermutation_Memo(arr, tar, dp);
        // return coinChangeCombination_Memo(arr, n - 1, tar, dp);
        // int ans = coinChangePermutation_DP(arr, tar, dp);
        // display(dp);
        // return ans;
        return coinChangeCombination_DP(arr, tar, dp);
    }

    public static void main(String[] args) {
        int[] coins = { 2, 3, 5, 7 };
        System.out.println("Working of Recursion also");
        System.out.println(targetSum());
        // System.out.println(coinChange(coins, 10));
    }
}
