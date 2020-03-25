package Structures.Graph;

public class WeightedGraph {
    private class Edge {
        int srcVertex;
        int destVertex;
        int weight;

        public Edge(int srcVertex, int destVertex, int weight) {
            this.srcVertex = srcVertex;
            this.destVertex = destVertex;
            this.weight = weight;
        }
    }

    private class Vertex {
        String name;
        Object content;
        boolean isInTree;

        public Vertex(String name, Object content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Vertex " + name + ": content = " + content;
        }
    }

    private class DistPar{
        int distance;
        int parentVertex;

        public DistPar(int distance, int parentVertex) {
            this.distance = distance;
            this.parentVertex = parentVertex;
        }
    }

    private class PriorityQueue {
        private static final int MAX_SIZE = 10;
        private int curSize;
        private Edge[] edges;

        public PriorityQueue() {
            edges = new Edge[MAX_SIZE];
        }

        public void insert(Edge edge) {
            if (curSize == MAX_SIZE) {
                System.out.println("Queue is full");
                return;
            }

            int idx;
            for (idx = 0; idx < curSize; idx++) {
                if (edge.weight >= edges[idx].weight) {
                    break;
                }
            }

            for (int i = curSize - 1; i >= idx; --i) {
                edges[i + 1] = edges[i];
            }

            edges[idx] = edge;
            ++curSize;
        }

        public Edge removeMin() {
            return edges[--curSize];
        }

        public void remove(int idx) {
            for (int i = idx; i < curSize - 1; i++) {
                edges[i] = edges[i + 1];
            }
        }

        public Edge peek() {
            return edges[curSize - 1];
        }

        public boolean isEmpty() {
            return curSize == 0;
        }

        public Edge peekAt(int idx) {
            return edges[idx];
        }

        public int find(int destIdx) {
            for (int i = 0; i < curSize; i++) {
                if (edges[i].destVertex == destIdx) {
                    return i;
                }
            }
            return -1;
        }
    }

    private static final int MAX_AMOUNT = 10;
    private Vertex[] vertexes;
    private int[][] adjMatrix;
    private int curAmount;
    private int curTreeSize;
    private static int current;
    private PriorityQueue priorityQueue;
    private DistPar[] sPath;
    private int startToCurrent;

    private boolean isOriented = false;

    public WeightedGraph() {
        this.vertexes = new Vertex[MAX_AMOUNT];
        this.adjMatrix = new int[MAX_AMOUNT][MAX_AMOUNT];
        this.priorityQueue = new PriorityQueue();
        this.sPath = new DistPar[MAX_AMOUNT];

        curAmount = 0;
        for (int i = 0; i < MAX_AMOUNT; i++) {
            for (int j = 0; j < MAX_AMOUNT; j++) {
                adjMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public WeightedGraph(boolean isOriented) {
        this();
        this.isOriented = isOriented;
    }

    public void addVertex(String name, Object content) {
        vertexes[curAmount++] = new Vertex(name, content);
    }

    public void addEdge(int start, int end, int weight) {
        adjMatrix[start][end] = weight;
        if (!isOriented) {
            adjMatrix[end][start] = weight;
        }
    }

    public void mst() {
        current = 0;

        while ((curTreeSize < curAmount - 1)) {
            vertexes[current].isInTree = true;
            ++curTreeSize;

            for (int i = 0; i < curAmount; i++) {
                int weight = adjMatrix[current][i];
                if (i == current || vertexes[i].isInTree || weight == Integer.MAX_VALUE) {
                    continue;
                }
                putInQueue(i, weight);
            }
            if (priorityQueue.isEmpty()) {
                System.out.println("Unable to build minimum spanning tree on disconnected graph");
                return;
            }
            Edge edge = priorityQueue.removeMin();
            int srcVertex = edge.srcVertex;
            current = edge.destVertex;

            System.out.print(vertexes[srcVertex].name + "" + vertexes[current].name + " ");
        }
        reset();
    }

    private void putInQueue(int idx, int weight) {
        int oldIdx = priorityQueue.find(idx);
        if (oldIdx != -1) {
            Edge temp = priorityQueue.peekAt(oldIdx);
            int oldWeight = temp.weight;
            if (oldWeight > weight) {
                priorityQueue.remove(oldIdx);
                priorityQueue.insert(new Edge(current, idx, weight));
            }
        } else {
            priorityQueue.insert(new Edge(current, idx, weight));
        }
    }

    public void floydAlgorithm() {
        for(int y = 0; y < curAmount; y++){
            for(int x = 0; x < curAmount; x++){
                for(int z = 0; z < curAmount; z++){
                    if(x != z && adjMatrix[x][y] != Integer.MAX_VALUE && adjMatrix[y][z] != Integer.MAX_VALUE){
                        if(adjMatrix[x][z] == Integer.MAX_VALUE)
                            adjMatrix[x][z] = adjMatrix[x][y] + adjMatrix[y][z];
                        else
                            adjMatrix[x][z] = Math.min(adjMatrix[x][z], adjMatrix[x][y] + adjMatrix[y][z]);
                    }
                }
            }
        }

        for (int i = 0; i < curAmount; i++) {
            for (int j = 0; j < curAmount; j++) {
                if (adjMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("- ");
                } else {
                    System.out.print(adjMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void dijkstraAlgorithm() {
        int startTree = 0;
        vertexes[startTree].isInTree = true;
        curTreeSize = 1;

        for (int i = 0; i < curAmount; i++) {
            int temp = adjMatrix[startTree][i];
            sPath[i] = new DistPar(temp, startTree);
        }

        while (curTreeSize < curAmount) {
            int minIdx = getMin();
            int minDist = sPath[minIdx].distance;

            if (minDist == Integer.MAX_VALUE) {
                System.out.println("Some vertexes are unreachable");
                break;
            } else {
                current = minIdx;
                startToCurrent = sPath[minIdx].distance;
                vertexes[current].isInTree = true;
                ++curTreeSize;
                adjustSPath();
            }

            displayPaths();

            reset();
        }
    }

    private int getMin() {
        int minDist = Integer.MAX_VALUE;
        int minIdx = 0;

        for (int i = 1; i < curAmount; i++) {
            if (!vertexes[i].isInTree && sPath[i].distance < minDist) {
                minDist = sPath[i].distance;
                minIdx = i;
            }
        }

        return minIdx;
    }

    private void adjustSPath() {
        int column = 1;

        while (column < curAmount) {
            if (vertexes[column].isInTree) {
                ++column;
                continue;
            }

            int curToCol = adjMatrix[current][column];
            int startToCol = startToCurrent = curToCol;
            int curDist = sPath[column].distance;

            if (startToCol < curDist) {
                sPath[column].parentVertex = current;
                sPath[column].distance = startToCol;
            }
            ++column;
        }
    }

    private void displayPaths() {
        for (int i = 0; i < curAmount; i++) {
            System.out.print(vertexes[i].name + " = ");
            if (sPath[i].distance == Integer.MAX_VALUE) {
                System.out.print("-");
            } else {
                System.out.print(sPath[i].distance);
            }
            String parent = vertexes[sPath[i].parentVertex].name;
            System.out.println(" (" + parent + ")");
        }
        System.out.println();
    }

    private void reset() {
        for (int i = 0; i < curAmount; i++) {
            vertexes[i].isInTree = false;
        }
    }

    public void print() {
        for (int i = 0; i < curAmount; i++) {
            System.out.println(vertexes[i]);
        }
    }
}
