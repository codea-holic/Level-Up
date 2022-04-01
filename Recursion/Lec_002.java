
public class Lec_002 {

    // coin Change Combination Infinite Coins available
    public static int coinChangeCombination_IN(int [] coins, int idx, int tar, String psf) {
        if(tar == 0){
            System.out.println(psf);
            return 1;
        }
        int count = 0;

        for(int i = idx; i < coins.length; i++){
            if(tar - coins[i] >= 0){
                count += coinChangeCombination_IN(coins, i, tar - coins[i], psf + coins[i]);
            }
        }
        return count;
    }

    public static int coinChangePermutation_IN(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        // System.out.println(psf + " " + tar);
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += coinChangePermutation_IN(coins, tar - coins[i], psf + coins[i]);
            }
        }
        return count;
    }
 
    // coin Change Combination Use each coin once
    public static int coinChangeCombination_Sin(int [] coins, int idx, int tar, String psf) {
        if(tar == 0){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int i = idx; i < coins.length; i++){
            if(tar - coins[i] >= 0){
                count += coinChangeCombination_Sin(coins, i+1, tar - coins[i], psf + coins[i]);
            }
        }
        return count;
    }

    public static int coinChangePermutation_Sin(int [] coins, int tar, String psf) {
        if(tar == 0){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int i = 0; i < coins.length; i++){
            if(tar - coins[i] >= 0){
                int val = coins[i];
                coins[i] = (int)1e9;
                count += coinChangePermutation_Sin(coins, tar - val, psf + val);
                coins[i] = val;
            }
        }
        return count;
    }

    // Do homework of Write all combination and Permutation with subsequence method
    // And Also draw trees and find relation with 2^n(Binary).
    public static int coinChangeCombination_IN_Sub(int [] coins, int idx, int tar, String psf){
        if(idx == coins.length || tar == 0){
            if(tar == 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - coins[idx] >= 0){
            count += coinChangeCombination_IN_Sub(coins, idx, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += coinChangeCombination_IN_Sub(coins, idx + 1, tar, psf);
        return count;
    }

    public static int coinChangePermutation_IN_Sub(int [] coins, int idx, int tar, String psf){
        if(idx == coins.length || tar == 0){
            if(tar == 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if(tar - coins[idx] >= 0){
            count += coinChangePermutation_IN_Sub(coins, 0, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += coinChangePermutation_IN_Sub(coins, idx + 1, tar, psf);

        return count;
    }

    public static int coinChangeCombination_Sin_Sub(int [] coins, int idx, int tar, String psf){
        if(idx == coins.length || tar == 0){
            if(tar == 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if(tar - coins[idx] >= 0) count += coinChangeCombination_Sin_Sub(coins, idx + 1, tar - coins[idx], psf + coins[idx] + " ");
        count += coinChangeCombination_Sin_Sub(coins, idx + 1, tar, psf);
        return count;
    }

    public static int coinChangePermutation_Sin_Sub(int [] coins, int idx, int tar, String psf){
        if(idx == coins.length || tar == 0){
            if(tar == 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if(coins[idx] > 0 && tar - coins[idx] >= 0){
            int val = coins[idx];
            coins[idx] = -coins[idx];
            count += coinChangePermutation_Sin_Sub(coins, 0, tar - val, psf + val + " ");
            coins[idx] = -coins[idx];
        }
        count += coinChangePermutation_Sin_Sub(coins, idx + 1, tar, psf);
        return count;
    }

    public static void main(String[] args) {
        int[] coins = { 2, 3, 5, 7 };
        // System.out.println(coinChangePermutation_IN(coins, 10, ""));
        // System.out.println(coinChangeCombination_Sin(coins, 0, 10, ""));
        // System.out.println(coinChangeCombination_IN(coins, 0, 10, ""));
        // System.out.println(coinChangePermutation_Sin(coins, 10, ""));
        // System.out.println(coinChangeCombination_IN_Sub(coins, 0, 10, ""));
        // System.out.println(coinChangePermutation_IN_Sub(coins, 0, 10, ""));
        // System.out.println(coinChangeCombination_Sin_Sub(coins, 0, 10, ""));
        System.out.println(coinChangePermutation_Sin_Sub(coins, 0, 10, ""));
    }
}