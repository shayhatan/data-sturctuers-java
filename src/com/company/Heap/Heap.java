package com.company.Heap;

/**
 * MAX HEAP
 */

public class Heap {

    private Integer[] heap;
    private int current_position = -1;

    public Heap(int size) {
        this.heap = new Integer[size];
    }

    public void insert(int item) {

        if (isFull()) {
            throw new RuntimeException("Heap is full...");
        }

        heap[++current_position] = item;
        fixUp(current_position);
    }

    private void fixUp(int index) {
        int parent_index = (index - 1) / 2;

        while (parent_index >= 0 && heap[parent_index] < heap[index]) {
            swap(index, parent_index);
            index = parent_index;
            parent_index = (index - 1) / 2;
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // remove the root node
    public int pool() {
        int result = heap[0];
        heap[0] = heap[current_position--];
        heap[current_position + 1] = null; // avoid obsolete references
        fixDown(current_position);
        return result;
    }

    public void heapsort() {
        for (int i = 0; i <= current_position; i++) {
            int temp = this.heap[0];
            System.out.print(temp + " ");
            heap[0] = heap[current_position - i];
            heap[current_position - i] = temp;
            fixDown(current_position - i - 1);
        }
    }

    private void fixDown(int upto) {
        int index = 0;
        if (upto < 0) {
            upto = current_position;
        }
        int left_child = 0;
        int right_child;

        while (index <= upto && left_child <= upto) {

            left_child = 2 * index + 1;
            right_child = 2 * index + 2;

            int child_to_swap;

            if (right_child > upto) {
                child_to_swap = left_child;
            } else {
                child_to_swap = (heap[left_child] > heap[right_child]) ? left_child : right_child;
            }

            if (heap[index] < heap[child_to_swap]) {
                swap(index, child_to_swap);
                index = child_to_swap;
            } else {
                index = upto + 1;
            }
        }
    }

    private boolean isFull() {
        return this.current_position == this.heap.length;
    }
}
