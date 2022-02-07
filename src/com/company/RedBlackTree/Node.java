package com.company.RedBlackTree;

public class Node<T> {
    private T data;
    private Node<T> right;
    private Node<T> left;
    private Node<T> parent;
    private NodeColor color;

    public Node(T _data, Node<T> _parent) {
        data = _data;
        parent = _parent;
        // default color is red
        color = NodeColor.RED;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "" + data + " ";
    }
}
