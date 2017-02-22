package com.mrdai.algorithm.sort;

/**
 * Test class for {@link HeapSort}.
 */
public class HeapSortTest extends SortTest {
    @Override
    Sort getTestedSort() {
        return new HeapSort();
    }
}