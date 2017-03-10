package com.mrdai.struct.tree;

public class BinaryTreeNode<T> {

    public T value;
    public BinaryTreeNode<T> parent;
    public BinaryTreeNode<T> leftChild;
    public BinaryTreeNode<T> rightChild;

    public BinaryTreeNode() {}

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public BinaryTreeNode(T value, BinaryTreeNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }
}
