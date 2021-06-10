package coursera.algorithm.graph.week4;

import java.io.FileInputStream;
import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        distance[s] = 0;
        List<Integer> reachableVertex = dfs(adj, s);
        reachableVertex.stream().forEach(x -> reachable[x] = 1);

        List<Integer> cycle = new ArrayList<>();

        for(int i = 0; i < adj.length; i++) {
            for(int curr: reachableVertex) {
                for(int k = 0; k < adj[curr].size();k++) {
                    int currentNeighbour = adj[curr].get(k);
                    int currentCost = cost[curr].get(k);

                    if(distance[currentNeighbour] > distance[curr] + currentCost) {
                        distance[currentNeighbour] = distance[curr] + currentCost;
                        if(i == adj.length - 1) {
                            shortest[currentNeighbour] = 0;
                            cycle.add(currentNeighbour);
                        }
                    }
                }
            }
        }

        Set<Integer> allNonReachable = new HashSet<>();
        if(!cycle.isEmpty()) {
            allNonReachable.addAll(dfs(adj,cycle.get(0)));
            allNonReachable.stream().forEach(x -> shortest[x] = 0);
        }
    }

    private static List<Integer> dfs(ArrayList<Integer>[] adj, int s) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];

        queue.add(s);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(!visited[curr]) {
                visited[curr] = true;
                result.add(curr);
                for(int next: adj[curr]) {
                    queue.add(next);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week4/ShortestPaths.txt"));
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}


