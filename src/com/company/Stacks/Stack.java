package com.company.Stacks;

public class Stack<T> {
    private T[] stack;
    private int count;

    public Stack() {
        stack = (T[]) new Object[1];
    }


    public void push(T newData) {
        // if there is to many items will double size of the array
        if (count == stack.length) {
            resize(2 * count);
        }
        stack[count++] = newData;
    }

    // this is the bottleneck
    private void resize(int capacity) {
        T[] stackCopy = (T[]) new Object[capacity];

        // copy the items
        for (int i = 0; i < count; ++i) {
            stackCopy[i] = stack[i];
        }
        stack = stackCopy;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T temp = stack[--count];

        // obsolete references - avoid memory leak
        stack[count] = null;
        return temp;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
