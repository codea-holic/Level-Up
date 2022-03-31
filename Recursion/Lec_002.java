
public class Lec_002 {

    // coin Change Combination Infinite Coins available
    public static int coinChangeCombination_IN() {
        int count = 0;
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
                count += coinChangeCombination_Sin(coins, i + 1, tar - coins[i], psf + coins[i]);
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

    public static void main(String[] args) {
        int[] coins = { 2, 3, 5, 7 };
        // System.out.println(coinChangePermutation_IN(coins, 10, ""));
        // System.out.println(coinChangeCombination_Sin(coins, 0, 10, ""));
        System.out.println(coinChangePermutation_Sin(coins, 10, ""));
    }
}