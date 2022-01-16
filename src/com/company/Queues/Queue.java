package com.company.Queues;

public class Queue<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    int count;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return count;
    }

    // O(1)
    public void enqueue(T newData) {

        Node<T> newNode = new Node<>(newData);
        if (isEmpty()) {
            head = newNode;
        } else {
            if(tail == null) {
                tail = newNode;
                head.setNextNode(tail);
            }
            else {
                Node<T> temp = tail;
                tail = newNode;
                newNode = temp;
                newNode.setNextNode(tail);
            }
        }
        ++count;
    }

    public T dequeue() {
        if(count == 0) {
            return null;
        }

        T to_remove = head.getData();
        head = head.getNextNode();
        --count;
        return to_remove;
    }
}

