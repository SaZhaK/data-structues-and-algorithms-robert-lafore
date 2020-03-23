package Structures.Heap;

import java.util.Random;

public class HeapTest {
    public static void main(String[] args) {
        Heap heap = new Heap();

        System.out.println("Insert:");
        heap.insert(1, "1");
        heap.insert(2, "2");
        heap.insert(3, "3");
        heap.insert(4, "4");
        heap.insert(5, "5");

        heap.print();

        System.out.println("Remove:");
        heap.remove();
        heap.print();

        System.out.println("Change:");
        heap.change(2, 6, "6");
        heap.print();

        System.out.println("\nRandom array:");
        int[] array = createArray(10);

        display(array);

        System.out.println("\nSorted:");
        HeapSort.sort(array);
        display(array);
    }

    private static int[] createArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt() % 20;
        }
        return array;
    }

    public static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
