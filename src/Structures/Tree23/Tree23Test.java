package Structures.Tree23;

public class Tree23Test {
    public static void main(String[] args) {
        Tree23 tree = new Tree23();

        tree.insert(1, "1");
        tree.insert(2, "2");
        tree.insert(3, "3");
        tree.insert(4, "4");
        tree.insert(5, "5");
        tree.insert(6, "6");
        tree.insert(7, "7");

        System.out.println("\nprint");
        tree.print();
    }
}
