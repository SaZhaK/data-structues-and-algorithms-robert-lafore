package Structures.RedBlackTree;

public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(1, "one");
        redBlackTree.insert(2, "two");
        redBlackTree.insert(3, "three");
        redBlackTree.insert(4, "four");
        redBlackTree.insert(5, "five");
        redBlackTree.print();

        System.out.println("Find: " + redBlackTree.find(3));

        System.out.println(redBlackTree.getMin());
        System.out.println(redBlackTree.getMax());

        redBlackTree.clear();
        System.out.println("After clearing:");
        redBlackTree.print();
        System.out.println(redBlackTree.isEmpty());
    }
}
