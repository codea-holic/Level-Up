// package ArraysNStrings;

public class Class_001 {

    public static void display(int [] arr){

        for(int ele : arr){
            System.out.print(ele + " ");
        }
    }

    // https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1/
    public static void rotateArr(int[] arr, int k) {

        int n = arr.length;
        k = k % n;
        if (k < 0)
            k += n;
        rotateHelper(arr, 0, k - 1);
        rotateHelper(arr, k, n - 1);
        rotateHelper(arr, 0, n - 1);
    }

    private static void rotateHelper(int[] arr, int si, int ei) {

        while (si < ei)
            swap(arr, si++, ei--);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rotateArr(arr, -3);
        display(arr);
    }

}
