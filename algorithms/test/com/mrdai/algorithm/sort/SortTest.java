package com.mrdai.algorithm.sort;

import org.junit.Test;

import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test cases on {@link Sort} implementations.
 */
public abstract class SortTest {
    private final Sort sort = getTestedSort();

    @Test
    public void testOnEmptyArray() {
        Integer[] array = new Integer[0];
        sort.sort(array);
        assertThat(array.length, is(0));
    }

    @Test
    public void testOnSingleElementArray() {
        int testElement = round((float)(random() * 10));
        Integer[] array = new Integer[]{ testElement };
        sort.sort(array);
        assertThat(array.length, is(1));
        assertThat(array[0], is(testElement));
    }

    @Test
    public void testOnAscendingArray() {
        Integer[] array = generateAscendingArray(100);
        sort.sort(array);
        assertThat(array.length, is(100));
        assertSorted(array);
    }

    @Test
    public void testOnDescendingArray() {
        Integer[] array = generateDescendingArray(100);
        sort.sort(array);
        assertThat(array.length, is(100));
        assertSorted(array);
    }

    @Test
    public void testOnRandomizedArray() {
        Integer[] array = generateRandomized(10);
        sort.sort(array);
        assertThat(array.length, is(10));
        assertSorted(array);
    }

    private void assertSorted(Integer[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i].compareTo(array[i - 1]) < 0)
                throw new AssertionError("The array is not sorted on index " + (i - 1)
                    + ":\narray[" + (i - 1) + "] = " + array[i - 1] + ", array[" + i + "] = " + array[i]);
    }

    private Integer[] generateRandomized(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++)
            result[i] = round((float)(random() * 1000));
        return result;
    }

    private Integer[] generateAscendingArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++)
            result[i] = i;
        return result;
    }

    private Integer[] generateDescendingArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++)
            result[i] = size - i;
        return result;

    }

    /**
     * Returns the {@link Sort} implementation to be tested.
     *
     * @return the {@code Sort} implementation to be tested.
     */
    abstract Sort getTestedSort();
}
