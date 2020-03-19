package Structures.Tree23;

import Structures.Tree234.Tree234;

public class Tree23 {
    private class Item {
        int key;
        Object content;

        public Item(int key, Object content) {
            this.key = key;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Item: key = " + key + ", content = " + content;
        }
    }

    private class Node {
        private static final int ORDER = 3;

        int elementsAmount;
        Node parent;
        Item[] items = new Item[ORDER - 1];
        Node[] children = new Node[ORDER];

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder("Node:\n");
            for (int i = 0; i < ORDER - 1; i++) {
                if (items[i] != null) {
                    str.append(items[i].toString()).append("\n");
                } else {
                    str.append("Item: ---");
                }
            }
            return str.toString();
        }

        boolean isFull() {
            return elementsAmount == ORDER - 1;
        }

        boolean isLeaf() {
            return children[0] == null;
        }

        void connectChild(int idx, Node child) {
            children[idx] = child;
            if (child != null) {
                child.parent = this;
            }
        }

        Node disconnectChild(int idx) {
            Node temp = children[idx];
            children[idx] = null;
            return temp;
        }

        int insertElement(Item item) {
            elementsAmount++;

            for (int i = ORDER - 2; i >= 0; --i) {
                if (items[i] == null) {
                    continue;
                } else {
                    if (item.key < items[i].key) {
                        items[i + 1] = items[i];
                    } else {
                        items[i + 1] = item;
                        return i + 1;
                    }
                }
            }

            items[0] = item;
            return 0;
        }

        int find(int key) {
            for (int i = 0; i < ORDER - 1; i++) {
                if (items[i] == null) {
                    break;
                } else if (key == items[i].key) {
                    return i;
                }
            }
            return -1;
        }

        Item remove() {
            Item temp = items[elementsAmount - 1];
            items[elementsAmount - 1] = null;
            elementsAmount--;
            return temp;
        }
    }

    private static Node root;

    public Tree23() {
        root = new Node();
    }

    public void insert(int key, Object content) {
        Node current = root;
        while (true) {
            if (current.isLeaf()) {
                break;
            } else {
                current = getNextChild(current, key);
            }
        }

        Item newItem = new Item(key, content);

        if (current.isFull()) {
            split(current, newItem);
        } else {
            current.insertElement(new Item(key, content));
        }
    }

    private Node split(Node node, Item item) {

        Item rightItem = node.remove();
        Item leftItem = node.remove();
        Node rightChild = node.disconnectChild(2);
        Node leftChild = node.disconnectChild(1);
        Node parent;

        if (node == root) {
            root = new Node();
            parent = root;
            parent.connectChild(0, node);
        } else {
            parent = node.parent;
        }

        Node brother = new Node();

        int leftIdx = 0;
        int parentIdx = 0;
        int rightIdx = 0;
        Item nextLevelItem = null;
        Node newLeftChild = new Node();
        Node newRightChild = new Node();

        if (item.key < leftItem.key) {
            nextLevelItem = leftItem;
            newLeftChild.insertElement(item);
            newRightChild.insertElement(rightItem);
            leftIdx = node.insertElement(item);
            rightIdx = brother.insertElement(rightItem);

        } else if (item.key > leftItem.key && item.key < rightItem.key) {
            nextLevelItem = item;
            newLeftChild.insertElement(leftItem);
            newRightChild.insertElement(rightItem);
            leftIdx = node.insertElement(leftItem);
            rightIdx = brother.insertElement(rightItem);

        } else if (item.key > rightItem.key) {
            nextLevelItem = rightItem;
            newLeftChild.insertElement(leftItem);
            newRightChild.insertElement(item);
            leftIdx = node.insertElement(leftItem);
            rightIdx = brother.insertElement(item);
        }

        if (parent.isFull()) {
            parent = split(parent, nextLevelItem);
            parent.connectChild(0, newLeftChild);
            parent.connectChild(1, newRightChild);
        } else {
            parentIdx = parent.insertElement(nextLevelItem);
            node.connectChild(leftIdx + 1, leftChild);
            parent.connectChild(parentIdx + 1, brother);
            brother.connectChild(rightIdx + 1, rightChild);
        }

        return brother;
    }

    private Node getNextChild(Node node, int key) {
        int i;
        for (i = 0; i < node.elementsAmount; i++) {
            if (key < node.items[i].key) {
                return node.children[i];
            }
        }
        return node.children[i];
    }

    public void print() {

        printTree(root);
    }

    private void printTree(Node node) {
        System.out.println(node);

        for (int i = 0; i < node.elementsAmount + 1; i++) {
            Node next = node.children[i];
            if (next != null) {
                printTree(next);
            } else {
                return;
            }
        }
    }
}
