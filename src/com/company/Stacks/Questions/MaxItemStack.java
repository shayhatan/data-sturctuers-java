package com.company.Stacks.Questions;

import java.util.Stack;

/*
    Max in a stack problem overview:

    The aim is to design an algorithm that can return the
    maximum item of a stack in O(1) running time complexity, integrate with all methods.
    We can use O(N) extra memory.

 */
public class MaxItemStack {

    //  tracking maxItem
    private Stack<Integer> maxStack;
    private Stack<Integer> stack;

    public void push(int item) {
        stack.push(item);
        if (maxStack.isEmpty()) {
            maxStack.push(item);
        } else {
            if (maxStack.peek() < stack.peek()) {
                maxStack.push(item);
            } else {
                maxStack.push(maxStack.peek());
            }
        }
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int getMax() {
        return maxStack.peek();
    }

    public int peek() {
        return stack.peek();
    }
}
