package com.company.AVLTree;

public class App {

    public static void main(String[] args) {
        Tree<Integer> avl = new AVLTree<>();
        avl.insert(1);
        avl.insert(3);
        avl.insert(4);

        avl.traverse();
    }
}
