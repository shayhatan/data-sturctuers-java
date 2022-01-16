package com.company.AVLTree;


import com.company.ArrayQuestions.PalindromeProblem;

public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(T data) {
        // first node
        if (root == null) {
            root = new Node<>(data, null);
        } else {
            // not empty
            insert(data, root);
        }

    }

    private void insert(T data, Node<T> node) {

        // works like strCmp
        if (node.getData().compareTo(data) < 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(data, node));
            } else {
                insert(data, node.getRight());
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(data, node));
            } else {
                insert(data, node.getLeft());
            }
        }
        updateHeight(node);
    }

    @Override
    public void remove(T data) {
        if (root == null) {
            return;
        }
        remove(data, root);
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRight() != null) {
            return getPredecessor(node.getRight());
        }
        return node;
    }

    private void remove(T data, Node<T> node) {
        // first we find the node
        if (node.getData().compareTo(data) < 0) {
            remove(data, node.getRight());
        } else if (node.getData().compareTo(data) > 0) {
            remove(data, node);
        } else {
            // we have found the node
            // CASE 1 no children at all
            if (node.getLeft() == null && node.getRight() == null) {
                Node<T> parent = node.getParent();
                if (parent != null) {
                    if (parent.getLeft() == node) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } else {
                    root = null;
                }
                // remove the node and makes it eligible for Garbage Collector
                node = null;
                updateHeight(parent);
            }
            // CASE 2 only left child
            else if (node.getLeft() != null && node.getRight() == null) {
                Node<T> parent = node.getParent();
                if (parent != null) {
                    if (parent.getLeft() == node) {
                        parent.setLeft(node.getLeft());
                    } else {
                        parent.setRight(node.getLeft());
                    }
                } else {
                    root = node.getLeft();
                }
                node = null;
                updateHeight(parent);
            }
            // CASE 3 only right
            else if (node.getRight() != null && node.getLeft() == null) {

                Node<T> parent = node.getParent();
                if (parent != null) {
                    if (parent.getLeft() == node) {
                        parent.setLeft(node.getRight());
                    } else {
                        parent.setRight(node.getRight());
                    }
                } else {
                    root = node.getRight();
                }
                node = null;
                updateHeight(parent);
            } else {
                // CASE 4 two children
                Node<T> predecessor = getPredecessor(node.getLeft());
                T temp = predecessor.getData();
                predecessor.setData(node.getData());
                node.setData(temp);

                //we call to delete method recursively on the predecessor
                remove(data, predecessor);
            }
        }
    }

    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        // in-order traversal in O(N) linear running time
        traversal(root);
    }

    private void traversal(Node<T> node) {

        if (node.getLeft() != null) {
            traversal(node.getLeft());
        }

        System.out.print(node + " ");

        if (node.getRight() != null) {
            traversal(node.getRight());
        }
    }

    private void updateHeight(Node<T> node) {
        if(node == null) {
            return;
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }

        return node.getHeight();
    }

    private int getBalance(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

}
