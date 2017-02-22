package com.mrdai.algorithm.sort;

/**
 * Test class for {@link BubbleSort}.
 */
public class BubbleSortTest extends SortTest {
    @Override
    Sort getTestedSort() {
        return new BubbleSort();
    }
}