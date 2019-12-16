public class PriorityQueue {
    private int[] elements;
    private int curAmount;

    public PriorityQueue(int maxSize) {
        elements = new int[maxSize];
        curAmount = 0;
    }

    public void insert(int element) {
        if (curAmount == elements.length) throw new ArrayIndexOutOfBoundsException();
        else {
            int insertIdx = curAmount;
            for (int i = 0; i < curAmount; i++) {
                if (elements[i] < element) {
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

    public int remove() {
        if (!isEmpty()) {
            curAmount--;
            return elements[curAmount];
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public int peek() {
        return elements[curAmount - 1];
    }

    public boolean isEmpty() {
        return curAmount == 0;
    }
}