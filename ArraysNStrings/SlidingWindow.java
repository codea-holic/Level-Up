package ArraysNStrings;
import java.util.HashMap;

public class SlidingWindow {
    
    // Most of the sliding window question are in Class_001 file.
    // 525
    public int findMaxLength(int[] arr){
        // Your code here
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        int ei = 0, sum = 0, len = 0, n = arr.length;
        idxMap.put(0, -1);
        while(ei < n){
            sum = sum + (arr[ei] == 1 ? 1 : -1);
            int idx = idxMap.getOrDefault(sum, -2); // -2 indicate that not found
            if(idx == -2){
                idxMap.put(sum, ei);
            } else{
                len = Math.max(len, ei - idx);
            }
            ei++;
        }
        return len;
    }

    // 239


    // 454
    


}
