package Structures.AVLTree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(1, "one");
        avlTree.insert(2, "two");
        avlTree.insert(3, "three");
        avlTree.insert(4, "four");
        avlTree.insert(5, "five");

        avlTree.print();

        System.out.println("Min = " + avlTree.getMin());
        System.out.println("Max = " + avlTree.getMax());

        System.out.println("Find = " + avlTree.find(4));

        avlTree.clear();
        System.out.println("After clearing: ");
        avlTree.print();
    }
}
