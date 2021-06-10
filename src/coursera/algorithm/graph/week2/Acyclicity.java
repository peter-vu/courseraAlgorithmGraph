package coursera.algorithm.graph.week2;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {

        boolean isCyclic = false;
        for(int i = 0; i < adj.length; i++){
            boolean[] visited = new boolean[adj.length];
            isCyclic = dfs(i, adj, visited);
            if(isCyclic) break;
        }
        if(isCyclic) return 1;
        else {
            return 0;
        }
    }

    private static boolean dfs(int node, ArrayList<Integer>[] adj ,boolean[] visited) {
//        boolean isCyclic = false;
        if(visited[node]) return true;
        visited[node] = true;
        for(int next: adj[node]){
            if(dfs(next, adj, visited)){
                return true;
            }
        }
        visited[node] = false;

        return false;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week2/Acyclicity.txt"));
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
        }
        System.out.println(acyclic(adj));
    }
}

