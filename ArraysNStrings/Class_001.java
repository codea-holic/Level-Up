// package ArraysNStrings;
import java.util.HashSet;
import java.util.Set;

public class Class_001 {

    public static void display(int[] arr) {

        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }

    // https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1/
    public static void rotateArr(int[] arr, int k) {

        int n = arr.length;
        k = k % n;
        if (k < 0)
            k += n;

        // Alternatively,
        // k = (k % n + n) % n;
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }

    private static void reverse(int[] arr, int si, int ei) {

        while (si < ei)
            swap(arr, si++, ei--);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void segregateNegativePositive(int[] arr) {

        int n = arr.length, i = 0, j = 0;
        while (j < n) {
            if (arr[j] < 0) {
                swap(arr, i, j);
                i++;
            }
            j++;
        }
    }

    public static void segregate01(int[] arr) {

        int n = arr.length, i = 0, j = 0;
        while (j < n) {
            if (arr[j] == 0) {
                swap(arr, i, j);
                i++;
            }
            j++;
        }
    }

    public static void segregate012(int[] arr) {

        int n = arr.length, i = 0, j = 0, k = n - 1;

        while (j <= k) {
            if (arr[j] == 0)
                swap(arr, i++, j++);
            else if (arr[j] == 1)
                j++;
            else
                swap(arr, j, k--);
        }
    }

    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1#
    public static int max_sum(int arr[], int n) {
        // Your code here
        int sum = 0;
        for (int ele : arr)
            sum += ele;

        int cSum = 0;
        for (int i = 0; i < n; i++)
            cSum += i * arr[i];

        int max = cSum;
        for (int i = 0; i < (n - 1); i++) {
            cSum = cSum - sum + n * arr[i];
            max = Math.max(cSum, max);
        }

        return max;
    }

    // 11. OR https://practice.geeksforgeeks.org/problems/container-with-most-water0535/1/
    public static int maxArea(int[] height) {
        int maxWater = 0;
        int i = 0, j = height.length - 1;
        while(i < j){
            maxWater = Math.max(Math.min(height[i], height[j]) * (j-i), maxWater);
            if(height[i] < height[j]) i++;
            else j--;
        }
        return maxWater;
    }

    // 3. TRY TO DO WITHOUT SET (USING ARRAY OF CHARACTER) -> its Easy.
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        // Frequency of Set is Atomized O(1), not exactly O(1).
        int i = 0, j = 0, n = s.length();
        int maxLen = 0;
        while(j < n){
            if(set.size() == 0 && !set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                maxLen = Math.max(maxLen, j - i + 1);
                j++;
            } else{
                while(i < j && set.contains(s.charAt(j))){
                    set.remove(s.charAt(i++));
                }
            }
        }
        return maxLen;
    }

    // Cramp this way of Code for these type of Question
    public int lengthOfLongestSubstring(String s, int N) {
        
        int [] freq = new int[128];
        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ > 0) count++;

            while(count > 0)
            if(freq[s.charAt(si++)]-- == 1) count--;

            len = Math.max(len, ei - si);
        }
        return len;
    }
    
    // Follow up:-
    
    // 159. Length of Longest Substring with Two Distinct Character.
    public int lengthOfLongestSubstringWithTwoDistinctChar(String s) {
        
        int [] freq = new int[128];
        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) count++;

            while(count > 2)
            if(freq[s.charAt(si++)]-- > 1) count--;

            len = Math.max(len, ei - si);
        }
        return len;
    }

    
    // 76.
    public static String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";

        int [] freq = new int[128];
        int ns = s.length(), nt = t.length(), count = nt, si = 0, ei = 0, len = (int)1e9, gsi = 0;
        for(int i = 0; i < nt; i++){
            freq[t.charAt(i)]++;
        }
        while(ei < ns){
            if(freq[s.charAt(ei++)]-- > 0) count--;

            while(count == 0){
                if(ei - si < len){
                    len = ei - si;
                    gsi = si;
                }

                if(freq[s.charAt(si++)]++ == 0) count++;
            }
        }

        return s.substring(gsi, gsi + len);
    }

    
    // 340.
    
    // 992.


    
    public static void main(String[] args) {
        int[] arr1 = { 1, -2, 3, 0, -5, 6, -7, -8, 9, 10 };
        // rotateArr(arr1, -3);
        // segregateNegativePositive(arr1);
        int[] arr2 = new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1 };
        int[] arr3 = new int[] { 2, 2, 2, 2, 2, 2, 0, 0, 0 };
        // segregate01(arr2);
        // segregate012(arr3);
        // display(arr3);
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

}
