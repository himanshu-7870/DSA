import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        // Master string containing all possible sequential digits
        String digits = "123456789";
        
        // The minimum length of a sequential number is 2 (since low >= 10)
        // The maximum length is 9
        for (int length = 2; length <= 9; length++) {
            // Slide a window of size 'length' across the master string
            for (int i = 0; i <= 9 - length; i++) {
                // Extract the substring and convert it to an integer
                String sub = digits.substring(i, i + length);
                int num = Integer.parseInt(sub);
                
                // If the number falls within the range, add it to our results
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}