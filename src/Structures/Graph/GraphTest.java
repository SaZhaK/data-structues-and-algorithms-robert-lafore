package Structures.Graph;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.println("Insert:");
        graph.addVertex("A", "A");
        graph.addVertex("B", "B");
        graph.addVertex("C", "C");
        graph.addVertex("D", "D");
        graph.addVertex("E", "E");
        graph.addVertex("F", "F");

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,5);
        graph.addEdge(2,3);
        graph.addEdge(3,4);

        graph.print();
        System.out.println("Depth-first search");
        graph.dfs();

        System.out.println("\nBreadth-first search");
        graph.bfs();

        System.out.println("\nMinimum spanning tree:");
        graph.mst();

        System.out.println("\nWarshalls algorithm:");
        graph.warshallsAlgorithm();

        System.out.println();
        Graph graph2 = new Graph(true);
        graph2.addVertex("A", "A");
        graph2.addVertex("B", "B");
        graph2.addVertex("C", "C");
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        System.out.println("\nTopologically sorted graph:");
        graph2.sort();
    }
}