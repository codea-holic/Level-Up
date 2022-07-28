package FaangList.ArraysNString;

public class Question001 {

    // 925. Long Pressed Name
    public boolean isLongPressedName(String name, String typed) {
        if(typed.length() < name.length()) return false;
        int i = 0, j = 0;
        while(i < name.length()){
            if(j < typed.length() && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            } else{
                if(i > 0 && j < typed.length() && typed.charAt(j) == name.charAt(i-1)) j++;
                else return false;
            }
        }
        while(j < typed.length()){
            if(typed.charAt(j) == name.charAt(i-1)) j++;
            else return false;
        }
        return true;
    }

    // 370. Range Addition
    public int[] getModifiedArray(int length, int[][] queries) {
        int [] result = new int[length];
        for(int i = 0; i < queries.length; i++){
            result[queries[i][0]] += queries[i][2];
            if(queries[i][1]+1 < length)
                result[queries[i][1]+1] += (-queries[i][2]);
        }
        
        for(int i = 1; i < length; i++){
            result[i] += result[i-1];
        }
        return result;
    }

    // 11. Container with most Water
    public int maxArea(int[] height) {
        
        int i = 0, n = height.length, j = n - 1, max = 0;
        while(i < j){
            int capacity = Math.min(height[i], height[j]) * (j - i);
            max = Math.max(capacity, max);
            if(height[i] > height[j]) j--;
            else i++;
        }
        return max;
    }

    
}
