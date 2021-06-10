package coursera.algorithm.graph.week4;

import java.io.FileInputStream;
import java.util.*;

public class Dijkstra {
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        final int length = adj.length;
        long[] distance = new long[length];
        for(int i = 0; i < length; i++) distance[i] = Long.MAX_VALUE;
        distance[s] = 0;
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        queue.add(new long[]{s, 0});
        while(!queue.isEmpty()) {
            long[] curr = queue.poll();
            int currNode = (int) curr[0];
            long currCost = curr[1];
            if(currNode == t) break;
            for(int i = 0; i < adj[currNode].size(); i++) {
                int nextNode = adj[currNode].get(i);
                int nextCost = cost[currNode].get(i);
                long sum = currCost + nextCost;
                if(distance[nextNode] > sum) {
                    distance[nextNode] = sum;
                    queue.add(new long[]{nextNode, sum});
                }
            }
        }
        if(distance[t] != Long.MAX_VALUE) return distance[t];
        else {
            return -1;
        }
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week4/Dijkstra.txt"));
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}


