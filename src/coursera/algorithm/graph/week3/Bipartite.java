package coursera.algorithm.graph.week3;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        int size = adj.length;
        int[] colors = new int[size];
        for(int i = 0; i < size; i++) colors[i] = -1;
        for(int i = 0; i < size; i++){
            if(colors[i] == -1) {
                int result = bipartite(adj, i, colors);
                if(result == 0) return 0;
            }
        }
        return 1;
    }

    private static int bipartite(ArrayList<Integer>[] adj, int startNode, int[] colors) {
        //write your code here
//        int size = adj.length;
//        int[] colors = new int[size];
//        for(int i = 0; i < size; i++) colors[i] = -1;
        colors[startNode] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            for(int next: adj[currNode]){
                if(colors[next] == -1) {
                    colors[next] = 1 - colors[currNode];
                    queue.add(next);
                }
                else if (colors[next] == colors[currNode]){
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week3/Bipartite.txt"));
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
        System.out.println(bipartite(adj));
    }
}


