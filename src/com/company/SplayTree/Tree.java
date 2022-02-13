package com.company.SplayTree;

public interface Tree<T> {

    public void insert(T data);

    public T find(T data);

    public void traverse();

    // for debug
    public T getRoot();
}
