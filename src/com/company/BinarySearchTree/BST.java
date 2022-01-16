package com.company.BinarySearchTree;


public class BST<T extends Comparable<T>> implements Tree<T> {

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
            } else {
                // CASE 4 two children
                Node<T> predecessor = node.getLeft();
                while (predecessor.getRight() != null) {
                    predecessor = predecessor.getRight();
                }
                if (predecessor.getParent().getLeft() == predecessor) {
                    predecessor.getParent().setLeft(null);
                } else {
                    predecessor.getParent().setRight(null);
                }
                node.setData(null);
                node.setData(predecessor.getData());
            }
        }
    }

    @Override
    public void traversal() {
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

    @Override
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

    @Override
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


    /************************************   Questions   ************************************/



    /*
        #1
            k-th the smallest element in a search tree overview

            Write an efficient in-place algorithm to find the k-th smallest (largest)
            item in a binary search tree!

            Good Luck!
                                                                                                 */
    public T getKthSmallest(int k) {
        if (k > treeSize(root)) {
            return null;
        }
        return getKthSmallest(root, k);
    }

    private T getKthSmallest(Node<T> node, int k) {
        int n = treeSize(node.getLeft()) + 1;
        if (n == k) {
            return node.getData();
        }

        if (n > k) {
            return getKthSmallest(node.getLeft(), k);
        }

        return getKthSmallest(node.getRight(), k - n);
    }

    // calculate the size of a subtree root node
    private int treeSize(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return ((treeSize(node.getLeft()) + treeSize(node.getRight())) + 1);
    }


    /*
        #2
            Compare binary trees overview

            Write an efficient algorithm that's able to compare two binary search trees.
            The method returns true if the trees are identical
            (same topology with same values in the nodes)
            otherwise it returns false.

            Good Luck!


                                                                                                        */
    public boolean tressCmp(BST<T> other) {
        return treesCmp(this.root, other.root);
    }

    private boolean treesCmp(Node<T> node1, Node<T> node2) {
        // base cases (leaf node)
        if (!(node1 != null && node2 != null)) {
            return node1 == node2;
        }

        if (node1.getData().compareTo(node2.getData()) != 0) {
            return false;
        }

        return (treesCmp(node1.getLeft(), node2.getLeft()) && treesCmp(node1.getRight(), node2.getRight()));
    }

    /*
        #3
            Family age problem overview

            Write an efficient algorithm to calculate the total sum of ages in a family tree.
            A family tree is a binary search tree
            in this case where all the nodes contain a Person object with [name,age] attributes.

            Good Luck!
                                                                                                                */

    public int getAgesSum() {
        return getAgesSum(root);
    }

    private int getAgesSum(Node<T> node) {

        if (node != null) {
            System.out.println("Considering node " + node);
        }

        int sum = 0;
        int left_sum = 0;
        int right_sum = 0;

        if (node == null) {
            return 0;
        }
        // we do simple post-order traversal because here we have to calculate both left and right value
        // to be able to calculate the parent value (sum of children ages)

        // check left subtree recursively
        left_sum = getAgesSum(node.getLeft());

        // check right subtree recursively
        right_sum = getAgesSum(node.getRight());

        // update the sum
        System.out.println("Considering node " + node + " total ages so far is "
                + (((Person) node.getData()).getAge() + left_sum + right_sum));
        sum = ((Person) node.getData()).getAge() + left_sum + right_sum;

        return sum;
    }


}
