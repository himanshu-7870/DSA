class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        long[] g = new long[maxVal + 1];
        
        for (int i = maxVal; i >= 1; i--) {
            long count = 0;
            for (int j = i; j <= maxVal; j += i) {
                count += freq[j];
            }
            
            long pairs = count * (count - 1) / 2;
            
            for (int j = 2 * i; j <= maxVal; j += i) {
                pairs -= g[j];
            }
            
            g[i] = pairs;
        }
        
        long[] pref = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            pref[i] = pref[i - 1] + g[i];
        }
        
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int left = 1;
            int right = maxVal;
            int res = maxVal;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (pref[mid] > q) {
                    res = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            answer[i] = res;
        }
        
        return answer;
    }
}