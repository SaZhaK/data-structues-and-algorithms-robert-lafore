package Structures.Tree234;

public class Tree234 {

    private class Item {
        Comparable key;
        Object content;

        public Item(Comparable key, Object content) {
            this.key = key;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Item: key = " + key + ", content = " + content;
        }
    }

    private class Node {
        private static final int ORDER = 4;

        int elementAmount = 0;
        Node parent;
        Item[] items = new Item[ORDER - 1];
        Node[] children = new Node[ORDER];

        @Override
        public String toString() {
            StringBuilder nodeStr = new StringBuilder("Node:\n");
            for (int i = 0; i < ORDER - 1; i++) {
                if (items[i] != null) {
                    nodeStr.append(items[i].toString()).append("\n");
                } else {
                    nodeStr.append("Item: --- \n");
                }
            }
            return nodeStr.toString();
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

        int findElement(Comparable key) {
            for (int i = 0; i < ORDER - 1; i++) {
                if (items[i] == null) {
                    break;
                } else if (key.compareTo(items[i].key) == 0) {
                    return i;
                }
            }
            return -1;
        }

        boolean isFull() {
            return elementAmount == ORDER - 1;
        }

        boolean isLeaf() {
            return children[0] == null;
        }

        int insert(Item item) {
            elementAmount++;

            for (int i = ORDER - 2; i >= 0; --i) {
                if (items[i] == null) {
                    continue;
                } else {
                    if (item.key.compareTo(items[i].key) < 0) {
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

        Item remove() {
            Item temp = items[elementAmount - 1];
            items[elementAmount - 1] = null;
            elementAmount--;
            return temp;
        }
    }

    private static Node root;

    public Tree234() {
        root = new Node();
    }

    public Node find(Comparable key) {
        Node current = root;
        while (true) {
            if (current.findElement(key) != -1) {
                return current;
            } else if (current.isLeaf()) {
                return null;
            } else {
                current = getNextChild(current, key);
            }
        }
    }

    public void insert(Comparable key, Object content) {
        Node current = root;

        while (true) {
            if (current.isFull()) {
                split(current);
                current = current.parent;

                current = getNextChild(current, key);
            } else if (current.isLeaf()) {
                break;
            } else {
                current = getNextChild(current, key);
            }
        }
        current.insert(new Item(key, content));
    }

    public void split(Node node) {
        Item itemC = node.remove();
        Item itemB = node.remove();
        Node child2 = node.disconnectChild(2);
        Node child3 = node.disconnectChild(3);
        Node parent;

        if (node == root) {
            root = new Node();
            parent = root;
            parent.connectChild(0, node);
        } else {
            parent = node.parent;
        }

        int itemIdx = parent.insert(itemB);

        for (int i = parent.elementAmount - 1; i > itemIdx; --i) {
            Node temp = parent.disconnectChild(i);
            parent.connectChild(i + 1, temp);
        }

        Node brother = new Node();
        parent.connectChild(itemIdx + 1, brother);

        brother.insert(itemC);
        brother.connectChild(0, child2);
        brother.connectChild(1, child3);
    }

    private Node getNextChild(Node node, Comparable key) {
        int i;
        for (i = 0; i < node.elementAmount; i++) {
            if (key.compareTo(node.items[i].key) < 0) {
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

        for (int i = 0; i < node.elementAmount + 1; i++) {
            Node next = node.children[i];
            if (next != null) {
                printTree(next);
            } else {
                return;
            }
        }
    }
}
