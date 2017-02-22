package com.mrdai.algorithm.sort;

/**
 * Test class for {@link HeapSort}.
 */
public class ShellSortTest extends SortTest {
    @Override
    Sort getTestedSort() {
        return new ShellSort();
    }
}