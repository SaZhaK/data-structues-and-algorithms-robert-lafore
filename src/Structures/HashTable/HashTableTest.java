package Structures.HashTable;

public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.insert(1, "1");
        hashTable.insert(2, "2");
        hashTable.insert(3, "3");
        hashTable.insert(4, "4");
        hashTable.insert(13, "13");

        System.out.println("Display:");
        hashTable.display();

        hashTable.insert(15, "15");
        hashTable.insert(19, "19");

        System.out.println("Display:");
        hashTable.display();

        System.out.println("Find:");
        System.out.println(hashTable.find(4));

        System.out.println("Delete");
        System.out.println(hashTable.delete(3));
        hashTable.display();

        hashTable.insert(3, "3");
        System.out.println("Display:");
        hashTable.display();
    }
}
