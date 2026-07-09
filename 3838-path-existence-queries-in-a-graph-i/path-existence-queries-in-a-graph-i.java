class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // This array will store the component ID for each node
        int[] component = new int[n];
        int currentComponentId = 0;
        
        // Group nodes into components based on maxDiff
        for (int i = 1; i < n; i++) {
            // If the difference exceeds maxDiff, the chain breaks; start a new component
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentComponentId++;
            }
            component[i] = currentComponentId;
        }
        
        // Process queries
        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            // If u and v have the same component ID, a path exists
            result[i] = (component[u] == component[v]);
        }
        
        return result;
    }
}