package com.company.Stacks.Questions;

import java.util.Stack;
/*
    Stack with queue overview

    The aim is to design a queue abstract data type
    with the help of single stacks.

    Complexity isn't the issue
*/

public class MakeQueue {

    private Stack<Integer> stack;

    MakeQueue() {
        stack = new Stack<>();
    }

    public void enqueue(int item) {
        stack.push(item);
    }

    public int dequeue(){
        int reuse;
        if (stack.size() == 1) {
             return stack.pop();
        }
        reuse = stack.pop();
        int temp = dequeue();
        stack.push(temp);
        return temp;
    }
}
