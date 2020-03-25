package Structures.Graph;

public class WeightedGraphTest {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(true);
        graph.addVertex("A", "A");
        graph.addVertex("B", "B");
        graph.addVertex("C", "C");
        graph.addVertex("D", "D");
        graph.addVertex("E", "E");
        graph.addVertex("F", "F");

        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 3, 4);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 7);
        graph.addEdge(1, 4, 7);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 5);
        graph.addEdge(2, 5, 6);
        graph.addEdge(3, 4, 12);
        graph.addEdge(4, 5, 7);

        System.out.println("Minimum spanning tree for a weighted graph:");
        graph.mst();

        System.out.println("Floyd's algorithm:");
        graph.floydAlgorithm();

        System.out.println("Dijkstra's algorithm:");
        graph.dijkstraAlgorithm();
    }
}
