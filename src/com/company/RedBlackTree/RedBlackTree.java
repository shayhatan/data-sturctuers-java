package com.company.RedBlackTree;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    @Override
    public void insert(T data) {
        // first node
        if (root == null) {
            root = new Node<>(data, null);
            settleViolations(root);
        } else {
            // not empty
            insert(data, root);
        }
    }

    private void insert(T data, Node<T> node) {

        // works like strCmp
        if (node.getData().compareTo(data) < 0) {
            if (node.getRight() == null) {
                Node<T> new_node = new Node<>(data, node);
                node.setRight(new_node);

                // MUST CHECK whether the red black properties violated
                settleViolations(new_node);
            } else {
                insert(data, node.getRight());
            }
        } else {
            if (node.getLeft() == null) {
                Node<T> new_node = new Node<>(data, node);
                node.setRight(new_node);

                // MUST CHECK whether the red black properties violated
                settleViolations(new_node);
            } else {
                insert(data, node.getLeft());
            }
        }
    }

    @Override
    public void remove(T data) {
        if (root == null) {
            return;
        }
        remove(data, root);
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
                settleViolations(parent);
            } else {
                // CASE 4 two children
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

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRight() != null) {
            return getPredecessor(node.getRight());
        }
        return node;
    }

    private void settleViolations(Node<T> node) {

        // document of properties and cases attached

        Node<T> parent_node = null;
        Node<T> grandparent = null;

        // must check violations up to the root node
        while (node != root && isRed(node) && isRed(node.getParent())) {

            parent_node = node.getParent();
            grandparent = node.getParent().getParent();

            if (parent_node == grandparent.getLeft()) {
                Node<T> uncle = grandparent.getRight();

                // CASE 1 or 4 (RECOLORING)
                if (isRed(uncle)) {
                    grandparent.setColor(NodeColor.RED);
                    parent_node.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);

                    // update references we keep going up to the root node
                    node = grandparent;
                } else {
                    // CASE 2
                    if (node == parent_node.getRight()) {
                        leftRotation(parent_node);

                        // update references we keep going up to the root node
                        node = parent_node;
                        parent_node = grandparent;
                    }

                    // CASE 3: rotation on the grandparent + parent and grandparent switch color
                    rightRotation(grandparent);

                    // for debug
                    System.out.println("Recoloring " + parent_node + " + " + grandparent);

                    // swap the color of the parent and the grandparent
                    NodeColor temp_color = parent_node.getColor();
                    parent_node.setColor(grandparent.getColor());
                    grandparent.setColor(temp_color);

                    // update references we keep going up to the root node
                    node = parent_node;
                }
            } else {
                // symmetric cases...

                // parent is a right child of it's parent
                Node<T> uncle = grandparent.getLeft();
                // CASE 1 and 4
                if (isRed(uncle)) {
                    grandparent.setColor(NodeColor.RED);
                    parent_node.setColor(NodeColor.BLACK);
                    node = grandparent;
                } else {
                    // CASE 2
                    if (node == parent_node.getLeft()) {
                        rightRotation(parent_node);
                        node = parent_node;
                        parent_node = grandparent;
                    }
                    // CASE 3
                    leftRotation(grandparent);

                    // for debug
                    System.out.println("Recoloring " + parent_node + " + " + grandparent);
                    NodeColor temp_color = parent_node.getColor();
                    parent_node.setColor(grandparent.getColor());
                    grandparent.setColor(temp_color);
                    node = parent_node;
                }
            }
        }
        root.setColor(NodeColor.BLACK);
    }

    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.getColor() == NodeColor.RED;
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


    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        // in-order traversal in O(N) linear running time
        traverse(root);
    }

    private void traverse(Node<T> node) {

        if (node.getLeft() != null) {
            traverse(node.getLeft());
        }

        System.out.print(node + " ");

        if (node.getRight() != null) {
            traverse(node.getRight());
        }
    }


    public T getMin() {
        if (root == null) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeft() == null) {
            return node.getData();
        }
        return getMin(node.getLeft());
    }


    public T getMax() {
        if (root == null) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRight() == null) {
            return node.getData();
        }
        return getMax(node.getRight());
    }
}
