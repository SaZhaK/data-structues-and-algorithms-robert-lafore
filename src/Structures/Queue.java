package Structures;

import java.util.LinkedList;

public class Queue {
    private LinkedList<Integer> elements;
    private int front;
    private int rear;

    public Queue() {
        elements = new LinkedList<>();
        front = 0;
        rear = -1;
    }

    public void insert(int element) {
        elements.add(element);
        rear++;
    }

    public int remove() {
        if (isEmpty()) {
            front++;
            return elements.get(front - 1);
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public int peek() {
        return elements.get(front);
    }

    public boolean isEmpty() {
        return rear < front;
    }
}
