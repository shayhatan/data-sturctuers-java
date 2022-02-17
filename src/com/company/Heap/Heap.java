package com.company.Heap;

/**
 * MAX HEAP
 */

public class Heap {

    private Integer[] heap;
    private int size;

    public Heap(int size) {
        this.heap = new Integer[size];
    }

    public void insert(int data) {
        if (isFull()) {
            throw new RuntimeException("Heap is full");
        }

        heap[size] = data;

        // check heap properties
        fixUp(size++);
    }

    private void fixUp(int index) {
        int parent_index = (index - 1) / 2;

        if (index > 0 && heap[index] > heap[parent_index]) {
            swap(index, parent_index);
            fixUp(parent_index);
        }
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public int getMax() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }

    public int pool() {
        int max = getMax();
        swap(0, --size);

        fixDown(0);

        return max;
    }

    private void fixDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        if (left < size) {

            if (heap[index] < heap[left]) {
                largest = left;
            }

            if (right < size) {

                if (heap[largest] < heap[right]) {
                    largest = right;
                }
            }

            if (index != largest) {
                swap(index, largest);
                fixDown(largest);
            }
        }
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    //heapsort is doing pool() operation for N times - O(N(logN))
    public void heapsort() {
        int n = size;

        for (int i = 0; i < n; ++i) {
            int max = pool();
            System.out.println(max + " ");
        }
    }

    public int getSize() {
        return size;
    }

}
