public class April_6th {

    public static int getMaxGold(int[][] arr) {
        // write your code here
        int maxG = 0;
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    int collectGold = getGold(arr, i, j, dir);
                    if (collectGold > maxG) {
                        maxG = collectGold;
                    }
                }
            }
        }
        return maxG;
    }

    public static int getGold(int[][] arr, int i, int j, int[][] dir) {
        int fillGold = 0;
        for (int rad = 1; rad < Math.max(arr.length, arr[0].length); rad++) {
            boolean flag = false;
            for (int d = 0; d < dir.length; d++) {
                int r = i + rad * dir[d][0];
                int c = j + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < arr.length && c < arr[0].length && arr[r][c] > 0) {
                    flag = true;
                    fillGold += arr[r][c];
                    arr[r][c] = -arr[r][c];
                    fillGold += getGold(arr, r, c, dir);
                }
            }
            if(!flag){
                break;
            }
        }
        return fillGold;
    }

    public static void main(String[] args) {

        // 5
        // 3
        // 7 4 0 
        // 2 5 8
        // 0 7 9 
        // 17 0 0 
        // 2 5 14 
        
        // int [][] grid = {{7, 2, 0, 17, 2}, {4, 5, 7, 0, 0}, {0, 8, 9, 0, 14}};
        int [][] grid2 = {{7, 4, 0}, {2, 5, 8}, {0, 7, 9}, {17, 0, 0}, {2, 5, 14}};
        System.out.println(grid2.length + " " + grid2[0].length);
        System.out.println(getMaxGold(grid2));
    }
}
