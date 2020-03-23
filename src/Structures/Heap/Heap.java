package Structures.Heap;

public class Heap {

    private Node[] array;
    private int maxSize = 10;
    private int currentSize;

    public Heap() {
        array = new Node[maxSize];
        currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public void incrementSize() {
        ++currentSize;
    }

    public void insertAt(int idx, int key, Object content) {
        array[idx] = new Node(key, content);
    }

    public boolean insert(int key, Object content) {
        if (isFull()) {
            return false;
        }
        Node newNode = new Node(key, content);
        array[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public Node remove() {
        if (isEmpty()) {
            return null;
        }

        Node root = array[0];
        array[0] = array[--currentSize];
        trickleDown(0);
        return root;
    }

    public boolean change(int idx, int key, Object content) {
        if (idx == 0 || idx >= currentSize) {
            return false;
        }
        Node oldNode = array[idx];
        array[idx] = new Node(key, content);

        if (oldNode.getKey() < key) {
            trickleUp(idx);
        } else {
            trickleDown(idx);
        }

        return true;
    }

    public void trickleUp(int idx) {
        int parentIdx = (idx - 1) / 2;
        Node bottom = array[idx];

        while (idx > 0 && array[parentIdx].getKey() < array[idx].getKey()) {
            array[idx] = array[parentIdx];
            idx = parentIdx;
            parentIdx = (parentIdx - 1) / 2;
        }

        array[idx] = bottom;
    }

    public void trickleDown(int idx) {
        Node top = array[idx];
        int largerChild;

        while (idx < currentSize / 2) {
            int leftChild = 2 * idx + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && array[rightChild].getKey() > array[leftChild].getKey()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.getKey() >= array[largerChild].getKey()) {
                break;
            }

            array[idx] = array[largerChild];
            idx = largerChild;
        }

        array[idx] = top;
    }

    public void print() {
        for (int i = 0; i < currentSize; i++) {
            System.out.println(array[i] + " ");
        }
    }
}

class Node {
    private int key;
    private Object content;

    public Node(int key, Object content) {
        this.key = key;
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Node: content = " + content;
    }
}
