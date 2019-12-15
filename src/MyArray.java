import java.util.NoSuchElementException;

public class MyArray {
    private int[] elements;
    private int curAmount;

    public MyArray(int maxSize) {
        elements = new int[maxSize];
    }

    public void display() {
        for (int i = 0; i < curAmount; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public int search(int element){
        int idx;
        for (idx = 0; idx < curAmount; idx++) {
            if (elements[idx] == element) break;
        }
        if (idx == curAmount) return -1;
        else return idx;
    }

    public void insert(int element) {
        if (curAmount == elements.length) throw new ArrayIndexOutOfBoundsException();
        else elements[curAmount++] = element;
    }

    public void delete(int element) {
        if (curAmount == 0) throw new ArrayIndexOutOfBoundsException();
        else {
            int idx = search(element);
            if (idx == -1) throw new NoSuchElementException();
            else {
                --curAmount;
                for (int i = idx; i < curAmount; i++) {
                    elements[i] = elements[i + 1];
                }
            }
        }
    }
}