package Structures.LinearStructures;

public class Stack {
    private int[] elements;
    private int top;

    public Stack(int maxSize) {
        elements  = new int[maxSize];
        top = -1;
    }

    public void push(int element) {
        elements[++top] = element;
    }

    public int pop() {
        return elements[top--];
    }

    public int peek() {
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}