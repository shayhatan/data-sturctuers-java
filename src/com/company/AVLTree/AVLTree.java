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
        settleViolations(node);
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
            remove(data, node.getLeft());
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
                settleViolations(parent);
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
                settleViolations(parent);
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
                settleViolations(parent);
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

    private void rightRotation(Node<T> node) {
        Node<T> temp_left = node.getLeft();
        Node<T> grand_child = temp_left.getRight();
        Node<T> temp_parent = node.getParent();

        // make rotation
        temp_left.setRight(node);
        node.setLeft(grand_child);

        // handle parents
        if (grand_child != null) {
            grand_child.setParent(node);
        }

        node.setParent(temp_left);
        temp_left.setParent(temp_parent);

        if (temp_parent == null) {
            if (node == root) {
                root = temp_left;
            }
        } else {
            if (temp_parent.getLeft() == node) {
                temp_parent.setLeft(temp_left);
            } else {
                temp_parent.setRight(temp_left);
            }
        }

        // update height
        updateHeight(node);
        updateHeight(temp_parent);
    }

    private void leftRotation(Node<T> node) {
        Node<T> temp_right = node.getRight();
        Node<T> grand_child = temp_right.getLeft();
        Node<T> temp_parent = node.getParent();

        // make rotation
        temp_right.setLeft(node);
        node.setRight(grand_child);

        // handle parents
        if (grand_child != null) {
            grand_child.setParent(node);
        }

        node.setParent(temp_right);
        temp_right.setParent(temp_parent);

        if (temp_parent == null) {
            if (node == root) {
                root = temp_right;
            }
        } else {
            if (temp_parent.getLeft() == node) {
                temp_parent.setLeft(temp_right);
            } else {
                temp_parent.setRight(temp_right);
            }
        }

        // update height
        updateHeight(node);
        updateHeight(temp_parent);
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
        if (node == null) {
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

    private void settleViolations(Node<T> node) {
        // we must check up to the root node O(logN)
        while (node != null) {
            updateHeight(node);
            violations(node);
            node = node.getParent();
        }
    }

    private void violations(Node<T> node) {
        int balance = getBalance(node);
        if (balance > 1) {

            // CASE 1: left-right salutation
            if (getBalance(node.getLeft()) < 0) {
                leftRotation(node.getLeft());
            }
            // CASE 2: right rotation
            rightRotation(node);
        }
        if (balance < -1) {
            // CASE 3: right-left salutation
            if (getBalance(node.getRight()) > 0) {
                rightRotation(node.getRight());
            }
            // CASE 4: left rotation
            leftRotation(node);
        }
    }

}
