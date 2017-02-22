package com.mrdai.algorithm.sort;

/**
 * Interface for sorting algorithms, which declares method {@link #sort(Comparable[])}.
 *
 * @author Robert Peng
 */
public interface Sort {

    /**
     * Sorts the given array in ascending order.
     *
     * @param array the given array.
     * @param <T> type of the array elements.
     */
    <T extends Comparable<T>> void sort(T[] array);

    /**
     * Swaps the elements on index {@code i} and {@code j} of the given array.
     *
     * @param array the given array.
     * @param i index {@code i}.
     * @param j index {@code j}.
     * @param <T> type of the elements in the array.
     */
    default <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
