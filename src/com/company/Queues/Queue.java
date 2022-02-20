package com.company.Queues;

public class Queue<T extends Comparable<T>> {

    private Node<T> head;
    private Node<T> tail;
    private int count;

    public boolean isEmpty(){
        return this.head == null;
    }

    public int size(){
        return this.count;
    }

    // O(1)
    public void enqueue(T new_data){

        Node<T> old_last_node = this.tail;
        this.tail = new Node<>(new_data);
        this.tail.setNextNode(null);

        if( isEmpty() ){
            this.head = this.tail;
        }else{
            old_last_node.setNextNode(this.tail);
        }

        count++;

    }

    // O(1)
    public T dequeue() {

        // could throw exception  if(count == null)
        this.count--;

        T to_dequeue = this.head.getData();
        this.head =this.head.getNextNode();

        if( isEmpty() ){
            this.tail = null;
        }

        return to_dequeue;
    }
}


