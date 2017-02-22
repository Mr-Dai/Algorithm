package com.mrdai.algorithm.sort;

/**
 * {@link Sort} implementation using quick sort.
 */
public class QuickSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array.length < 2) return;
        quickSort(array, 0, array.length);
    }

    protected <T extends Comparable<T>> void quickSort(T[] array, int start, int end) {
        if (start >= end - 1) return;
        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex);
        quickSort(array, pivotIndex + 1, end);
    }

    protected <T extends Comparable<T>> int partition(T[] array, int start, int end) {
        T pivot = array[start];
        int i = start + 1, j = end - 1;
        while (i < j) {
            while (array[j].compareTo(pivot) >= 0 && i < j) j--;
            swap(array, i, j);
            while (array[i].compareTo(pivot) < 0 && i < j) i++;
            swap(array, i, j);
        }
        if (array[i].compareTo(pivot) >= 0)
            i--;
        for (j = start; j < i; j++)
            array[j] = array[j + 1];
        array[i] = pivot;
        return i;
    }
}
