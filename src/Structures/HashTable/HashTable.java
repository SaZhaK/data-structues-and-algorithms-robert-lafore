package Structures.HashTable;

public class HashTable {

    private class DuplicateException extends RuntimeException {}

    private final static int INITIAL_SIZE = 5;

    private Item[] items;
    private int arraySize = INITIAL_SIZE;
    private int currentAmount = 0;

    public HashTable() {
        this.items = new Item[arraySize];
    }

    private int hashFunc(int key) {
        return key % arraySize;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int getNextPrime(int size) {
        while (true) {
            if (isPrime(size)) {
                return size;
            } else {
                ++size;
            }
        }
    }

    public Item find(int key) {
        int hashKey = hashFunc(key);

        while (items[hashKey] != null) {
            if (items[hashKey].getKey() == key) {
                return items[hashKey];
            }
            ++hashKey;
            hashKey %= arraySize;
        }

        return null;
    }

    private void copy() {
        int newArraySize = getNextPrime(arraySize * 2);

        Item[] newItems = new Item[newArraySize];
        for (int i = 0; i < arraySize; i++) {
            if (items[i] != null) {
                int hashKey = items[i].getKey() % newArraySize;

                while (newItems[hashKey] != null) {
                    ++hashKey;
                    hashKey %= newArraySize;
                }
                newItems[hashKey] = items[i];
            }
        }

        items = newItems;
        arraySize = newArraySize;
    }

    public void insert(int key, Object content) {
        ++currentAmount;

        if (currentAmount == arraySize) {
            copy();
        }

        Item newItem = new Item(key, content);
        int hashKey = hashFunc(key);

        while (items[hashKey] != null) {
            if (items[hashKey].getKey() == key) {
                throw new DuplicateException();
            }

            ++hashKey;
            hashKey %= arraySize;
        }

        items[hashKey] = newItem;
    }

    public Item delete(int key) {
        --currentAmount;
        int hashKey = hashFunc(key);

        while (items[hashKey] != null) {
            if (items[hashKey].getKey() == key) {
                Item temp = items[hashKey];
                items[hashKey] = null;
                return temp;
            }
            ++hashKey;
            hashKey %= arraySize;
        }

        return null;
    }

    public void display() {
        for (int i = 0; i < arraySize; i++) {
            System.out.println("Item: hash key = " + i + " " + items[i]);
        }
    }
}

class Item {
    private int key;
    private Object content;

    public Item(int key, Object content) {
        this.key = key;
        this.content = content;
    }

    @Override
    public String toString() {
        return "key = " + key + ", content = " + content;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
