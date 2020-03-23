package Structures.Heap;

public class HeapTreeTest {
    public static void main(String[] args) {
        HeapTree heapTree = new HeapTree();
        System.out.println("Insert:");
        heapTree.insert(1, "1");
        heapTree.insert(2, "2");
        heapTree.insert(3, "3");
        heapTree.insert(4, "4");
        heapTree.insert(5, "5");
        heapTree.print();

        System.out.println("Remove:");
        heapTree.remove();
        heapTree.print();
    }
}
