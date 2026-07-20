class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k = k % total;
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int new1D = i * n + j;
                int old1D = (new1D - k + total) % total;
                row.add(grid[old1D / n][old1D % n]);
            }
            result.add(row);
        }
        
        return result;
    }
}
