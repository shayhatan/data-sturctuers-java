package com.company.Stacks;

public class LinkedStack<T> {
    private Node<T> head;
    private int count;

    public void push(T data) {
        ++count;
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> oldNode = head;
            head = new Node<>(data);
            head.setNextNode(oldNode);
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        T item = head.getData();
        head = head.getNextNode();
        --count;
        return item;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

}
