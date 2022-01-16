package com.company.BinarySearchTree.Questions;

import com.company.BinarySearchTree.Node;

/*
    Compare binary trees overview

    Write an efficient algorithm that's able to compare two binary search trees.
    The method returns true if the trees are identical
    (same topology with same values in the nodes)
    otherwise it returns false.

    Good Luck!
                                                                                                        *//*
public class TreeCompare<T extends Comparable<T>> {
    public boolean treesCmp(Node<T> node1, Node<T> node2) {
        // base cases (leaf node)
        if (!(node1 != null && node2 != null)) {
            return node1 == node2;
        }

        if (node1.getData().compareTo(node2.getData()) != 0) {
            return false;
        }

        return (treesCmp(node1.getLeft(), node2.getLeft()) && treesCmp(node1.getRight(), node2.getRight()));
    }
}*/
