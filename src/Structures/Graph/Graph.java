package Structures.Graph;

import Structures.LinearStructures.Queue;
import Structures.LinearStructures.Stack;

public class Graph {
    private class Vertex {
        String name;
        Object content;
        boolean isVisited = false;

        public Vertex(String name, Object content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Vertex " + name + ": content = " + content;
        }
    }

    private static final int MAX_AMOUNT = 10;
    private Vertex[] vertexes;
    private int[][] adjMatrix;
    private int curAmount;

    private boolean isOriented = false;

    public Graph() {
        this.vertexes = new Vertex[MAX_AMOUNT];
        this.adjMatrix = new int[MAX_AMOUNT][MAX_AMOUNT];

        curAmount = 0;
        for (int i = 0; i < MAX_AMOUNT; i++) {
            for (int j = 0; j < MAX_AMOUNT; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public Graph(boolean isOriented) {
        this();
        this.isOriented = isOriented;
    }

    public void addVertex(String name, Object content) {
        vertexes[curAmount++] = new Vertex(name, content);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        if (!isOriented) {
            adjMatrix[end][start] = 1;
        }
    }

    private int getAdjUnvisitedVertex(int vertexIdx) {
        for (int i = 0; i < curAmount; i++) {
            if (adjMatrix[vertexIdx][i] == 1 && !vertexes[i].isVisited) {
                return i;
            }
        }
        return -1;
    }

    public void dfs() {
        Stack stack = new Stack(MAX_AMOUNT);

        vertexes[0].isVisited = true;
        System.out.print(vertexes[0].name);
        stack.push(0);

        while (!stack.isEmpty()) {
            int next = getAdjUnvisitedVertex(stack.peek());
            if (next == -1) {
                stack.pop();
            } else {
                vertexes[next].isVisited = true;
                System.out.print(vertexes[next].name);
                stack.push(next);
            }
        }
        reset();
    }

    public void bfs() {
        Queue queue = new Queue();
        vertexes[0].isVisited = true;
        System.out.print(vertexes[0].name);
        queue.insert(0);
        int vertex2;

        while (!queue.isEmpty()) {
            int vertex1 = queue.remove();

            while ((vertex2 = getAdjUnvisitedVertex(vertex1)) != -1) {
                vertexes[vertex2].isVisited = true;
                System.out.print(vertexes[vertex2].name);
                queue.insert(vertex2);
            }
        }
        reset();
    }

    public void mst() {
        Stack stack = new Stack(MAX_AMOUNT);
        vertexes[0].isVisited = true;
        stack.push(0);

        while (!stack.isEmpty()) {
            int curVertex = stack.peek();
            int next = getAdjUnvisitedVertex(curVertex);
            if (next == -1) {
                stack.pop();
            } else {
                vertexes[next].isVisited = true;
                stack.push(next);

                System.out.print(vertexes[curVertex].name + "" + vertexes[next].name + " ");
            }
        }
        reset();
    }

    // Topological sort
    public void sort() {
        int initialAmount = curAmount;
        String[] sortedArray = new String[curAmount];

        while (curAmount > 0) {
            int next = noSuccessors();
            if (next == -1) {
                System.out.println("Cyclic graph can not be topologically sorted");
                return;
            }

            sortedArray[curAmount - 1] = vertexes[next].name;
            deleteVertex(next);
        }

        for (int i = 0; i < initialAmount; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }

    private int noSuccessors() {
        boolean isEdge;
        for (int rows = 0; rows < curAmount; rows++) {
            isEdge = false;
            for (int cols = 0; cols < curAmount; cols++) {
                if (adjMatrix[rows][cols] > 0) {
                    isEdge = true;
                    break;
                }
            }

            if (!isEdge) {
                return rows;
            }
        }
        return -1;
    }

    public void deleteVertex(int vertex) {
        if (vertex != curAmount - 1) {
            for (int i = vertex; i < curAmount; i++) {
                vertexes[i] = vertexes[i + 1];
            }

            for (int row = vertex; row < curAmount - 1; row++) {
                moveRowUp(row, curAmount);
            }

            for (int col = vertex; col < curAmount - 1; col++) {
                moveRowUp(col, curAmount - 1);
            }
        }
        --curAmount;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++) {
            adjMatrix[row][col] = adjMatrix[row + 1][col];
        }
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++) {
            adjMatrix[row][col] = adjMatrix[row][col + 1];
        }
    }

    public void warshallsAlgorithm() {
        for (int y = 0; y < curAmount; y++) {
            for (int x = 0; x < curAmount; x++) {
                if (adjMatrix[y][x] == 1) {
                    for (int z = 0; z < curAmount; z++) {
                        if (adjMatrix[z][y] == 1) {
                            adjMatrix[x][z] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < curAmount; i++) {
            for (int j = 0; j < curAmount; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void reset() {
        for (int i = 0; i < curAmount; i++) {
            vertexes[i].isVisited = false;
        }
    }

    public void print() {
        for (int i = 0; i < curAmount; i++) {
            System.out.println(vertexes[i]);
        }
    }
}