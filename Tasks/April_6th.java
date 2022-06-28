// package Tasks;
public class April_6th {

    // GoldMine 2
    public static int getMaxGold(int[][] arr) {
        // write your code here
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    int getGold = 0;
                    getGold = fillBag(arr, i, j);
                    if (getGold > max) {
                        max = getGold;
                    }
                }
            }
        }

        return max;
    }

    private static int fillBag(int[][] arr, int i, int j) {
        int getGold = 0;

        getGold += arr[i][j];
        arr[i][j] = -arr[i][j];
        int n = arr.length, m = arr[0].length;
        if ((j + 1) < m && arr[i][j + 1] > 0)
            getGold += fillBag(arr, i, j + 1);
        if ((i + 1) < n && arr[i + 1][j] > 0)
            getGold += fillBag(arr, i + 1, j);
        if ((i - 1) >= 0 && arr[i - 1][j] > 0)
            getGold += fillBag(arr, i - 1, j);
        if ((j - 1) >= 0 && arr[i][j - 1] > 0)
            getGold += fillBag(arr, i, j - 1);

        return getGold;
    }

    // Friends Pairing 2
    
    private static int counter = 1; // used because question demands here

    public static void friendsPairing(int i, int n, boolean[] used, String asf) {
        // write your code here
        if (i > n) {
            System.out.println(counter + "." + asf);
            counter++;
            return;
        }
        if (used[i] == true) {
            friendsPairing(i + 1, n, used, asf);
        } else {
            used[i] = true;
            friendsPairing(i + 1, n, used, asf + "(" + i + ") ");
            for (int j = i + 1; j <= n; j++) {
                if (used[j] == false) {
                    used[j] = true;
                    friendsPairing(i + 1, n, used, asf + "(" + i + "," + j + ") ");
                    used[j] = false;
                }
            }
            used[i] = false;
        }
    }

    // mask = 1 << (n+1);
    // Error: Tried through bits but giving extra values...
    public static void friendsPairingBits(int i, int n, int mask, String asf) {
        // write your code here
        if (i >= n) {
            System.out.println(counter + "." + asf);
            counter++;
            return;
        }

        if ((mask & (1 << i)) == 1) {
            friendsPairingBits(i + 1, n, mask, asf);
        } else {
            mask = (mask ^ (1 << i));
            friendsPairingBits(i + 1, n, mask, asf + "(" + i + ") ");
            for (int j = i + 1; j <= n; j++) {
                if ((mask & (1 << i)) != 1) {
                    mask = (mask ^ (1 << i));
                    friendsPairingBits(i + 1, n, mask, asf + "(" + i + "," + j + ") ");
                    mask = (mask ^ (1 << i));
                }
            }
            mask = (mask ^ (1 << i));
        }
    }


    public static void main(String[] args) {

        // int [][] grid = {{7, 2, 0, 17, 2}, {4, 5, 7, 0, 0}, {0, 8, 9, 0, 14}};
        // int[][] grid2 = { { 7, 4, 0 }, { 2, 5, 8 }, { 0, 7, 9 }, { 17, 0, 0 }, { 2, 5, 14 } };
        // System.out.println(getMaxGold(grid2));
        friendsPairingBits(1, 2, (1 << (2+1)), "");
    }
}
