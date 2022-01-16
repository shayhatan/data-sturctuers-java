package com.company.BinarySearchTree;

public interface Tree<T> {

    public void insert(T data);

    public void remove(T data);

    public void traversal();

    public T getMin();

    public T getMax();

}
