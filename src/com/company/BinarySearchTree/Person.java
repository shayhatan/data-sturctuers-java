package com.company.BinarySearchTree;

import java.util.Comparator;

public class Person implements Comparable<Person> {

    private int age;
    private String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " " + age;
    }


    @Override
    public int compareTo(Person o) {
        return Comparator.comparingInt(com.company.BinarySearchTree.Person::getAge).
                thenComparing(com.company.BinarySearchTree.Person::getName).compare(this, o);
    }
}

