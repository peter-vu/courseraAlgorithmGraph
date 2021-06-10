package coursera.algorithm.graph.week4;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        final int length = adj.length;
        int[] distance = new int[length];
        for(int i = 0; i < length; i++) {
            distance[i] = Integer.MAX_VALUE / 2;
        }
        distance[0] = 0;
        for(int n = 0; n < length; n++){
            // compute
            for(int i = 0; i < length; i++){
                for(int j = 0; j < adj[i].size(); j++){
                    int nextNode = adj[i].get(j);
                    int nextCost = cost[i].get(j);
                    if(distance[nextNode] > distance[i] + nextCost) {
                        distance[nextNode] = distance[i] + nextCost;
                        // distance is still improved after n-1 rounds, so it is cyclic
                        if(n == adj.length - 1) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}