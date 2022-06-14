// package SearchAndSort;

public class QuickSort {
    
    private static void swap(int[] arr, int pivot, int ei) {
        int temp = arr[pivot];
        arr[pivot] = arr[ei];
        arr[ei] = temp;
    }

    public static int segregateData(int [] arr, int si, int ei, int pivot){

        swap(arr, pivot, ei);
        int p = si - 1, itr = si;

        while(itr <= ei){
            if(arr[itr] <= arr[ei])
                swap(arr, ++p, itr);
            itr++;
        }

        return p;
    }

    public static void quickSort(int [] arr, int si, int ei){

        if(si > ei) return;

        int pivot = ei; // last Index
        // int pivot = si; // first Index
        // int pivot = (si + ei) / 2; // for mid Index
        int pIdx = segregateData(arr, si, ei, pivot);
        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }
    public static void main(String[] args) {
        int [] arr = {-12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3};
        quickSort(arr, 0, arr.length - 1);

        for(int ele : arr){
            System.out.print(ele + " ");
        }
    }
}
