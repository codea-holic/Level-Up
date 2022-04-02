import java.util.*;

public class Lec_003_Problems {

    // 40. Combination Sum
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, ans, new ArrayList<>());
        return ans;
    }

    private int combinationSum2(int[] coins, int tar, int idx, List<List<Integer>> ans, List<Integer> sAns) {
        if (tar == 0) {
            List<Integer> bres = new ArrayList<>(sAns);
            ans.add(bres);
            return 1;
        }

        int count = 0, prev = -1;
        for (int i = idx; i < coins.length; i++) {
            if (prev != coins[i] && tar - coins[i] >= 0) {
                sAns.add(coins[i]);
                count += combinationSum2(coins, tar - coins[i], i + 1, ans, sAns);
                sAns.remove(sAns.size() - 1);
                prev = coins[i];
            } else if (tar - coins[i] < 0) {
                break;
            }
        }
        return count;
    }

    // 17. Letter Combinations of a Phone Number
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        String[] codes = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        letterCombinations(digits, 0, codes, ans, "");
        return ans;
    }
    
    private int letterCombinations(String digits, int idx, String [] codes, List<String> ans, String sAns) {
        if(idx == digits.length()){
            ans.add(new String(sAns));
            return 1;
        }
        int count = 0;
        int index = (int)(digits.charAt(idx) - '0');
        String code = codes[index];
        for(int i = 0; i < code.length(); i++){
            char ch = code.charAt(i);
            letterCombinations(digits, idx + 1, codes, ans, sAns + ch);
        }
        return count;
    }

    public static void main(String[] args) throws Exception{
        Lec_003_Problems l = new Lec_003_Problems();
        l.letterCombinations("23");
    }
}
