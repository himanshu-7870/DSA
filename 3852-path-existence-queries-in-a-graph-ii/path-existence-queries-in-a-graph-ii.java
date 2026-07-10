import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Find unique sorted values from nums
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        int m = 0;
        
        // Remove duplicates to form our unique value array 'U'
        for (int i = 0; i < n; i++) {
            if (i == 0 || sortedNums[i] != sortedNums[i - 1]) {
                sortedNums[m++] = sortedNums[i];
            }
        }
        int[] U = Arrays.copyOf(sortedNums, m);

        // Step 2: Build the binary lifting (sparse) table
        int LOG = 18;
        int[][] up = new int[m][LOG];

        int right = 0;
        for (int left = 0; left < m; left++) {
            while (right + 1 < m && U[right + 1] - U[left] <= maxDiff) {
                right++;
            }
            up[left][0] = right;
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < m; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        // Step 3: Process the queries
        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            // If it's the exact same node, distance is 0
            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int valU = nums[u];
            int valV = nums[v];

            // If they are different nodes but have the identical value, 
            // it takes exactly 1 jump to connect them
            if (valU == valV) {
                ans[i] = 1;
                continue;
            }

            // Always jump from the smaller value to the larger value
            if (valU > valV) {
                int temp = valU;
                valU = valV;
                valV = temp;
            }

            int idxA = Arrays.binarySearch(U, valU);
            int idxB = Arrays.binarySearch(U, valV);

            // If the maximum reachable boundary from idxA is less than idxB, 
            // they are in disconnected components.
            if (up[idxA][LOG - 1] < idxB) {
                ans[i] = -1;
                continue;
            }

            int curr = idxA;
            int jumps = 0;

            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < idxB) {
                    curr = up[curr][j];
                    jumps += (1 << j);
                }
            }

            ans[i] = jumps + 1;
        }

        return ans;
    }
}