package SearchAndSort;

import java.util.ArrayList;
import java.util.Arrays;

public class LIS {
    // 300
    public static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        if (n <= 1)
            return n;

        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(arr[0]));
        // It will not guarantee you to give Correct LIS in ArrayList, But it can give
        // you guarantee that length of ArrayList(al) is always correct.
        for (int i = 1; i < n; i++) {
            int ele = arr[i];
            if (ele > al.get(al.size() - 1)) {
                al.add(ele);
            } else {
                int idx = insertLocation(al, ele);
                al.set(idx, ele);
            }
        }
        return al.size();
    }

    private static int insertLocation(ArrayList<Integer> al, int ele) {

        int n = al.size(), si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (al.get(mid) <= ele) {
                si = mid + 1;
            } else
                ei = mid - 1;
        }

        return si; // When ele is greater than all element of ArrayList, then it will return the
                   // size of (al);
    }

    public static void main(String[] args) {

        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        System.out.println(longestIncreasingSubsequence(arr));
    }
}
