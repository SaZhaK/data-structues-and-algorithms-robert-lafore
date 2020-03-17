package Structures.LinearStructures;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class OrderedArray {
    private int[] elements;
    private int curAmount;

    public OrderedArray(int maxSize) {
        elements = new int[maxSize];
    }

    public int getCurAmount() {
        return curAmount;
    }

    public int getElementAt(int idx) {
        return elements[idx];
    }

    public void display() {
        for (int i = 0; i < curAmount; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public int search(int element) {
        int curIdx;
        int leftBorder = 0;
        int rightBorder = curAmount - 1;

        while (true) {
            curIdx = (leftBorder + rightBorder) / 2;
            if (elements[curIdx] == element) return curIdx;
            else if (leftBorder > rightBorder) return -1;
            else {
                if (elements[curIdx] > element) rightBorder = curIdx - 1;
                else leftBorder = curIdx + 1;
            }
        }
    }

    public void insert(int element) {
        if (curAmount == elements.length) throw new ArrayIndexOutOfBoundsException();
        else {
            int insertIdx = curAmount;
            for (int i = 0; i < curAmount; i++) {
                if (elements[i] > element) {
                    insertIdx = i;
                    break;
                }
            }
            for (int i = curAmount; i > insertIdx; i--) {
                elements[i] = elements[i - 1];
            }
            elements[insertIdx] = element;
            curAmount++;
        }
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

    public int getMax() {
        if (curAmount == 0) throw new EmptyStackException();
        else return elements[curAmount];
    }

    public int removeMax() {
        int maxElement = getMax();
        curAmount--;
        return maxElement;
    }

    public void merge(OrderedArray receivedArray) {
        if (curAmount + receivedArray.getCurAmount() > elements.length) throw new ArrayIndexOutOfBoundsException();
        else {
            for (int i = 0; i < receivedArray.getCurAmount(); i++) {
                this.insert(receivedArray.getElementAt(i));
            }
        }
    }

    public void noDuplicates() {
        for (int i = 0; i < curAmount; i++) {
            for (int j = 0; j < i; j++) {
                if (elements[i] == elements[j]) delete(elements[i]);
            }
        }
    }
}