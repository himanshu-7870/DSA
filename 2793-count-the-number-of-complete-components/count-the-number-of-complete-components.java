import java.util.ArrayList;
import java.util.List;

class Solution {
    int nodes = 0;
    int edges = 0;

    public int countCompleteComponents(int n, int[][] edgesList) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] e : edgesList) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int res = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                nodes = 0;
                edges = 0;
                
                dfs(i, adj, vis);
                
                if (edges == nodes * (nodes - 1)) {
                    res++;
                }
            }
        }
        
        return res;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] vis) {
        vis[u] = true;
        nodes++;
        edges += adj.get(u).size();

        for (int v : adj.get(u)) {
            if (!vis[v]) {
                dfs(v, adj, vis);
            }
        }
    }
}