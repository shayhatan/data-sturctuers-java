package com.company.BinarySearchTree;


public class Main3 {
    public static void main(String[] args) {
        /*
        BST<Integer> bst1 = new BST<>();
        bst1.insert(12);
        bst1.insert(20);
        bst1.insert(4);
        bst1.insert(5);
        bst1.insert(1);
        System.out.println(bst1.getKthSmallest(2));*/
        BST<Person> bst1 = new BST<>();
        bst1.insert(new Person("Rachel", 47));
        bst1.insert(new Person("Phoebe", 21));
        bst1.insert(new Person("Joe", 55));
        bst1.insert(new Person("Monica", 20));
        bst1.insert(new Person("Chandler", 34));
        bst1.insert(new Person("Jack", 68));
        bst1.insert(new Person("Susan", 23));
        bst1.insert(new Person("Rose", 38));
        System.out.println(bst1.getAgesSum());

    }
}
