import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count frequency of every element in nums
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Case 1: k == n
        // Every element is part of the single existing subarray.
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 2: k == 1
        // Return the largest element that occurs exactly once in nums.
        if (k == 1) {
            int maxVal = -1;
            for (int num : nums) {
                if (freq.get(num) == 1) {
                    maxVal = Math.max(maxVal, num);
                }
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        // Only boundary elements (nums[0] and nums[n-1]) can appear in exactly 1 subarray.
        int maxVal = -1;
        if (freq.get(nums[0]) == 1) {
            maxVal = Math.max(maxVal, nums[0]);
        }
        if (freq.get(nums[n - 1]) == 1) {
            maxVal = Math.max(maxVal, nums[n - 1]);
        }

        return maxVal;
    }
}