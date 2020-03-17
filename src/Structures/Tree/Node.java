package Structures.Tree;

public class Node {
    private int key;
    private Object value;
    private Node leftChild;
    private Node rightChild;

    public Node(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public int getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
