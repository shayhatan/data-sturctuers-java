package com.company.ArrayQuestions;


import java.lang.Math;

/*
    Given n non-negative integers representing an elevation map
    where the width of each bar is 1.
    Compute how much water it can trap after raining!
*/
public class TrappedRainProblem {
    //the smaller values out of three
    private int x;
    private int y;

    public int trappedRain(int[] a) {
        int i = 2;

        // index of the highest bar of all till current iteration
        int max_index = 0;

        // index of the highest value of current 3 bars
        int curr_max_index = 0;

        //  index of the second-highest bar of all till current iteration
        int second_max_val = 0;

        int trapped_rain = 0;

        /*

            each iteration measure the trapped water between 3 values
            if we found out after words that there is more room because of new value
            we add it by cover the distance between them times the room to add.

        */

        while (i < a.length) {

            curr_max_index = maxThree(a, i, i - 1, i - 2);
            trapped_rain += Math.abs(a[x] - a[y]);

            if (i == 2) {
                max_index = curr_max_index;
                second_max_val = Math.max(a[x], a[y]);
            } else {

                if (second_max_val < Math.max(a[x], a[y])) {
                    second_max_val = Math.max(a[x], a[y]);
                }

                if (a[curr_max_index] >= a[max_index] && max_index != curr_max_index) {

                    int to_add = a[max_index] - second_max_val;
                    int d = curr_max_index - max_index - 1;

                    trapped_rain += to_add * d;

                    max_index = curr_max_index;

                    // now the second_max_val must be zero
                    second_max_val = 0;
                }

            }
            i += 2;
        }
        return trapped_rain;
    }

    private int maxThree(int[] a, int x1, int x2, int x3) {
        if (a[x1] < a[x2]) {
            if (a[x3] < a[x2]) {
                x = x1;
                y = x3;
                return x2;
            } else {
                x = x1;
                y = x2;
                return x3;
            }
        } else {
            if (a[x3] < a[x1]) {
                x = x3;
                y = x2;
                return x1;
            } else {
                x = x1;
                y = x2;
                return x3;
            }
        }
    }

}
