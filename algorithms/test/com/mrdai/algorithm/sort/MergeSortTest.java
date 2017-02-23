package com.mrdai.algorithm.sort;

/**
 * Test class for {@link MergeSort}.
 */
public class MergeSortTest extends SortTest {
    @Override
    Sort getTestedSort() {
        return new MergeSort();
    }
}