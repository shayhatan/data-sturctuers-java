package com.company.ArrayQuestions;

/*

        "A palindrome is a string that reads the same forward and backward"

        For example: radar or madam

        Design an optimal algorithm for checking whether a given string is palindrome or not!

        Good luck!

 */


public class PalindromeProblem {
    public boolean isPalindrome(char[] s) {

        int high = s.length;

        for (int i = 0; i < s.length / 2; ++i) {
            if (s[i] != s[--high]) {
                return false;
            }
        }
        return true;
    }
}
