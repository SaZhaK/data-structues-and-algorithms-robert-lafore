package Structures.LinearStructures;

import java.util.EmptyStackException;

public class BilateralList {
    private Node first;
    private Node last;

    public BilateralList() {
        first = null;
        last = null;
    }

    public void insertFirst(int element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            newNode.next = first;
            first = newNode;
        }
    }

    public void insertLast(int element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public void deleteFirst() {
        if (isEmpty()){
            throw new EmptyStackException();
        } else {
            first = first.next;
        }
    }

    public void deleteLast() {
        if (isEmpty()){
            throw new EmptyStackException();
        } else {
            Node current = first;
            while (current.next != last) {
                current = current.next;
            }
            current.next = null;
            last = current;
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