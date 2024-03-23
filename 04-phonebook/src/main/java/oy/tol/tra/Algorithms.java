package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;


public class Algorithms {

    public static <T extends Comparable<T>> void fastSort(T[] array){
        if(array == null || array.length == 0){
            return;
        }
        fastSort(array, 0, array.length - 1);
    }
    private static <T extends Comparable<T>> void fastSort(T[] array, int a, int b){
        if(a < b){
            int pivotIndex = partition(array, a, b);
            fastSort(array, a, pivotIndex - 1);
            fastSort(array, pivotIndex + 1, b);
        }
    }
    private static <T extends Comparable<T>> int partition(T[] array, int a, int b){
        T pivot = array[b];
        int i = a - 1;
        for(int j = a; j < b; j++){
            if(array[j].compareTo(pivot) < 0){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, b);
        return i + 1;
    }
    // Swap the positions of two elements in the array
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    // Partition the array according to the given rule and return the partition index
    public static <T> int partitionByRule(T[] array, int left, Predicate<T> rule) {
        int index = left;
        while (index < array.length && !rule.test(array[index])) {
            index++;
        }
        if (index >= array.length) {
            return array.length;
        }
        int nextIndex = index + 1;
        while (nextIndex < array.length) {
            if (!rule.test(array[nextIndex])) {
                swap(array, index, nextIndex);
                index++;
            }
            nextIndex++;
        }
        return index;
    }
    // Sort the array using a comparator
    public static <T> void sortWithComparator(T[] array, Comparator<T> comparator) {
        fastSortWithComparator(array, comparator, 0, array.length - 1);
    }
    // Swap the positions of two elements in the array
    private static <T> void fastSortWithComparator(T[] array,  Comparator<T> comparator, int start, int end) {

        if(start < end){
            int pivotIndex = partitionWithComparator(array, comparator, start, end);
            fastSortWithComparator(array, comparator, start, pivotIndex - 1);
            fastSortWithComparator(array, comparator, pivotIndex + 1, end);
        }
    }

    private static <T> int partitionWithComparator(T[] array, Comparator<T> comparator, int start, int end) {
        T pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(comparator.compare(array[j], pivot) <= 0){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }


}
