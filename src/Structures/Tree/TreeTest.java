package Structures.Tree;

public class TreeTest {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(1, "One");
        tree.insert(2, "Two");
        tree.insert(3, "Three");
        tree.insert(4, "Four");
        tree.insert(5, "Five");

        System.out.println("Find: " + tree.find(3));
        System.out.println("Min: " + tree.getMin());
        System.out.println("Max: " + tree.getMax());

        System.out.println("\nDisplay:");
        System.out.println("Traverse:");
        tree.display(0);
        System.out.println("\nTraverse preOrder:");
        tree.display(1);
        System.out.println("\nTraverse inOrder:");
        tree.display(2);
        tree.delete(3);
        System.out.println("\nAfter delete:");
        tree.display(0);
    }
}
