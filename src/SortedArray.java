import java.util.NoSuchElementException;

public class SortedArray {
    private int[] elements;
    private int curAmount;

    SortedArray(int maxSize) {
        elements = new int[maxSize];
    }

    public int getCurAmount() {
        return curAmount;
    }

    public int getElementAt(int idx) {
        return elements[idx];
    }

    public void display() {
        for (int i = 0; i < curAmount; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public void insert(int element) {
        elements[curAmount++] = element;
    }

    public int search(int element) {
        int curIdx;
        int leftBorder = 0;
        int rightBorder = curAmount - 1;

        while (true) {
            curIdx = (leftBorder + rightBorder) / 2;
            if (elements[curIdx] == element) return curIdx;
            else if (leftBorder > rightBorder) return -1;
            else {
                if (elements[curIdx] > element) rightBorder = curIdx - 1;
                else leftBorder = curIdx + 1;
            }
        }
    }

    public void delete(int element) {
        if (curAmount == 0) throw new ArrayIndexOutOfBoundsException();
        else {
            int idx = search(element);
            if (idx == -1) throw new NoSuchElementException();
            else {
                --curAmount;
                for (int i = idx; i < curAmount; i++) {
                    elements[i] = elements[i + 1];
                }
            }
        }
    }

    public void bubbleSort() {
        for (int i = curAmount - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    public void bilateralBubbleSort() {
        int leftIdx = 0;
        int j = leftIdx;
        int rightIdx = curAmount - 1;
        while (rightIdx > leftIdx) {
            for (j = leftIdx; j < rightIdx; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
            rightIdx--;
            for (j = rightIdx; j > leftIdx; j--) {
                if (elements[j] < elements[j - 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j - 1];
                    elements[j - 1] = temp;
                }
            }
            leftIdx++;
        }
    }

    public void selectionSort() {
        for (int i = 0; i < curAmount - 1; i++) {
            int min = i;
            for (int j = i + 1; j < curAmount; j++) {
                if (elements[j] < elements[min]) {
                    min = elements[j];
                }
            }
            int temp = elements[i];
            elements[i] = elements[min];
            elements[min] = temp;
        }
    }

    public void insertionSort() {
        for (int i = 1; i < curAmount; i++) {
            int idx = i;
            int curElement = elements[i];
            while (elements[idx - 1] >= curElement && idx > 0) {
                elements[idx] = elements[idx - 1];
                --idx;
            }
            elements[idx] = curElement;
        }
    }

    public void noDuplicatesInsertionSort() {
        insertionSort();
        int duplicatesAmount = 0;
        for (int i = 0; i < curAmount - 1; i++) {
            if (elements[i] == elements[i + 1]) {
                elements[i] = Integer.MAX_VALUE;
                ++duplicatesAmount;
            }
        }
        insertionSort();
        curAmount -= duplicatesAmount;
    }

    public void oddEvenSort() {
        for (int i = 0; i < curAmount; i++) {
            for (int j = 0; j < curAmount - 1; j++) {
                if (j % 2 == 1) {
                    if (elements[j + 1] < elements[j]) {
                        int temp = elements[j];
                        elements[j] = elements[j + 1];
                        elements[j + 1] = temp;
                    }
                }
            }

            for (int j = 0; j < curAmount - 1; j++) {
                if (j % 2 == 0) {
                    if (elements[j + 1] < elements[j]) {
                        int temp = elements[j];
                        elements[j] = elements[j + 1];
                        elements[j + 1] = temp;
                    }
                }
            }
        }
    }
}