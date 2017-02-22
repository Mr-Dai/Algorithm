package com.mrdai.algorithm.sort;

/**
 * Test class for {@link SelectionSort}.
 */
public class SelectionSortTest extends SortTest {
    @Override
    Sort getTestedSort() {
        return new SelectionSort();
    }
}