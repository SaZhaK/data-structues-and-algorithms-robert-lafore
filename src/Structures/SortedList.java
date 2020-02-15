package Structures;

import java.util.EmptyStackException;

public class SortedList {
    private Node first;

    public SortedList() {
        first = null;
    }

    public void insert(int id) {
        Node newNode = new Node(id);

        if (isEmpty()) {
            first = newNode;
        } else {
            Node current = first;
            Node prev = first;
            while (current != null) {
                if (current.getId() < id) {
                    prev = current;
                    current = current.next;
                } else {
                    break;
                }
            }
            prev.next = newNode;
            newNode.next = current;
        }
    }

    public void find(int id) {
        Node current = first;
        while (current != null) {
            if (current.getId() == id) {
                System.out.println("Found element with id = " + current.getId());
                return;
            } else {
                current = current.next;
            }
        }
        System.out.println("Did not found element with id = " + id);
    }

    public void delete(int id) {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            Node current = first;
            Node prev = first;
            while (current != null) {
                if (current.getId() == id) {
                    prev.next = current.next;
                    return;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
            System.out.println("Did not found element with id = " + id);
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void display() {
        Node current = first;
        while (current != null) {
            System.out.print(current.getId() + " ");
            current = current.next;
        }
        System.out.println();
    }
}
