package com.company.DoublyLinkedList;

public class Node<T extends Comparable<T>> {
    private T data;

    // memory heavy
    private Node<T> previousNode;
    private Node<T> nextNode;


    // by default prev and next are null
    public Node(T data) {
        this.data = data;
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
