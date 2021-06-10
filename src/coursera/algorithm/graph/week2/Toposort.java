package coursera.algorithm.graph.week2;

import java.io.FileInputStream;
import java.util.*;
import java.util.stream.IntStream;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        for(int i = 0; i < adj.length; i++) {
            if(used[i] == 0){
                toposortUtil(i, used, adj, order);
            }
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = adj.length -1 ; i >=0; i --){
            temp.add(order.get(i));
        }
        return temp;
    }

    private static void toposortUtil(int v, int[] used, ArrayList<Integer>[] adj, ArrayList<Integer> order) {
        used[v] = 1;
        for(int next: adj[v]) {
            if(used[next] == 0){
                toposortUtil(next, used, adj, order);
            }
        }
        order.add(v);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week2/Toposort.txt"));
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

