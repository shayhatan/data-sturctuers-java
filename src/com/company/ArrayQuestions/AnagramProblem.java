package com.company.ArrayQuestions;

import java.util.Arrays;

/*
        construct an algorithm to check whether two words are anagrams or not!
        An anagram is a word or phrase formed by rearranging the letters of a different word
        , typically using all the original letters exactly once

        For example: restful and fluster are anagrams.

        Good luck!
 */

public class AnagramProblem {
    public boolean isAnagram(char[] s1, char[] s2) {

        if (s1.length != s2.length) {
            return false;
        }

        // sort the letters of the strings
        // O(NlogN) this is the "bottleneck" of the algorithm
        Arrays.sort(s1);
        Arrays.sort(s2);

        for (int i = 0; i < s1.length; ++i) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }

}
