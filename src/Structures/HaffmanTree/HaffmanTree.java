package Structures.HaffmanTree;

import Structures.Tree.Node;
import Structures.Tree.Tree;

import java.util.List;

public class HaffmanTree extends Tree implements Comparable {
    private Node root;

    public Node getRoot() {
        return root;
    }

    @Override
    public int compareTo(Object object) {
        if (!(object instanceof HaffmanTree)) {
            return -1;
        } else {
            return this.root.getKey() - ((HaffmanTree) object).root.getKey();
        }
    }

    public void generateCodes(Node current, String code, List<Symbol> symbols) {
        if (current == null) {
            return;
        } else {
            generateCodes(current.getLeftChild(), code + "0", symbols);

            for (int i = 0; i < symbols.size(); i++) {
                if (current.getValue().toString().equals(String.valueOf(symbols.get(i).getChar()))) {
                    symbols.get(i).setCode(code);
                    break;
                }
            }

            generateCodes(current.getRightChild(), code + "1", symbols);
        }
    }

    public void insert(int key, Object value) {
        Node newNode = new Node(key, value);

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;

            while (true) {
                Node parent = current;

                if (key < current.getKey()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(newNode);
                        return;
                    }
                }
            }
        }

    }

    public void insert(Node newNode) {
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;

            while (true) {
                Node parent = current;

                if (newNode.getKey() < current.getKey()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(newNode);
                        return;
                    }
                }
            }
        }

    }
}
