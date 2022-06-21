// package SearchAndSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TwoSum {

    // 167.
    public int[] twoSum(int[] arr, int target) {

        int n = arr.length, si = 0, ei = n - 1;
        while (si < ei) {
            int val = arr[si] + arr[ei];
            if (val == target)
                return new int[] { si + 1, ei + 1 };
            if (val < target)
                si++;
            else
                ei--;
        }

        return new int[] { -1, -1 };
    }

    // 167 -> Follow up : Sorted Array contain duplicates and you have to return all
    // the unique pairs...
    // If range will be given then we pass si & ei as parameter...
    // Time Complexity: O(N)
    public static ArrayList<int[]> twoSumFollowUp(int[] arr, int target, int si, int ei) {

        ArrayList<int[]> result = new ArrayList<int[]>();
        while (si < ei) {
            int val = arr[si] + arr[ei];
            if (val == target) {
                result.add(new int[] { arr[si], arr[ei] });
                int temp1 = arr[si], temp2 = arr[ei];
                while (si < ei && arr[si] == temp1)
                    si++;
                while (si < ei && arr[ei] == temp2)
                    ei--;
            } else if (val < target) {
                int temp = arr[si];
                while (si < ei && arr[si] == temp)
                    si++;
            } else {
                int temp = arr[ei];
                while (si < ei && arr[ei] == temp)
                    ei--;
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] arr, int target, int si, int ei) {
        if (arr.length < 2)
            return new ArrayList<>();
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < arr.length - 2) {
            int tar_ = target - arr[i];
            ArrayList<int[]> res = twoSumFollowUp(arr, tar_, i + 1, arr.length - 1);
            // Is there any scope of Optimization ?
            for (int[] ans : res) {
                result.add(new ArrayList<>(Arrays.asList(arr[i], ans[0], ans[1])));
            }
            i++;
            while (i < arr.length && arr[i - 1] == arr[i])
                i++;
        }
        return result;
    }

    // TRY FOUR-SUM (in Video 4 ) @ 1:33:00

    // Time complexity: O(N)^(k - 1);
    List<List<Integer>> kSum(int[] arr, int target, int k, int si, int ei) {

        if (k == 2) {
            ArrayList<int[]> base = twoSumFollowUp(arr, target, si, ei);
            // create ans and return res correctly
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = si; i < ei;) {
            List<List<Integer>> smallAns = kSum(arr, target - arr[i], k - 1, i + 1, ei);
            // add smallAns in Main ans
            while (i < ei && arr[i] == arr[i - 1])
                i++;
        }
    }

    // ================================================================================================================

    // 454
    // Initially, we will calculate 2sumCount

    List<List<Integer>> twoSumCount(int[] a, int[] b, int target, int si, int ei) {
        Arrays.sort(a);
        Arrays.sort(b);
        List<List<Integer>> ans = new ArrayList<>();
        int n = a.length;
        while (si < n && ei >= 0) {
            int tar_ = a[si] + b[ei];
            if (tar_ == target)
                ans.add(new ArrayList<>(Arrays.asList(si, ei)));
            else if (tar_ > target) {
                ei--;
                while (ei >= 0 && b[ei] == b[ei + 1])
                    ei--;
            } else {
                si++;
                while (si < n && a[si] == a[si - 1])
                    si++;
            }
        }

        return ans;
    }

    public static int twoSumCount(int[] nums1, int[] nums2, int target) {

        Map<Integer, Integer> fmap = new HashMap<>();

        for (int ele : nums1) {
            fmap.put(ele, fmap.getOrDefault(ele, 0) + 1);
        }

        int count = 0;
        for (int ele : nums2) {
            if (fmap.containsKey(target - ele))
                count += fmap.get(target - ele);
        }
    }

    // O(N^2)
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        Map<Integer, Integer> fmap = new HashMap<>();

        for (int ele1 : nums1) {
            for (int ele2 : nums2)
                fmap.put(ele1 + ele2, fmap.getOrDefault(ele1 + ele2, 0) + 1);
        }

        int count = 0, target = 0;
        for (int ele1 : nums3) {
            for (int ele2 : nums4) {
                if (fmap.containsKey(target - (ele1 + ele2)))
                    count += fmap.get(target - (ele1 + ele2));
            }
        }
        return count;
    }

    // TRY WITH ARRAY INSTEAD OF HASHMAP fourSumCount;
    // ================================================================================================================

    // 658
    public static int insertPosition(int[] arr, int data) {

        int n = arr.length, si = 0, ei = n - 1;
        int idx = 0;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return si; // insert Pos => si, Last Index => (si - 1);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> ans = new ArrayList<>();
        for (int ele : arr) {
            ans.add(ele);
        }
        int n = arr.length;
        if (x < arr[0])
            return ans.subList(0, k);
        else if (x > arr[n - 1])
            return ans.subList(n - k, n);
        else {
            int idx = insertPosition(arr, x);
            int lr = Math.max(0, idx - k);
            int rr = Math.min(n - 1, idx + k);

            while ((rr - lr + 1) > k) {
                if (x - arr[lr] > arr[rr] - x)
                    lr++;
                else
                    rr--;
            }

            return ans.subList(lr, lr + k);
        }
    }

    // Alternative:- DRY RUN THIS...
    // Time Complexity:- O(log(n) + K) 
    public List<Integer> findClosestElements_02(int[] arr, int k, int x) {
        LinkedList<Integer> ret = new LinkedList<Integer>();
        int left = 0;
        int right = arr.length - k;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for(int i=left; i<left+k;i++){
            ret.add(arr[i]);
        }
        return ret;
    }
    // ================================================================================================================

    // 300
    // public int lengthOfLIS(int [] arr){
            // Done in New File.
    // }
    
    public static int[] twoRepeated(int arr[], int N){
        // Your code here
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            int ele = Math.abs(arr[i]);
            if(arr[ele - 1] < 0){
                ans.add(ele);
                if(ans.size() == 0) break;
            } else{
                arr[ele - 1] = -arr[ele - 1];
            }
        }
        int [] res = new int[2];
        res[0] = ans.get(0);
        res[1] = ans.get(1);
        return res;
    }

    // ================================================================================================================

    public static void main(String[] args) {

        int[] arr = { -1, -1, 2, 3, 3, 4, 4, 4, 5, 6, 6, 7, 9, 9 };

        // ArrayList<int[]> res = twoSumFollowUp(arr, 8, 0, arr.length - 1);
        // for (int[] ans : res) {
        // System.out.println(ans[0] + " " + ans[1]);
        // }

        // int [] tc = {0,0,0};
        // List<List<Integer>> res = threeSum(tc, 0, 0, 2);
        // for(List<Integer> values : res){
        // System.out.println(values.get(0) + " " + values.get(1) + " " +
        // values.get(2));
        // }

        int [] temp = new int[]{4, 3, 2, 3, 1, 1};
        twoRepeated(temp, 4);
    }

}
