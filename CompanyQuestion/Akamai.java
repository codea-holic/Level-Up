package CompanyQuestion;

import java.io.*;
import java.util.*;

public class Akamai {

    /**
     * Matrix Traversal
     * In a 2-d matrix consisting of n rows and m columns, the integer at grid[i][j]
     * represents the cell's id at the ith row and the jth column.
     * 
     * 
     * 
     * In 1 second, a traveler can travel from any cell, (i, j)
     * 
     * to any adjacent cell to the right (i + 1, j) or down (i, j + 1).
     * to any cell with the same id as cell (i, j).
     * 
     * 
     * Given a 2-d array of cell ids, find the minimum number of seconds required to
     * travel from the top-left cell, i.e. (0, 0), to the bottom-right cell (n - 1,
     * m - 1).
     * 
     * 
     * 
     * Example
     * 
     * Suppose n = 2 , m = 3, and
     * 
     * grid = [[1,2,3],
     * 
     * [3,1,3]]
     * 
     * 
     * 
     * There are two optimal paths that take 2 seconds.
     * 
     * Travel from (0, 0) to (1, 1) since both cells have an id of 1. From there
     * travel right to (1, 2).
     * Travel down from (0, 0) to (1, 0). Then travel to (1, 2) since both cells
     * have an id of 3.
     * 
     * 
     * Return the number of seconds, 2.
     * 
     * 
     * 
     * Function Description:
     * 
     * Complete the function getMinSeconds in the editor below.
     * 
     * 
     * 
     * The function getMinSeconds has the following parameter:
     * 
     * int grid[n][m]: the ids of the cells
     * 
     * 
     * 
     * Return
     * 
     * int: the minimum number of seconds to travel from the top-left to the
     * bottom-right cell
     * 
     * 
     * 
     * Constraints:
     * 
     * 1 ≤ n ≤ 103
     * 1 ≤ m ≤ 103
     * 1 ≤ n * m ≤ 105
     * 1 ≤ grid[i][j] ≤ 105
     * 
     * Input Format:
     * The first line contains an integer, n, which denotes the number of rows in
     * the matrix, grid.
     * 
     * The second line contains an integer, m, which denotes the number of columns
     * in the matrix, grid.
     * 
     * The next n lines contain m integers each denoting the ith row of the grid.
     * 
     * STDIN FUNCTION
     * ----- --------
     * 2 → grid[] size n = 2
     * 3 → grid[][] size m = 3
     * 1 2 3 → grid = [[1, 2, 3], [1, 1, 1]]
     * 1 1 1
     * 
     * OUTPUT: 1
     * 
     * EXPLANATION:
     * Since the top-left and the bottom-right cells both have the same
     * ids, the traveler can reach the goal in one second.
     */
    class Question1 {

        /*
         * Complete the 'getMinSeconds' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts 2D_INTEGER_ARRAY grid as parameter.
         */

        public static int getMinSeconds(List<List<Integer>> grid) {
            // Write your code here
            int n = grid.size();
            int m = grid.get(0).size();
            int[][] dp = new int[n][m];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }

            int[][] dir = { { 0, -1 }, { -1, 0 } };
            return getMinSeconds(grid, n - 1, m - 1, dp, dir);
        }

        private static int getMinSeconds(List<List<Integer>> grid, int er, int ec, int[][] dp, int[][] dir) {
            if (er == 0 && ec == 0) {
                return dp[er][ec] = 0;
            }
            if (dp[er][ec] != -1)
                return dp[er][ec];

            int minCount = (int) 1e9;
            for (int d = 0; d < dir.length; d++) {
                int r = er + dir[d][0];
                int c = ec + dir[d][1];
                if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                    minCount = Math.min(getMinSeconds(grid, r, c, dp, dir), minCount);
                }
            }

            for (int i = 0; i < er; i++) {
                for (int j = 0; j < ec; j++) {
                    if (grid.get(i).get(j) == grid.get(er).get(ec)) {
                        minCount = Math.min(getMinSeconds(grid, i, j, dp, dir), minCount);
                    }
                }
            }

            return dp[er][ec] = minCount + 1;
        }

        public static void main(String[] args) throws IOException {
            Integer[][] arr = { { 1, 2, 3 }, { 4, 5, 6 } };
            List<List<Integer>> grid = new ArrayList<>();

            grid.add(new ArrayList<>(Arrays.asList(arr[0])));
            grid.add(new ArrayList<>(Arrays.asList(arr[1])));
            System.out.println(getMinSeconds(grid));
        }
    }

    /**
     * Leaf Distance
     * A tree is defined as an undirected, unweighted graph of g_nodes nodes
     * numbered from 1 to g_nodes. It has g_nodes - 1 edges, where the ith edge
     * connects nodes g_from[i] and g_to[i]. The tree is rooted at node number 1.
     * 
     * 
     * 
     * Nodes listed in the integer array special_leaf are the origin nodes of the
     * paths to analyze.
     * 
     * 
     * 
     * The distance between two nodes is defined as the number of edges in the path
     * between the two nodes. Given the tree g, and the array special_leaf, find the
     * sum of distances of all the nodes from all of the special_leaf nodes. Since
     * the answer can be large, report it modulo 109 + 7.
     * 
     * 
     * 
     * Note: A leaf is a node without children.
     * 
     * 
     * 
     * Example : C:\Users\ganes\Pictures\leaf-distance-explanation0.jpg
     * 
     * Suppose g_nodes = 4, g_from = [1, 1, 4], g_to = [4, 3, 2], special_leaf = [2,
     * 3].
     * 
     * 
     * 
     * 
     * 
     * A list of all paths to special leaves is:
     * 
     * 1 → 3 with distance 1
     * 1 → 4 → 2 with distance 2
     * 2 → 4 → 1 → 3 with distance 3
     * 3 → 1 → 4 → 2 with distance 3
     * 4 → 1 → 3 with distance 2
     * 4 → 2 with distance 1
     * The total sum of distances is 12. 12 modulo 109 + 7 = 12.
     * 
     * 
     * 
     * Function Description
     * 
     * Complete the function getTotalDistance in the editor below.
     * 
     * 
     * 
     * getTotalDistance has the following parameter(s):
     * 
     * int g_nodes: the number of nodes in the tree
     * 
     * int[g_nodes-1] g_from : one end of the edges
     * 
     * int[g_nodes-1] g_to : another end of the edges
     * 
     * int[k] special_leaf : the special nodes
     * 
     * 
     * 
     * Returns
     * 
     * int: the total sum of distances to the special leaves modulo 109 + 7.
     * 
     * 
     * 
     * Constraints
     * 
     * 1 ≤ g_nodes ≤ 10^5
     * 1 ≤ g_from[i], g_to[i], special_leaf[i] ≤ g_nodes
     * |g_from| = |g_to| = g_nodes-1 where |x| denotes the length of the array x
     * 0 ≤ k ≤ g_nodes
     * 
     * INPUT FORMAT:
     * The first line contains two integers, g_nodes and g_edges (where g_edges =
     * g_nodes - 1), that denotes the number of nodes and number of edges
     * respectively.
     * 
     * Each line i of the g_nodes - 1 subsequent line (where 0 ≤ i < g_nodes - 1)
     * contains two integers, u and v, that denote an edge connecting the nodes u
     * and v.
     * 
     * The next line contains an integer k that represents the size of special_leaf
     * array.
     * 
     * The ith of the next k lines contains an integer that represents the ith
     * special leaf node.
     * 
     * TEST CASE 1:
     * STDIN FUNCTION
     * ----- --------
     * 5 4 → g_nodes = 5, g_edges = g_nodes - 1 = 4
     * 1 3 → g_from = [1, 1, 1, 5], g_to = [3, 5, 2, 4]
     * 1 5
     * 1 2
     * 5 4
     * 3 → special_leaf[] size k = 3
     * 2 → special_leaf = [2, 3, 4]
     * 3
     * 4
     * 
     * OUTPUT: 25
     * 
     * EXPLANATION: C:\Users\ganes\Pictures\leaf-distance-explanation.jpg
     */
    class Question2 {
        /*
         * Complete the 'getTotalDistance' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         * 1. UNWEIGHTED_INTEGER_GRAPH g
         * 2. INTEGER_ARRAY special_leaf
         */

        /*
         * For the unweighted graph, <name>:
         *
         * 1. The number of nodes is <name>Nodes.
         * 2. The number of edges is <name>Edges.
         * 3. An edge exists between <name>From[i] and <name>To[i].
         *
         */

        public static int getTotalDistance(int gNodes, List<Integer> gFrom, List<Integer> gTo,
                List<Integer> special_leaf) {

            return 0;
        }
    }

    // https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
    /**
     * Repeater Strings
     * 
     * A string is considered to be k-repeated if there is some character that
     * exists in every substring of length k. For example, "abcacba" is 3-repeated
     * because 'a' is present in every substring of length 3.
     * 
     * 
     * 
     * Given a string s of lowercase English letters, find the minimum possible
     * value of k such that the given string is k-repeated.
     * 
     * 
     * 
     * Note: A substring of a string is a contiguous section of the string.
     * 
     * 
     * 
     * 
     * 
     * Example
     * 
     * Consider s = "babdadd".
     * 
     * 
     * 
     * Considering each k-value,
     * 
     * For k = 1,
     * All the substrings of length 1 are: ["b", "a", "b", "d", "a", "d", "d"].
     * There is no character that is present in each substring of length 1.
     * For k = 2,
     * All the substrings of length 2 are: ["ba", "ab", "bd", "da", "ad", "dd"].
     * There is no character that is present in each substring of length 2.
     * For k = 3,
     * All the substrings of length 3 are: ["bab", "abd", "bda", "dad", "add"].
     * The character 'a' is present in each substring of length 3.
     * 
     * 
     * The smallest value of k for which the string is k-similar is 3. Return 3.
     * 
     * 
     * 
     * Function Description
     * 
     * Complete the function getSmallestKValue in the editor below.
     * 
     * 
     * 
     * getSmallestKValue has the following parameter:
     * 
     * string s: the string
     * 
     * 
     * 
     * Returns
     * 
     * int: the smallest value of k for which s is k-similar
     * 
     * 
     * 
     * Constraints
     * 
     * 1 ≤ length(s) ≤ 10^5
     * s consists of lowercase English letters only.
     * 
     */
    class Question3 {
        private static int check(String s, int k) {

            for (int ch = 0; ch < 26; ch++) {
                char c = (char) ('a' + ch);
                int l = -1;
                boolean flag = true;
                for (int i = 0; i < k; i++) {
                    char ch1 = s.charAt(i);
                    if (ch1 == c)
                        l = i;
                }
                if (l == -1)
                    continue;
                for (int i = k; i < s.length(); i++) {
                    char ch1 = s.charAt(i);
                    if (ch1 == c)
                        l = i;

                    if (l <= (i - k)) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    return 1;
            }
            return 0;
        }

        /*
         * Complete the 'getSmallestKValue' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING s as parameter.
         */
        public static int getSmallestKValue(String s) {
            // Write your code here
            int low = 1, high = s.length();
            int ans = 0;
            while (low <= high) {
                int mid = (high + low) / 2;
                if (check(s, mid) == 1) {
                    ans = mid;
                    high = mid - 1;
                } else
                    low = mid + 1;
            }
            return ans;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s = bufferedReader.readLine();

            int result = Result.getSmallestKValue(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}