package com.mrdai.algorithm.string;

import com.mrdai.struct.tree.BinaryTreeNode;
import com.mrdai.util.Bits;
import com.mrdai.util.Counter;
import com.mrdai.util.Pair;

import java.util.*;

/**
 * Huffman coding algorithm.
 */
public class HuffmanCoding {
    private final char[] text;
    private final Map<Character, String> bitTable = new HashMap<>();

    public HuffmanCoding(String text) {
        this(text.toCharArray());
    }

    public HuffmanCoding(char[] text) {
        this.text = text;
        computeBitTable();
    }

    public String toBits() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length; i++)
            builder.append(bitTable.get(text[i]));
        return builder.toString();
    }

    public Map<Character, String> getBitTable() {
        return bitTable;
    }

    @SuppressWarnings("unchecked")
    private void computeBitTable() {
        // Count each character
        Map<Character, Counter> counters = new HashMap<>();
        for (int i = 0; i < text.length; i++)
            counters.computeIfAbsent(text[i], (c) -> new Counter()).increment();
        PriorityQueue<BinaryTreeNode<Pair<Character, Integer>>> queues
                = new PriorityQueue<>(Comparator.comparing((n) -> n.value.right));
        // Put all the leaf nodes into the priority queue
        counters.entrySet().forEach((e) -> queues.add(new BinaryTreeNode<>(new Pair<>(e.getKey(), e.getValue().value))));
        // Construct the Huffman tree
        BinaryTreeNode<Pair<Character, Integer>> root, first, second;
        while (queues.size() > 1) {
            first = queues.poll();
            second = queues.poll();
            root = new BinaryTreeNode<>(new Pair<>(null, first.value.right + second.value.right));
            root.leftChild = first;
            root.rightChild = second;
            first.parent = root;
            second.parent = root;
            queues.add(root);
        }
        computeBitTableInternal(queues.poll(), "");
    }

    private void computeBitTableInternal(BinaryTreeNode<Pair<Character, Integer>> node, String str) {
        if (node == null) return;
        if (node.value.left != null)
            bitTable.put(node.value.left, str);
        else { // Internal node
            computeBitTableInternal(node.leftChild, str + "0");
            computeBitTableInternal(node.rightChild, str + "1");
        }
    }
}
