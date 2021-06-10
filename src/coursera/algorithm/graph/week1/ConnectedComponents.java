package coursera.algorithm.graph.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        boolean[] visited = new boolean[adj.length];
        for(int i = 0; i < adj.length; i++) {
            if(!visited[i]) {
                result++;
                dfs(i, adj,visited);
            }
        }
        return result;
    }

    static void dfs(int start, ArrayList<Integer>[] adj, boolean[] visited) {
        visited[start] = true;
        for(int next: adj[start]){
            if(!visited[next]){
                dfs(next, adj,visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}


