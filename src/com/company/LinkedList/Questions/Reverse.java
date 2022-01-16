package com.company.LinkedList.Questions;

/*
    Reverse a linked list in-place overview
    Construct an in-place algorithm
    (without the need for extra memory) to reverse a linked list!

    For example: 1 -> 2 -> 3 -> 4 should be transformed into 4 -> 3 -> 2 -> 1

    Good luck!
 */

import com.company.LinkedList.LinkedList;
import com.company.LinkedList.Node;

public class Reverse<T extends Comparable<T>> {
    LinkedList<T> list;

    public void setList(LinkedList<T> list) {
        this.list = list;
    }

    public void reverse() {
        Node<T> actual = list.root;
        Node<T> prev = null;
        Node<T> next = null;
        while (actual != null) {
            next = actual.getNextNode();
            actual.setNextNode(prev);
            prev = actual;
            actual = next;
        }
        list.root = prev;

    }


}
