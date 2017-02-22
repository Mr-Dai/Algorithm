package com.mrdai.algorithm.sort;

/**
 * Test class for {@link QuickSort}.
 */
public class QuickSortTest extends SortTest {
    @Override
    Sort getTestedSort() {
        return new QuickSort();
    }
}