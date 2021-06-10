package coursera.algorithm.graph.week3;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int size = adj.length;
        int[] distance = new int[size];
        for(int i = 0; i < size; i++) distance[i] = Integer.MAX_VALUE;
        distance[s] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{s, 0});
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int currNode = curr[0];
            int currDist = curr[1];
            if(currNode == t) break;
            for(int next: adj[currNode]) {
                if(distance[next] > currDist + 1){
                    distance[next] = currDist + 1;
                    queue.add(new int[]{next, currDist + 1});
                }
            }
        }
        if(distance[t] == Integer.MAX_VALUE) return -1;
        return distance[t];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week3/BFS.txt"));
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
        System.out.println(distance(adj, x, y));
    }
}

