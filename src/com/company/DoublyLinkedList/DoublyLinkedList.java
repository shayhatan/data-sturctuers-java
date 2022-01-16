package com.company.DoublyLinkedList;

public class DoublyLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);

        // this is the first item in the linked list
        if (tail == null) {
            // both of the pointers are pointing to the new node
            tail = newNode;
            head = newNode;
        } else {

            // inserting the new item to the end of the list - O(1)
            newNode.setPreviousNode(tail);
            tail.setNextNode(newNode);
            tail = newNode;
        }
    }

    public void traverse() {
        Node<T> actual = head;
        while (actual != null) {
            System.out.print(" " + actual);
            actual = actual.getNextNode();
        }
    }

    public void traverseBackward() {
        Node<T> actual = tail;
        while (actual != null) {
            System.out.print(" " + actual);
            actual = actual.getPreviousNode();
        }
    }

}
