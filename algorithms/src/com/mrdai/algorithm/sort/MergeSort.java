package com.mrdai.algorithm.sort;

/**
 * {@link Sort} implementation using merge sort.
 */
public class MergeSort implements Sort {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array.length < 2) return;
        T[] temp = (T[]) new Comparable[array.length];
        mergeSort(array, 0, array.length, temp);
    }

    private <T extends Comparable<T>> void mergeSort(T[] array, int start, int end, T[] temp) {
        if (end - start < 2) return;

        int mid = (start + end) / 2;
        mergeSort(array, start, mid, temp);
        mergeSort(array, mid, end, temp);

        // Merge to affiliate space
        int i = start, j = mid, current = i;
        while (i < mid || j < end) {
            if (i >= mid) temp[current++] = array[j++];
            else if (j >= end) temp[current++] = array[i++];
            else if (array[i].compareTo(array[j]) < 0) temp[current++] = array[i++];
            else temp[current++] = array[j++];
        }
        // Copy back
        System.arraycopy(temp, start, array, start, end - start);
    }
}
