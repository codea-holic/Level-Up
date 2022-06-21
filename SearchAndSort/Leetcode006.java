// package SearchAndSort;

import java.lang.String;

// Conceptual Binary Search Problem Video 006
public class Leetcode006 {

    // 875 Koko Eating Bananas
    private static boolean isPossibleToEat(int[] arr, int eatingSpeed, int hour) {
        int hr = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            hr += Math.ceil(arr[i] / (eatingSpeed * 1.0));
            if (hr > hour)
                return false;
        }
        return true;
    }

    public int minEatingSpeed(int[] piles, int h) {
        // Arrays.sort(piles);
        int si = 1, ei = (int) 1e9;
        while (si < ei) {
            int mid = (si + ei) / 2;
            if (isPossibleToEat(piles, mid, h)) {
                si = mid + 1;
            } else
                ei = mid;
        }
        return si;
    }

    // https://leetcode.com/discuss/interview-question/348510/Google-or-OA-2019-or-Maximum-Area-Serving-Cake
    private static boolean isPossibleToServeCake(int[] radius, double cakeArea, int guests) {

        int guestCount = 0;
        for (int i = radius.length - 1; i >= 0; i--) {
            double area = Math.PI * radius[i] * radius[i];
            guestCount += (int) (area / cakeArea);
            if (guestCount >= guests) {
                return true;
            }
        }
        return false;
    }

    public static double maxAreaServingCake(int[] radius, int guests) {

        double si = 1.0, ei = (double) 1e4;
        while (si < ei) {
            double mid = (si + ei) / 2.0;
            if (isPossibleToServeCake(radius, mid, guests)) {
                si = mid;
            } else
                ei = mid - 0.0001;
        }

        return si;
    }

    // 1101
    private static boolean isPossibleToShipInDdays(int[] weights, int capacity, int days) {

        int n = weights.length, i = 0, count = 0;
        while (i < n) {
            int sum = 0;
            while (i < n && sum + weights[i] <= capacity)
                sum += weights[i++];
            count++;
            if (count > days)
                return false;
        }
        return true;
    }

    public static int shipWithinDays(int[] weights, int days) {

        int si = 1, ei = (int) 1e9;
        while (si < ei) {
            int mid = (si + ei) / 2;
            if (!isPossibleToShipInDdays(weights, mid, days))
                si = mid + 1;
            else
                ei = mid;
        }
        return si;
    }

    /**
     * Follow up Question 1101:- Additionaly, You are given with the
     * cost(P) * Plane capacity (C),
     * Return the minimum cost (P * C) to ship all the items in (D) days with Plane,
     * <p>
     * 
     * @Solution Only we have to minimize the capacity of Ship. That will give you
     *           minimum cost
     *           Same as Above Solution...
     */

    /**
     * <b> Follow up-2 </b> {@code Leetcode 1101 } Now, you can deliver any item first than other
     * No Need to follow order of their weights.
     * <p>
     * <i>Before move to above question.</i>
     * <p>
     * Do this:-{@code minimizeSet } You're given an array {@code "arr"} of Elements and Target {@code "tar"},
     *          You have to return minimum no. of sets required, sum of element of each set
     *          doesn't exceed given target. 
     *          You have to keep all the arr elements in set.
     *          You can't keep one element (index-wise) in two set.
     * 
     * @categoryInput
     * <blockquote><pre>
     * int [] arr = {1, 1, 3, 4, 5, 6, 7, 8, 9, 10}, int tar = 13;
     * </pre></blockquote>
     * <p>
     * @categoryOutput
     * <blockquote><pre>
     * 5
     * </pre></blockquote>
     * @categoryExplanation
     * Many ways to represent in set, One such way given below
     * <p>
     * <blockquote><pre>
     * <1, 10>, <1, 9>, <3,8>, <4, 7>, <5, 6>
     * </pre></blockquote>
     * @param args
     */
    public static void minimizeSets(){

    }

    public static void main(String[] args) {
        int[] radius1 = { 1, 1, 1, 1, 2, 2, 3 };
        int[] radius2 = { 4, 3, 3 };
        int[] radius3 = { 6, 7 }; // result in: SIGTERM
        double ans = maxAreaServingCake(radius3, 12);
        System.out.println((double) Math.round(ans * 10000d) / 10000d);

        // int[] weights = { 3, 2, 2, 4, 1, 4 };
        // System.out.println(shipWithinDays(weights, 3));
        // System.out.println(isPossibleToShipInDdays(weights, 6, 3));
    }

}
