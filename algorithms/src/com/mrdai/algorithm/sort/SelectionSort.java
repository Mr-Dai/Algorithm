package com.mrdai.algorithm.sort;

/**
 * {@link Sort} implementation using selection sort.
 */
public class SelectionSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            T min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min.compareTo(array[j]) > 0) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i)
                swap(array, i, minIndex);
        }
    }

}
