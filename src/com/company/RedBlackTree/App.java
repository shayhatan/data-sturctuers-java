package com.company.RedBlackTree;

public class App {
    public static void main(String[] args) {
        Tree<Integer> rbt = new RedBlackTree<>();
        rbt.insert(10);
        rbt.insert(20);
        rbt.insert(39);
        rbt.insert(49);
        rbt.remove(10);
        rbt.traverse();
    }
}
