package Structures.Heap;

import java.util.ArrayList;
import java.util.List;

public class HeapTree {
    private class Node {
        int key;
        Object content;

        Node parent;
        Node left;
        Node right;

        public Node(int key, Object content) {
            this.key = key;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Node: key = " + key + ", content = " + content;
        }
    }

    private Node root;
    private int currentSize;

    public void insert(int key, Object content) {
        ++currentSize;
        Node newNode = new Node(key, content);

        if (root == null) {
            root = newNode;
            newNode.parent = null;
        } else {
            List<Integer> path = getPath();
            Node current = root;

            for (int i = path.size() - 2; i > 0; --i) {
                if (path.get(i) == 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (path.get(0) == 0) {
                current.left = newNode;
                current.left.parent = current;
                trickleUp(current.left);
            } else {
                current.right = newNode;
                current.right.parent = current;
                trickleUp(current.right);
            }
        }
    }

    private List<Integer> getPath() {
        int idx = currentSize;
        List<Integer> path = new ArrayList<>();

        while (idx > 0) {
            path.add(idx % 2);
            idx /= 2;
        }
        return path;
    }

    public void remove() {
        if (root == null) {
            return;
        } else {
            List<Integer> path = getPath();
            Node current = root;

            for (int i = 1; i < path.size() - 1; ++i) {
                if (path.get(i) == 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            Node top;
            if (path.get(0) == 0) {
                top = new Node(current.left.key, current.left.content);
                current.left = null;
            } else {
                top = new Node(current.right.key, current.right.content);
                current.right = null;
            }

            top.left = root.left;
            top.right = root.right;

            if (top.left != null) {
                top.left.parent = top;
            }
            if (top.right != null) {
                top.right.parent = top;
            }

            trickleDown(top);
        }
    }

    private void trickleUp(Node node) {
        Node bottom = new Node(node.key, node.content);
        Node parent = node.parent;
        bottom.parent = parent;

        while (parent != null && parent.key < node.key) {
            node.key = parent.key;
            node.content = parent.content;
            if (parent.parent != null) {
                parent.key = parent.parent.key;
                parent.content = parent.parent.content;
            }
            node = parent;
            parent = parent.parent;
        }

        node.key = bottom.key;
        node.content = bottom.content;

        if (bottom == bottom.parent.left) {
            bottom.parent.left = null;
        } else if (bottom == bottom.parent.right) {
            bottom.parent.right = null;
        }
    }

    private void trickleDown(Node node) {
        Node top = new Node(node.key, node.content);
        Node largerChild;
        Node current = node;

        while (current.left != null && current.right != null) {
            if (current.key > current.left.key) {
                largerChild = current.right;
            } else {
                largerChild = current.left;
            }

            if (top.key >= largerChild.key) {
                break;
            }

            current.key = largerChild.key;
            current.content = largerChild.content;

            current = largerChild;
        }

        current.key = top.key;
        current.content = top.content;
    }

    public void print() {
        printTree(root);
    }

    private void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node);
            printTree(node.right);
        }
    }
}
