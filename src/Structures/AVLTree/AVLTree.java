package Structures.AVLTree;

public class AVLTree {
    private class Node {
        Comparable key;
        Object content;
        Node left;
        Node right;
        int height;

        public Node(Comparable key, Object content) {
            this(key, content, null, null);
        }

        public Node(Comparable key, Object content, Node left, Node right) {
            this.key = key;
            this.content = content;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        @Override
        public String toString() {
            return "Node key = " + key + ", content = " + content;
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
    private Node nullNode;

    private static Node parent;
    private static Node current;

    {
        nullNode = new Node(-1, null);
        nullNode.left = nullNode.right = nullNode;
    }

    public AVLTree() {
        this.root = new Node(-1, null);
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
        current = parent = root;
        nullNode.key = key;
        nullNode.content = content;

        while (current.key != key) {
            parent = current;

            current.height++;

            current = compare(key, current) < 0 ? current.left : current.right;
        }

        current = new Node(key, content, nullNode, nullNode);

        if (compare(key, current) < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }
        balance(current);
    }

    int balanceFactor(Node node) {
        return node.right.height - node.left.height;
    }

    private void balance(Node node) {
        fixHeight(node);

        if (balanceFactor(node) == 2) {

            if (balanceFactor(node.right) < 0) {
                node.right = rightRotate(node.right);
            }
            leftRotate(node);
        }

        if (balanceFactor(node) == -2) {

            if (balanceFactor(node.left) > 0) {
                node.left = leftRotate(node.left);
            }
            rightRotate(node);
        }

    }

    private void fixHeight(Node node) {
        int leftHeight = node.left.height;
        int rightHeight = node.right.height;

        node.height = (Math.max(leftHeight, rightHeight)) + 1;
    }

    private Node leftRotate(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        fixHeight(node);
        fixHeight(left);

        return left;
    }

    private Node rightRotate(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        fixHeight(node);
        fixHeight(right);

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

    public void clear() {
        root.right = nullNode;
    }

    public boolean isEmpty() {
        return root.right == nullNode;
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
