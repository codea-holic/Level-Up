// package Tasks;

public class Practice {

    public static void mazePath() {
        int[][] dir = { { 0, -1 }, { -1, 0 }, { -1, -1 } }; // v, h, d;
        int er = 2, ec = 2;
        // System.out.println(mazePath(er, ec, dir));
        int[][] dp = new int[er + 1][ec + 1];
        System.out.println(mazePath_memo(er, ec, dir, dp));
    }

    public static int mazePath(int er, int ec, int[][] dir) {
        if (er == 0 && ec == 0)
            return 1;

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath(r, c, dir);
            }
        }

        return count;
    }

    public static int mazePath_memo(int er, int ec, int[][] dir, int[][] dp) {
        if (er == 0 && ec == 0)
            return 1;

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath(r, c, dir);
            }
        }

        return dp[er][ec] = count;
    }

    
    public static void main(String[] args) {
        mazePath();
    }
}
