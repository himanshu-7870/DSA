class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1000000007;
        int maxVal = 200; // As per the problem constraints, nums[i] <= 200
        
        // dp[g1][g2] stores the number of valid pairs of subsequences
        // with GCD g1 and g2. 0 represents an empty subsequence.
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        
        // Base case: Both subsequences are initially empty
        dp[0][0] = 1;
        
        for (int x : nums) {
            // Create a new DP array for the current state to avoid 
            // reading updated states from the same iteration
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    int currentCount = dp[g1][g2];
                    
                    // Choice 1: Skip the current element (don't add to either)
                    nextDp[g1][g2] = (nextDp[g1][g2] + currentCount) % MOD;
                    
                    // Choice 2: Add the current element to the first subsequence
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    nextDp[ng1][g2] = (nextDp[ng1][g2] + currentCount) % MOD;
                    
                    // Choice 3: Add the current element to the second subsequence
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    nextDp[g1][ng2] = (nextDp[g1][ng2] + currentCount) % MOD;
                }
            }
            dp = nextDp; // Move to the next iteration
        }
        
        int ans = 0;
        // Aggregate all valid pairs where both subsequences are non-empty
        // and have equal GCDs (g1 == g2 and both > 0)
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        
        return ans;
    }
    
    // Helper function to calculate Greatest Common Divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}