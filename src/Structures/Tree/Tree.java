package Structures.Tree;

public class Tree {
    private Node root;

    public Node find(int key) {
        Node current = root;

        while (current.getKey() != key) {
            if (key < current.getKey()) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }

            if (current == null) {
                return null;
            }
        }
        return current;
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

    public Node getMin() {
        Node current = root;

        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }

        return current;
    }

    public Node getMax() {
        Node current = root;

        while (current.getRightChild() != null) {
            current = current.getRightChild();
        }

        return current;
    }

    public void display(int order) {
        switch (order) {
            case 0: {
                traverse(root);
                break;
            }
            case 1: {
                traversePreOrder(root);
                break;
            }
            case 2: {
                traverseInOrder(root);
                break;
            }
            default:
                System.out.println("No option available, try again");
        }
    }

    private void traverse(Node current) {
        if (current == null) {
            return;
        } else {
            traverse(current.getLeftChild());
            System.out.println(current);
            traverse(current.getRightChild());
        }
    }

    private void traversePreOrder(Node current) {
        if (current == null) {
            return;
        } else {
            System.out.println(current);
            traverse(current.getLeftChild());
            traverse(current.getRightChild());
        }
    }

    private void traverseInOrder(Node current) {
        if (current == null) {
            return;
        } else {
            traverse(current.getLeftChild());
            traverse(current.getRightChild());
            System.out.println(current);
        }
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        //Searching node to delete
        while (current.getKey() != key) {
            parent = current;
            if (key < current.getKey()) {
                current = current.getLeftChild();
                isLeftChild = true;
            } else {
                current = current.getRightChild();
                isLeftChild = false;
            }

            if (current == null) {
                return false;
            }
        }

        //Current contains node to delete
        if (current.getLeftChild() == null && current.getRightChild() == null) {
            if (current == root) {
                root = null;
            }

            if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        } else if (current.getLeftChild() != null) {
            if (current == root) {
                root = current.getLeftChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
        } else if (current.getRightChild() != null) {
            if (current == root) {
                root = current.getRightChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            } else {
                parent.setRightChild(current.getRightChild());
            }
        } else {
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeftChild(successor);
            } else {
                parent.setRightChild(successor);
            }
        }
        return true;
    }

    private Node getSuccessor(Node targetNode) {
        Node successorParent = targetNode;
        Node successor = targetNode;
        Node current = targetNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != targetNode.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(targetNode.getRightChild());
        }

        return successor;
    }
}
