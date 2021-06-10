package coursera.algorithm.graph.week5;

import java.util.*;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        DisjointSet<Point> disjointSet = new DisjointSet<>();
        for(int i = 0; i < x.length;i++) {
            disjointSet.makeSet(new Point(x[i],y[i]));
        }

        TreeMap<Double, List<Pair>> queue = new TreeMap<>();
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x.length && j != i; j++) {
                double distance = distance(x, y, i, j);
                if(queue.get(distance) == null) {
                    queue.put(distance, new ArrayList<>());
                }
                queue.get(distance).add(new Pair(new Point(x[i], y[i]), new Point(x[j],y[j])));
            }
        }
        int vertexCount = 1;
        List<Double> edges = new ArrayList<>();
        while(vertexCount < x.length) {
            Map.Entry<Double, List<Pair>> current = queue.pollFirstEntry();
            double currentDistance = current.getKey();
            List<Pair> currentList = current.getValue();

            for(Pair pair: currentList) {
                if (!disjointSet.findSet(pair.first).equals(disjointSet.findSet(pair.second))) {
                    disjointSet.union(pair.first, pair.second);
                    edges.add(currentDistance);
                    vertexCount++;
                }
            }
        }
        Collections.sort(edges);
        Collections.reverse(edges);
        return edges.get(k - 2);
    }

    private static double distance(int[] x, int[] y, int i, int j) {
        return Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Pair {
        Point first;
        Point second;

        public Pair(Point first, Point second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    static class DisjointSet<T> {
        private static class Node<T> {
            int rank;
            T parent;
            Node(T parent, int rank) {
                this.parent = parent;
                this.rank = rank;
            }
        }

        private final HashMap<T, DisjointSet.Node<T>> objectsToNodes = new HashMap<>();

        public T findSet(Object o) {
            DisjointSet.Node<T> node = objectsToNodes.get(o);
            if(node == null) return null;
            if(o != node.parent) {
                node.parent = findSet(node.parent);
            }
            return node.parent;
        }

        public void makeSet(T o) {
            objectsToNodes.put(o, new DisjointSet.Node<>(o, 0));
        }

        public void removeSet(Object o) {
            Object set = findSet(o);
            if(set == null) return;
            for (Iterator<T> it = objectsToNodes.keySet().iterator(); it.hasNext(); ) {
                T next = it.next();
                //remove the set representative last, otherwise findSet will fail
                if (next != set && findSet(next) == set) {
                    it.remove();
                }
            }
            objectsToNodes.remove(set);
        }

        public void toList(List<? super T> list) {
            list.addAll(objectsToNodes.keySet());
        }

        public void union(T x, T y) {
            T setX = findSet(x);
            T setY = findSet(y);
            if (setX == null || setY == null || setX == setY) {
                return;
            }
            DisjointSet.Node<T> nodeX = objectsToNodes.get(setX);
            DisjointSet.Node<T> nodeY = objectsToNodes.get(setY);
            //join the two sets by pointing the root of one at the root of the other
            if (nodeX.rank > nodeY.rank) {
                nodeY.parent = x;
            } else {
                nodeX.parent = y;
                if (nodeX.rank == nodeY.rank) {
                    nodeY.rank++;
                }
            }
        }
    }
}

