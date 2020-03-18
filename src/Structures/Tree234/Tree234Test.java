package Structures.Tree234;

public class Tree234Test {
    public static void main(String[] args) {
        Tree234 tree = new Tree234();
        tree.insert(10, "10");
        tree.insert(15, "15");
        tree.insert(20, "20");
        tree.insert(7, "7");
        tree.insert(8, "8");
        tree.insert(12, "12");

        tree.print();

        System.out.println("Find " + tree.find(20));
    }
}