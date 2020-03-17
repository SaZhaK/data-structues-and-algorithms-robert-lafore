package Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        List<Integer> list = generateRandomList(10);

        System.out.println("List: ");
        display(list);

        List<Integer> result = mergeSort(list);
        System.out.println("\nSorted list:");
        display(result);
    }

    public static <T extends Comparable> List<T> mergeSort(List<T> list) {

        if (list.size() == 1) return list;
        else {
            List<T> list1 = new ArrayList<>();
            List<T> list2 = new ArrayList<>();
            for (int i = 0; i < list.size() / 2; i++) {
                list1.add(list.get(i));
            }
            for (int i = list.size() / 2; i < list.size(); i++) {
                list2.add(list.get(i));
            }

            return merge(mergeSort(list1), mergeSort(list2));
        }
    }

    private static <T extends Comparable> List<T> merge(List<T> list1, List<T> list2) {
        List<T> resultList = new ArrayList<>();

        int idx1 = 0, idx2 = 0;
        while (idx1 < list1.size() && idx2 < list2.size()) {
            if (list1.get(idx1).compareTo(list2.get(idx2)) < 0) resultList.add(list1.get(idx1++));
            else resultList.add(list2.get(idx2++));
        }

        while (idx1 < list1.size()) resultList.add(list1.get(idx1++));
        while (idx2 < list2.size()) resultList.add(list2.get(idx2++));

        return resultList;
    }

    public static void display(List list) {
        for (Object element : list) {
            System.out.print(element + " ");
        }
    }

    public static List<Integer> generateRandomList(int size) {
        Random random = new Random();
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            resultList.add(random.nextInt() % 20);
        }

        return resultList;
    }
}
