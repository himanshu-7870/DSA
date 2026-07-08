class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1000000007;
        
        // ... rest of the code remains exactly the same ...

        // 1. Compress string and find valid non-zero indices
        int[] pos = new int[n];
        int k = 0; // Count of non-zero digits
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                pos[i] = k++;
            } else {
                pos[i] = -1;
            }
        }

        // prev[i] stores the compressed index of the closest non-zero digit <= i
        int[] prev = new int[n];
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (pos[i] != -1) last = pos[i];
            prev[i] = last;
        }

        // next[i] stores the compressed index of the closest non-zero digit >= i
        int[] next = new int[n];
        last = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (pos[i] != -1) last = pos[i];
            next[i] = last;
        }

        // 2. Precompute Prefix Arrays
        long[] pow10 = new long[k + 1];
        pow10[0] = 1;
        for (int i = 1; i <= k; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        long[] prefSum = new long[k + 1];
        long[] prefConcat = new long[k + 1];
        
        int currK = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                int d = s.charAt(i) - '0';
                prefSum[currK + 1] = prefSum[currK] + d;
                prefConcat[currK + 1] = (prefConcat[currK] * 10 + d) % MOD;
                currK++;
            }
        }

        // 3. Process Queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0];
            int R = queries[i][1];

            int left = next[L];
            int right = prev[R];

            // If range is invalid or contains no non-zero digits
            if (left == -1 || right == -1 || left > right) {
                ans[i] = 0;
            } else {
                int len = right - left + 1;
                long sum = prefSum[right + 1] - prefSum[left];
                
                // Extract compressed value X utilizing prefix concatenation
                long x = (prefConcat[right + 1] - (prefConcat[left] * pow10[len]) % MOD) % MOD;
                
                // Handle negative mod results
                if (x < 0) {
                    x += MOD;
                }

                ans[i] = (int)((x * (sum % MOD)) % MOD);
            }
        }

        return ans;
    }
}