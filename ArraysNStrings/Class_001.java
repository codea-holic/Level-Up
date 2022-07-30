package ArraysNStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Sliding Window
public class Class_001 {

    public static void display(int[] arr) {

        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    // https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1/
    public static void rotateArr(int[] arr, int k) {

        int n = arr.length;
        k = k % n;
        if (k < 0)
            k += n;

        // Alternatively,
        // k = (k % n + n) % n;
        reverse(arr, 0, n - 1);
        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - k - 1);
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

    // 11. OR
    // https://practice.geeksforgeeks.org/problems/container-with-most-water0535/1/
    public static int maxArea(int[] height) {
        int maxWater = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            maxWater = Math.max(Math.min(height[i], height[j]) * (j - i), maxWater);
            if (height[i] < height[j])
                i++;
            else
                j--;
        }
        return maxWater;
    }

    // 3. TRY TO DO WITHOUT SET (USING ARRAY OF CHARACTER) -> its Easy.
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        // Frequency of Set is Atomized O(1), not exactly O(1).
        int i = 0, j = 0, n = s.length();
        int maxLen = 0;
        while (j < n) {
            if (set.size() == 0 && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                maxLen = Math.max(maxLen, j - i + 1);
                j++;
            } else {
                while (i < j && set.contains(s.charAt(j))) {
                    set.remove(s.charAt(i++));
                }
            }
        }
        return maxLen;
    }

    // Cramp this way of Code for these type of Question
    public int lengthOfLongestSubstring(String s, int N) {

        int[] freq = new int[128];
        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        while (ei < n) {
            if (freq[s.charAt(ei++)]++ > 0)
                count++;

            while (count > 0)
                if (freq[s.charAt(si++)]-- == 1)
                    count--;

            len = Math.max(len, ei - si);
        }
        return len;
    }

    // Follow up:-

    // 159. Length of Longest Substring with Two Distinct Character.
    public int lengthOfLongestSubstringWithTwoDistinctChar(String s) {

        int[] freq = new int[128];
        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count++;

            while (count > 2)
                if (freq[s.charAt(si++)]-- > 1)
                    count--;

            len = Math.max(len, ei - si);
        }
        return len;
    }

    // 76.
    public static String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";

        int[] freq = new int[128];
        int ns = s.length(), nt = t.length(), count = nt, si = 0, ei = 0, len = (int) 1e9, gsi = 0;
        for (int i = 0; i < nt; i++) {
            freq[t.charAt(i)]++;
        }
        while (ei < ns) {
            if (freq[s.charAt(ei++)]-- > 0)
                count--;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si++)]++ == 0)
                    count++;
            }
        }

        return s.substring(gsi, gsi + len);
    }

    // 340. OR
    // https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1/
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {

        int n = s.length(), count = k, si = 0, ei = 0, len = -1;
        int[] freq = new int[128];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count--;
            while (count < 0) {
                if (freq[s.charAt(si++)]-- == 1)
                    count++;
            }
            if (count == 0) {
                len = Math.max(len, ei - si);
            }
        }
        return len;
    }

    // https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1/
    public static int findSubString(String str) {

        int[] freq = new int[128];
        int n = str.length(), count = 0;
        for (int i = 0; i < n; i++) {
            if (freq[str.charAt(i)]++ == 0)
                count++;
        }
        Arrays.fill(freq, 0);
        int si = 0, ei = 0, len = (int) 1e9, gsi = 0;
        while (ei < n) {
            if (freq[str.charAt(ei++)]++ == 0)
                count--;
            while (count == 0) {
                len = Math.min(len, ei - si);
                if (freq[str.charAt(si++)]-- == 1)
                    count++;
            }
        }
        return len;
    }

    // 1456.
    public int maxVowels(String s, int k) {

        int si = 0, ei = 0, n = s.length(), vowelCount = 0, maxVowelCount = 0;
        while (ei < n) {
            if (isVowel(s.charAt(ei++)))
                vowelCount++;

            if (ei - si == k) {
                maxVowelCount = Math.max(vowelCount, maxVowelCount);
                if (isVowel(s.charAt(si++)))
                    vowelCount--;
            }
        }

        return maxVowelCount;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // Count all SubArray with atmost K Different Intgers
    private static int atMostKDistinct(int[] arr, int k) {
        int si = 0, ei = 0, n = arr.length, count = 0, ans = 0, num = 2 * (int) 1e5 + 1;
        int[] freq = new int[num];
        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                count++;

            while (count > k) {
                if (freq[arr[si++]]-- == 1)
                    count--;
            }

            ans += (ei - si);
        }
        return ans;
    }

    // 992.
    public static int subarraysWithKDistinct(int[] arr, int k) {
        return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
    }

    // 1248.
    public int numberOfSubarrays(int[] arr, int k) {
        return atMostKOddInteger(arr, k) - atMostKOddInteger(arr, k - 1);
    }

    private int atMostKOddInteger(int[] arr, int k) {

        int si = 0, ei = 0, count = 0, countSubArray = 0;
        int n = arr.length;
        while (ei < n) {
            if (arr[ei++] % 2 != 0)
                count++;

            while (count > k) {
                if (arr[si++] % 2 != 0)
                    count--;
            }

            countSubArray += (ei - si);
        }

        return countSubArray;
    }

    // 395.

    // 904.
    // Actually this is the question of subarray with at Most 2 distinct Integer.
    public int totalFruit(int[] fruits) {

        int[] freq = new int[100000 + 1];
        int si = 0, ei = 0, n = fruits.length, len = 0, count = 0;

        while (ei < n) {
            if (freq[fruits[ei++]]++ == 0)
                count++;

            while (count > 2)
                if (freq[fruits[si++]]-- == 1)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 930
    public static int numSubarraysWithSum(int[] nums, int goal) {
        // what if goal == 0
        if (goal == 0)
            return countSubArrAtMostGoal(nums, goal);
        return countSubArrAtMostGoal(nums, goal) - countSubArrAtMostGoal(nums, goal - 1);
    }

    private static int countSubArrAtMostGoal(int[] arr, int g) {

        int si = 0, ei = 0, n = arr.length, countSubArr = 0, count = 0;
        while (ei < n) {
            if (arr[ei++] == 1)
                count++;

            while (count > g) {
                if (arr[si++] == 1)
                    count--;
            }

            countSubArr += (ei - si);
        }
        return countSubArr;
    }

    // 485
    public int findMaxConsecutiveOnes(int[] nums) {

        int ei = 0, count = 0, len = 0;
        int n = nums.length;
        while (ei < n) {
            if (nums[ei] == 1)
                count++;

            if (nums[ei++] == 0) {
                len = Math.max(len, count);
                count = 0;
            }
        }

        return Math.max(len, count);
    }

    // 487. Given a binary array nums, return the maximum number of consecutive 1's
    // in the array if you can flip at most one '0'
    public int findMaxConsecutiveOnesFlip(int[] nums) {

        int n = nums.length, si = 0, ei = 0, len = 0, count = 0;
        while (ei < n) {
            if (nums[ei++] == 0)
                count++;

            while (count > 1)
                if (nums[si++] == 0)
                    count--;

            len = Math.max(len, si - ei);
        }

        return len;
    }

    // 1004
    public int longestOnes(int[] nums, int k) {

        int n = nums.length, si = 0, ei = 0, len = 0, count = 0;

        while (ei < n) {
            if (nums[ei++] == 0)
                count++;

            while (count > k)
                if (nums[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 974
    public static int subarraysDivByK(int[] nums, int k) {

        int[] rem = new int[k];
        int count = 0, ei = 0, sum = 0, n = nums.length;
        rem[0] = 1; // kyunki pehli bar hi 0 aane ka mtlb yeh hai ki, wahan tak independently (0,
                    // idx) k se divisible hai
        while (ei < n) {
            sum += nums[ei++];
            int r = (sum % k + k) % k;
            count += rem[r]++;
        }
        return count;
    }

    // Follow up:- Find longest SubArray Divisible By K
    public static  int  longestSubArrayDivByK(int[] nums, int k) {

        int[] rem = new int[k];
        int len = 0, ei = 0, sum = 0, n = nums.length;
        Arrays.fill(rem, -2); // Explained in Video-4 @ 2:38:00
        rem[0] = -1;
        while (ei < n) {
            sum += nums[ei++];
            int r = (sum % k + k) % k;

            if (rem[r] == -2)
                rem[r] = ei;
            else
                len = Math.max(len, ei - rem[r]);
        }
        return len;
    }


    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    public int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
        // add your code here
        // Replace all 0's with -1's
        HashMap<Integer, Integer> fmap = new HashMap<>();
        // HashMap ka use to krna hi padega kyunki hame pata nhi hai ki range knha tak jayega
        int ei = 0, count = 0, sum = 0;
        fmap.put(0, 1);
        
        while(ei < n){
            sum = sum + (arr[ei++] == 1 ? 1 : -1);
            int sumVal = fmap.getOrDefault(sum, 0);
            count += sumVal;
            fmap.put(sum, sumVal + 1);
        }
        
        return count;
    }

    // https:/c/practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1/#
    public static int maxLen(int[] arr, int n){
        // Your code here
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        int ei = 0, sum = 0, len = 0;
        idxMap.put(0, -1);
        while(ei < n){
            sum = sum + (arr[ei] == 1 ? 1 : -1);
            int idx = idxMap.getOrDefault(sum, -2); // -2 indicate that not found
            if(idx == -2){
                idxMap.put(sum, ei);
            } else{
                len = Math.max(len, ei - idx);
            }
            ei++;
        }
        return len;
    }


    public static void main(String[] args) {
        // int[] arr1 = { 1, -2, 3, 0, -5, 6, -7, -8, 9, 10 };
        // rotateArr(arr1, -3);
        // segregateNegativePositive(arr1);
        // int[] arr2 = new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1 };
        // int[] arr3 = new int[] { 2, 2, 2, 2, 2, 2, 0, 0, 0 };
        // segregate01(arr2);
        // segregate012(arr3);
        // display(arr3);
        // String s = "ADOBECODEBANC";
        // String t = "ABC";
        // System.out.println(minWindow(s, t));
        // System.out.println(findSubString("CCCbAbbBbbCDd"));
        // System.out.println(lengthOfLongestSubstringKDistinct("CCCbAbbbbbbBbbCDd",
        // 2));
        // int [] nums = {1, 2, 1, 2, 3};
        // System.out.println(subarraysWithKDistinct(nums, 2));
        // int[] nums = { 0, 0, 0, 0, 0 };
        // System.out.println(numSubarraysWithSum(nums, 0));
        // int[] arr = { 4, 5, 4, 2, -4, 6, 6, -2, 3, 1, 2, 2, 5, 2, -2 };
        // int[] arr2 = { 4, 5, 0, -2, -3, 1 };
        // System.out.println(longestSubArrayDivByK(arr, 5));
        int [] nums = {1, 0, 0, 0, 1, 1, 0, 1, 0};
        System.out.println(maxLen(nums, 9));

    }

}
