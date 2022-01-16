package com.company.ArrayQuestions;

/*

                   The problem is that we want to reverse a T[] array
                   in O(N) linear time complexity.
                   We want the algorithm to be in-place as well -
                   The algorithm can not use additional memory!
                   For example: input is [1,2,3,4,5] then the output is [5,4,3,2,1]
                   Good luck!

                                                                                                             */

public class ReverseArrayProblem {
    public void reverseArray(int[] nums) {
        int high_index = nums.length;

        for (int i = 0; i < nums.length/2; ++i) {
            swap(nums, i, --high_index);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
