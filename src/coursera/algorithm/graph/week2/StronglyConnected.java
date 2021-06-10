package coursera.algorithm.graph.week2;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
        boolean[] visited = new boolean[adj.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < adj.length; i++){
            if(!visited[i]) {
                explore(i, adj, visited, stack);
            }
        }

        ArrayList<Integer>[] revAdj = getReverseGraph(adj);
        visited = new boolean[adj.length];
        int cnt = 0;
        while(!stack.isEmpty()) {
            int v = stack.pop();
            if(!visited[v]) {
                cnt++;
                explore(v,revAdj,visited);
            }
        }

        return cnt;
    }

    private static void explore(int v, ArrayList<Integer>[] adj, boolean[] visited) {
        visited[v] = true;
        for(int next: adj[v]){
            if(!visited[next]){
                explore(next, adj, visited);
            }
        }
    }

    private static void explore(int node, ArrayList<Integer>[] adj, boolean[] visited, LinkedList<Integer> stack) {
        visited[node] = true;
        for(int next: adj[node]){
            if(!visited[next]){
                explore(next, adj, visited, stack);
            }
        }
        stack.addFirst(node);
    }



    private static ArrayList<Integer>[] getReverseGraph(ArrayList<Integer>[] adj){
        ArrayList<Integer>[] revAdj = new ArrayList[adj.length];
        for(int i = 0; i < adj.length; i++) {
            revAdj[i] = new ArrayList<>();
        }
        for(int i = 0; i < adj.length; i++){
            for(int next: adj[i]){
                revAdj[next].add(i);
            }
        }

        return revAdj;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/coursera/algorithm/graph/week2/StronglyConnected.txt"));
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}


