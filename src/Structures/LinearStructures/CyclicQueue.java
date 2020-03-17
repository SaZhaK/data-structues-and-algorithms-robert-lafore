package Structures.LinearStructures;

public class CyclicQueue {
    private int[] elements;
    private int front;
    private int rear;
    private int curAmount;

    public CyclicQueue(int maxSize) {
        elements = new int[maxSize];
        curAmount = 0;
        front = 0;
        rear = -1;
    }

    public void insert(int element) {
        if (curAmount+1 <= elements.length) {
            if (rear == elements.length - 1) {
                rear = 0;
                elements[rear] = element;
            } else {
                elements[++rear] = element;
            }
            curAmount++;
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public int remove() {
        int returnElement;
        if (curAmount != 0) {
            if (front == elements.length - 1) {
                front = 0;
                returnElement = elements[elements.length - 1];
            } else {
                front++;
                returnElement = elements[front-1];
            }
            curAmount--;
            return returnElement;
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public int peek() {
        return elements[front];
    }

    public boolean isEmpty() {
        return curAmount == 0;
    }

    public void display() {
        if (rear > front) {
            for (int i = front; i <= rear; i++) {
                System.out.println(elements[i] + " ");
            }
        } else {
            for (int i = front; i < elements.length; i++) {
                System.out.println(elements[i] + " ");
            }
            for (int i = 0; i <= rear; i++) {
                System.out.println(elements[i] + " ");
            }
        }
    }
}
