import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Lec_005_Sudoku {

    public static void solveSudoku(char[][] board) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list.add(i * n + j);
                }
            }
        }
        sudokuSolver(board, list, 0);
    }

    private static boolean isValid(char[][] board, int r, int c, int num) {

        // col
        for (int i = 0, j = c; i < 9; i++) {
            if (board[i][j] == (char) (num + '0'))
                return false;
        }

        // row
        for (int i = r, j = 0; j < 9; j++) {
            if (board[i][j] == (char) (num + '0'))
                return false;
        }

        // sub-matrix
        int si = r / 3 * 3;
        int sj = c / 3 * 3;
        for (int i = si; i < si + 3; i++) {
            for (int j = sj; j < sj + 3; j++) {
                if (board[i][j] == (char) (num + '0'))
                    return false;
            }
        }

        return true;
    }

    private static boolean sudokuSolver(char[][] board, ArrayList<Integer> list, int idx) {
        if (idx == list.size()) {
            return true;
        }

        int r = list.get(idx) / 9;
        int c = list.get(idx) % 9;
        for (int i = 1; i <= 9; i++) {
            if (isValid(board, r, c, i)) {
                board[r][c] = (char) ('0' + i);
                if (sudokuSolver(board, list, idx + 1))
                    return true;
                board[r][c] = '.';
            }
        }
        return false;
    }

    // 139 : word Break
    private boolean wordBreak(String s, String asf, HashSet<String> set) {

        for (int i = 0; i <= s.length(); i++) {
            String smallStr = s.substring(0, i);
            if (set.contains(smallStr)) {
                if (wordBreak(s.substring(i), asf + smallStr + " ", set)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        return wordBreak(s, "", set);
    }

    // Crypto
    // =====================================================================================================================================

    static String str1 = "send", str2 = "more", str3 = "money";
    static boolean[] isNumUsed = new boolean[10];
    static int[] mapping = new int[26];

    public static void crypto() {
        String str = str1 + str2 + str3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0)
                str += (char) (i + 'a');
        }
        if (str.length() > 10)
            return;

        crypto(str, 0);
    }

    public static boolean isValid() {
        int num1 = stringToNumber(str1);
        int num2 = stringToNumber(str2);
        int num3 = stringToNumber(str3);
        return (num1 + num2) == num3;
    }

    private static int stringToNumber(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0 && mapping[ch - 'a'] == 0){
                return -1;
            }
            res = res * 10 + mapping[ch - 'a'];
        }
        return res;
    }

    public static int crypto(String str, int idx) {
        if (idx == str.length()) {
            if (isValid()) {
                for (int i = 0; i < str.length(); i++) {
                    char ch = str.charAt(i);
                    System.out.print(ch + " = " + mapping[ch - 'a'] + ", ");
                }
                System.out.println();
                return 1;
            }
            return 0;
        }

        int count = 0;
        char ch = str.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if (!isNumUsed[num]) {
                mapping[ch - 'a'] = num;
                isNumUsed[num] = true;
                count += crypto(str, idx + 1);
                mapping[ch - 'a'] = 0;
                isNumUsed[num] = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        crypto();
    }
}

// 1307

class Solution {
    public boolean isSolvable(String[] words, String result) {
        HashSet<Character> hset = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                hset.add(ch);
            }
        }
        for (int i = 0; i < result.length(); i++) {
            char ch = result.charAt(i);
            hset.add(ch);
        }

        String str = new String();
        for (Character c : hset) {
            str += c;
        }

        boolean[] possibleNumbers = new boolean[10];
        HashMap<Character, Integer> map = new HashMap<>();
        int n = isSolvable(words, str, 0, possibleNumbers, map, result);
        return n == 0 ? false : true;
    }

    private int isSolvable(String[] words, String str, int idx, boolean[] p, HashMap<Character, Integer> map,
            String result) {
        // base case;
        if (idx == str.length()) {
            if (isValid(words, result, map)) {
                return 1;
            }
            return 0;
        }

        char c = str.charAt(idx);
        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (!p[i]) {
                p[i] = true;
                map.put(c, i);
                count += isSolvable(words, str, idx + 1, p, map, result);
                map.remove(c);
                p[i] = false;
            }
        }

        return count;
    }

    private boolean isValid(String [] words, String result, HashMap<Character, Integer> map){
        
        int res = 0;
        for(String word : words){
            if(map.get(word.charAt(0)) == 0) return false;
        }

        for(String word : words){
            int num = convertToNumber(word, map);
            res += num;
        }
        
        int resultVal = convertToNumber(result, map);
        return res == resultVal;
    }

    private int convertToNumber(String word, HashMap<Character, Integer> map) {
        int res = 0;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            res = res * 10 + map.get(ch);
        }
        return res;
    }
}
