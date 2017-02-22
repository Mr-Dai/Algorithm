package com.mrdai.algorithm.sort;

/**
 * {@link Sort} implementation using heap sort.
 */
public class HeapSort implements Sort {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array.length < 2) return;
        buildMaxHeap(array);
        int currentLength = array.length - 1;
        while (currentLength > 0) {
            swap(array, 0, currentLength); // Swap the biggest element to the end
            siftDown(array, 0, currentLength); // Repair the remaining heap
            currentLength--;
        }
    }

    /**
     * Puts elements of given array in heap order.
     */
    private <T extends Comparable<T>> void buildMaxHeap(T[] array) {
        int start = parent(array.length - 1); // The last parent node
        while (start >= 0) {
            // Sift down the node at index 'start' to the proper place such that all nodes below
            // the start index are in heap order
            siftDown(array, start, array.length);
            // Go to the next parent node
            start--;
        }
    }

    /**
     * Repairs the heap whose root element is at index 'start', assuming the heaps rooted at its children are valid
     */
    private <T extends Comparable<T>> void siftDown(T[] array, int start, int end) {
        int root = start;
        while (leftChild(root) < end) { // Break if the current `root` is a leaf node
            // Swap the root element with its maximal child
            int swap = root;
            if (leftChild(root) < end && array[swap].compareTo(array[leftChild(root)]) < 0)
                swap = leftChild(root);
            if (rightChild(root) < end && array[swap].compareTo(array[rightChild(root)]) < 0)
                swap = rightChild(root);

            if (swap == root) // The heap is valid. End the loop
                return;
            swap(array, root, swap);
            root = swap; // Continue to repair the swapped sub-heap
        }
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        if (index == 0) return -1;
        return (index - 1) / 2;
    }
}
