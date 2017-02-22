package com.mrdai.algorithm.sort;

/**
 * {@link Sort} implementation using shell sort.
 */
public class ShellSort implements Sort {
    private static final int[] GAPS = new int[]{ 701, 301, 132, 57, 23, 10, 4, 1 };

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array.length < 2) return;
        int i, j, k;
        for (k = 0; k < GAPS.length; k++) {
            int gap = GAPS[k];
            for (i = gap; i < array.length; i++) {
                T temp = array[i];
                for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap)
                    array[j] = array[j - gap];
                array[j] = temp;
            }
        }
    }
}
