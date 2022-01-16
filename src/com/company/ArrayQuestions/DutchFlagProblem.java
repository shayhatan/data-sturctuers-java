package com.company.ArrayQuestions;


/*
        The problem is that we want to sort a T[] one-dimensional array of integers in O(N)
        running time - and without any extra memory.
        The array can contain values:
        0, 1 and 2 (check out the theoretical section for further information).

        Good luck!
*/

public class DutchFlagProblem {

    private int[] bucket = new int[]{0, 0, 0};

    public void dutchFlagSolve1(int[] a) {
        for (int i : a) {
            ++bucket[i];
        }

        for (int i = 1; i < bucket.length; ++i) {
            bucket[i] += bucket[i - 1];
        }

        int curr = 0;
        for (int i = 0; i < bucket.length; ++i) {
            //int dest = bucket[i];
            while (curr != bucket[i]) {
                a[curr++] = i;
            }
        }
    }

    public void dutchFlagSolve2(int[] a) {
        int low = 0;
        int high = a.length - 1;
        int i = 0;
        int pivot = 1;

        while (i <= high) {
            if(a[i] < pivot) {
                swap(a, i++, low++);
            }
            else if(pivot < a[i]) {
                swap(a, i, high--);
            }
            else {
                ++i;
            }
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
