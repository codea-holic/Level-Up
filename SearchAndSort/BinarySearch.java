// package SearchAndSort;

public class BinarySearch {

    public static void display(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }

    public static int binarySearch(int[] arr, int data) {

        int n = arr.length, si = 0, ei = n - 1;

        while (si <= ei) {
            int mid = (ei + si) / 2;
            if (arr[mid] == data) {
                return mid;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return -1;
    }

    public static int BinarySearchInRange(int[] arr, int si, int ei, int data) {

        while (si <= ei) {
            int mid = (ei + si) / 2;
            if (arr[mid] == data) {
                return mid;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return -1;
    }

    public static int firstIdx(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        int idx = -1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                idx = mid;
                ei = mid - 1;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return idx;
    }

    public static int lastIdx(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        int idx = -1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                idx = mid;
                si = mid + 1;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return idx;
    }

    // 34
    public static int[] searchRange(int[] arr, int data) {
        return new int[] { firstIdx(arr, data), lastIdx(arr, data) };
    }

    // Remember : ending index (ei) is always pointing to the last index of element
    // and starting index(si) always pointing to the first occrence at the end of
    // looping... ((arr[ei] <= data < arr[si])) reference Video-1 @ 2:45:00 also
    // known as (floor <= data < ceil).

    // https://practice.geeksforgeeks.org/problems/search-insert-position-of-k-in-a-sorted-array/1/#
    static int searchInsertK(int arr[], int N, int data) {
        int si = 0, ei = N - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                return mid;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }

    /**
     * This function find where these element to be inserted, array contains
     * duplicate values.
     */
    public static int insertLocation(int[] arr, int data) {

        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        // si - 1 >= 0 && arr[si - 1] == data ? si - 1: si;
        int insertPos = si;
        int lastIdx = si - 1;
        return si;
    }

    public static int perfectPosOfElement(int[] arr, int data) {
        int insertPos = insertLocation(arr, data);
        int lastIdx = insertPos - 1;

        return (lastIdx >= 0 && arr[lastIdx] == data ? lastIdx : insertPos);
    }

    public static int nearestElement(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        if (data <= arr[0] || arr[ei] <= data)
            return data <= arr[0] ? arr[0] : arr[ei];

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        // hame ye pata hai ki loop jab break hua h tab (ei) piche hai (si) se...
        return data - arr[ei] <= arr[si] - data ? arr[ei] ? arr[si];
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 3, 9, 12, 12, 12, 19, 19, 19, 19 };
        // System.out.println(binarySearch(arr, 3));
        // display(searchRange(arr, 19));
        System.out.println(nearestElement(arr, 11));
    }
}
