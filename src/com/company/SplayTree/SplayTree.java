package com.company.SplayTree;

public class SplayTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(T data) {

        if (root == null) {
            root = new Node<>(data, null);
        } else {
            insert(root, data);
        }
    }

    private void insert(Node<T> node, T data) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), data);
            } else {
                Node<T> new_node = new Node<>(data, node);
                node.setLeft(new_node);

                splay(new_node);
            }
        } else {
            if (node.getRight() != null) {
                insert(node.getRight(), data);
            } else {
                Node<T> new_node = new Node<>(data, node);
                node.setRight(new_node);

                splay(new_node);
            }
        }
    }

    private void splay(Node<T> node) {
        while (node.getParent() != null) {

            // ZIG CASE: if grandparent is a null it means that we have to make simple rotation
            if (node.getParent().getParent() == null) {
                // right rotate
                if (node.getParent().getLeft() == node) {
                    rightRotation(node.getParent());
                } else {
                    leftRotation(node.getParent());
                }
            }
            // ZIG ZIG CASE.
            else if (node.getParent().getLeft() == node &&
                    node.getParent().getParent().getLeft() == node.getParent()) {
                rightRotation(node.getParent().getParent());
                rightRotation(node.getParent());
            }
            // ZIG ZIG CASE - symmetric
            else if (node.getParent().getRight() == node &&
                    node.getParent().getParent().getRight() == node.getParent()) {
                leftRotation(node.getParent().getParent());
                leftRotation(node.getParent());
            }
            // ZIG ZAG CASE.
            else if (node.getParent().getLeft() == node &&
                    node.getParent().getParent().getRight() == node.getParent()) {
                rightRotation(node.getParent());
                leftRotation(node.getParent());
            } else {
                leftRotation(node.getParent());
                rightRotation(node.getParent());
            }
        }
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
    }

    @Override
    public T find(T data) {
        if (root == null) {
            return null;
        }
        return find(data, root);
    }

    private T find(T data, Node<T> node) {
        if (node == null) {
            return null;
        } else if (data.compareTo(node.getData()) < 0) {
            return find(data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            return find(data, node.getRight());
        } else {
            splay(node);
            return data;
        }
    }

    @Override
    public void traverse() {
        if (root != null) {
            traverse(root);
        }
    }

    private void traverse(Node<T> node) {
        if (node.getLeft() != null) {
            traverse(node.getLeft());
        }

        System.out.println(node + " ");

        if (node.getRight() != null) {
            traverse(node.getRight());
        }
    }

    @Override
    public T getRoot() {
        if (root == null) {
            return null;
        }
        return root.getData();
    }
}
