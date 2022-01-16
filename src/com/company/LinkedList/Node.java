package com.company.LinkedList;

// extends Comparable means the use must give us a type who has comparable operations
public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next_node;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNextNode() {
        return next_node;
    }

    public void setNextNode(Node<T> next_node) {
        this.next_node = next_node;
    }

    @Override
    public String toString() {
        return data + " ";
    }
}
