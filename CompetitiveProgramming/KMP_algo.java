package CompetitiveProgramming;

public class KMP_algo {
    
    public static void longestProperPrefix(char [] str, int [] lps){

        // Longest Proper prefix and Suffix...
        int i = 1, len = 0;
        while(i < str.length){

            if(str[i] == str[len]){
                len++;
                lps[i] = len;
                i++;
            } else{
                if(len > 0){
                    len = lps[len - 1];
                } else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }


}
