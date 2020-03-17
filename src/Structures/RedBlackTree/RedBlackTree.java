package Structures.RedBlackTree;

public class RedBlackTree {
    private static class Node {
        Comparable key;
        Object content;
        int color;
        Node left;
        Node right;

        public Node(Comparable key, Object content) {
            this(key, content, null, null);
        }

        public Node(Comparable key, Object content, Node left, Node right) {
            this.key = key;
            this.content = content;
            this.left = left;
            this.right = right;
            this.color = BLACK;
        }

        @Override
        public String toString() {
            return "Node: key = " + key + ", content = " + content;
        }
    }

    private class EmptyTreeException extends RuntimeException {
        public EmptyTreeException() {
        }

        public EmptyTreeException(String message) {
            super(message);
        }
    }

    private Node root;
    private static Node nullNode;

    private static Node great;
    private static Node grand;
    private static Node parent;
    private static Node current;

    private static int BLACK = 1;
    private static int RED = 0;

    static {
        nullNode = new Node(null, null);
        nullNode.left = nullNode.right = nullNode;
    }

    public RedBlackTree() {
        this.root = new Node(null, null);
        root.left = root.right = nullNode;
    }

    private int compare(Comparable key, Node node) {
        if (node == root) {
            return 1;
        } else {
            return key.compareTo(node.key);
        }
    }

    public void insert(Comparable key, Object content) {
        current = parent = grand = root;
        nullNode.key = key;
        nullNode.content = content;

        while (compare(key, current) != 0) {
            great = grand;
            grand = parent;
            parent = current;

            current = compare(key, current) < 0 ? current.left : current.right;

            if (current.left.color == RED && current.right.color == RED) {
                fixInsertion(key);
            }
        }

        current = new Node(key, content, nullNode, nullNode);

        if (compare(current.key, parent) < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }
        fixInsertion(key);
    }

    private void fixInsertion(Comparable key) {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            great.color = RED;
            if (compare(key, grand) != compare(key, parent)) {
                parent = rotate(key, grand);
            }
            current = rotate(key, great);
            current.color = BLACK;
        }
        root.right.color = BLACK;
    }

    private Node rotate(Comparable key, Node parent) {
        if (compare(key, parent) < 0) {
            return parent.left = compare(key, parent.left) < 0 ? leftRotate(parent.left) : rightRotate(parent.left);
        } else {
            return parent.right = compare(key, parent.right) < 0 ? leftRotate(parent.right) : rightRotate(parent.right);
        }
    }

    private Node leftRotate(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    private Node rightRotate(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    public void print() {
        printTree(root.right);
    }

    private void printTree(Node node) {
        if (node != nullNode) {
            printTree(node.left);
            System.out.println(node);
            printTree(node.right);
        }
    }

    public Node getMin() {
        current = root.right;
        while (current.left != nullNode) {
            current = current.left;
        }
        return current;
    }

    public Node getMax() {
        current = root.right;
        while (current.right != nullNode) {
            current = current.right;
        }
        return current;
    }

    public boolean isEmpty() {
        return root == nullNode;
    }

    public void clear() {
        root = nullNode;
    }

    public Node find(Comparable key) {
        if (this.isEmpty()) throw new EmptyTreeException();
        current = root.right;

        while (true) {
            if (current.key == key) {
                return current;
            } else if (compare(key, current) < 0) {
                current = current.left;
            } else if (compare(key, current) > 0) {
                current = current.right;
            } else return null;
        }
    }
}
