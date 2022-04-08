import java.util.HashMap;
import java.util.HashSet;

public class Lec_006_Bits {

    public static int setTrue(int x, int idx) {
        int num = (1 << idx); // on-mask
        return x | num;
    }

    public static int setFalse(int x, int idx) {
        int num = ~(1 << idx); // off-mask
        return x & num;
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public int leftShift(int n){
        return n << 2;
    }

    public int rightShift(int n, int m){
        // if MSB is 1, then '1' will appended from right side 'm' times otherwise '0'.
        return n >> m;
    }

    public boolean isPowerOfFour(int n) {
        if(n > 0 && (n & (n - 1)) == 0){
            while(n > 2){
                n >>>= 2;
            }
            return n == 1 ? true: false;
        }
        return false;
    }

    public int missingNumbers(int [] nums){
        int n = nums.length; 
        int ans = n, i = 0;
        while(i < n){
            ans ^= nums[i] ^ (i++);
        }
        return ans;
    }

    // 191. No. of set Bits ==============================================================================
    
    public int HammingWeight_1(int n){
        int count = 0, i = 0;
        while(i < 32){
            if((n & (i - 1)) != 0) count++;
            i++;
        }
        return count;
    }
    
    public int HammingWeight_2(int n){
        int count = 0, i = 0;
        while(i < 32){
            if((n & 1) != 0) count++;
            n >>>= 1;
        }
        return count;
    }
    
    //Best Approach
    public int HammingWeight_3(int n){
        int count = 0;
        while(n != 0){
            n = (n & (n - 1));
            count++;
        }
        return count;
    }

    public int[] countBits(int n) {
        int [] arr = new int[n+1];
        for(int i = 1; i <= n; i++){
            int nu = ((i - 1) & i);
            arr[i] = arr[nu] + 1;
        }
        return arr;
    }

    // 260.
    public int[] singleNumber3(int[] nums) {
        int [] ans = new int[2];
        int allXor = 0;
        for(int val : nums){
            allXor ^= val;
        }
        
        int rmsb = allXor & (-allXor);
        for(int val : nums){
            if((val & rmsb) == 0){
                ans[0] ^= val;
            } else{
                ans[1] ^= val;
            }
        }
        
        return ans;
    }

    

    public static void main(String[] args) {
        System.out.println(setTrue(10, 1));
        System.out.println(setFalse(10, 1));
    }

}
