package com.company.LinkedList;

public class LinkedList<T extends Comparable<T>> implements List<T> {

    // change it here to private, works the same.
    public Node<T> root;
    private int num_of_items;

    // O(1)
    @Override
    public void insert(T data) {

        ++num_of_items;
        if (root == null) {
            // this is the first item in the linked list
            root = new Node<>(data);
        } else {

            // if we use insertEnd here we get the list in order of insertion - O(n)
            insertBeginning(data);
        }
    }

    // we just have to update the references O(1)
    private void insertBeginning(T data) {

        Node<T> new_node = new Node<>(data);
        new_node.setNextNode(root);
        root = new_node;

    }

    // must start with root node in order to find last node O(N)
    private void insertEnd(T data, Node<T> node) {

        if (node.getNextNode() != null) {
            insertEnd(data, node);
        } else {

            // we found the last node
            Node<T> new_node = new Node<>(data);
            node.setNextNode(new_node);
        }

    }

    @Override
    public void remove(T data) {
        if (root == null) {
            return;
        }

        // like str-cmp zero means equality
        if (root.getData().compareTo(data) == 0) {
            root = root.getNextNode();
        } else {
            remove(data, root, root.getNextNode());
        }


    }

    private void remove(T data, Node<T> prev, Node<T> actual) {
        // we have to find the node we want to remove
        while (actual != null) {
            // this is the node we want to remove
            if (actual.getData().compareTo(data) == 0) {
                // update references
                --num_of_items;
                prev.setNextNode(actual.getNextNode());
                return;
            }
            prev = actual;
            actual = actual.getNextNode();
        }
    }

    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        Node<T> actual_node = root;

        while (actual_node != null) {
            if (actual_node.getNextNode() == null) {
                System.out.println(actual_node);
            } else {
                System.out.print(actual_node + "-> ");
            }
            actual_node = actual_node.getNextNode();
        }
    }

    @Override
    public int size() {
        return num_of_items;
    }
}
