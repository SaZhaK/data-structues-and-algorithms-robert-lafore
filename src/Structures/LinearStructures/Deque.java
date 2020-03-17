package Structures.LinearStructures;

public class Deque {
    private int[] elements;
    private int curAmount;
    private int left;
    private int right;

    public Deque(int maxSize) {
        elements = new int[maxSize];
        curAmount = 0;
        left = 0;
        right = 0;
    }

    public void insertLeft(int element) {
        if (curAmount < elements.length) {
            if (left > 0) {
                curAmount++;
                elements[left--] = element;
            } else {
                left = elements.length - 1;
                curAmount++;
                elements[left--] = element;
            }
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public void insertRight(int element) {
        if (curAmount < elements.length) {
            if (right < elements.length) {
                curAmount++;
                elements[right++] = element;
            } else {
                right = 0;
                curAmount++;
                elements[right++] = element;
            }
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public void removeLeft() {
        if (curAmount > 0) {
            if (left < elements.length) {
                curAmount--;
                left++;
            } else {
                left = 1;
                curAmount--;
            }
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public void removeRight() {
        if (curAmount > 0) {
            if (right >= 0) {
                curAmount--;
                right--;
            } else {
                right = elements.length - 2;
                curAmount--;
            }
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return curAmount == 0;
    }

    public boolean isFull() {
        return curAmount == elements.length;
    }
}