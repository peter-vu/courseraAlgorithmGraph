package coursera.algorithm.graph.week1;

import java.io.FileInputStream;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        boolean[] visited = new boolean[adj.length];
        visited[x] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(x);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int next: adj[curr]){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        if(visited[y]) return 1;
        else {
            return 0;
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week1/Reachability.txt"));
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}


