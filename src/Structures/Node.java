package Structures;

public class Node {
    private int id;
    public Node next;
    public Node prev; //Only used in DoublyList

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}