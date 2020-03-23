package Structures.Heap;

public class HeapSort {
    public static void sort(int[] array) {
        Heap heap = new Heap();

        for (int i = 0; i < array.length; i++) {
            heap.insertAt(i, array[i], null);
            heap.incrementSize();
        }

        for (int i = array.length / 2 - 1; i >= 0; --i) {
            heap.trickleDown(i);
        }

        for (int i = array.length -1; i >= 0; --i) {
            array[i] = heap.remove().getKey();
        }
    }
}
