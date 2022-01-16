package com.company.ArrayQuestions;

public class MainArrays {
    //trappedRain
    public static void mainArrays(){
        /*
            / reverseArray
            int[] arr = new int[]{1, 2, 3, 4, 5};

            ReverseArrayProblem reverse = new ReverseArrayProblem();
            reverse.reverseArray(arr);

            for (int i : arr) {
                System.out.println(i);
            }
        */



        /*
            / isAnagram
            char[] s1 = new char[]{'r', 'e', 's', 't', 'f', 'u', 'l'};
            char[] s2 = new char[]{'f', 'l', 'u', 's', 't', 'e', 'r'};

            AnagramProblem anagram = new AnagramProblem();

            System.out.println(anagram.isAnagram(s1, s2));
        */



        /*
            / isPalindrome
            char[] s = new char[]{'m', 'a', 'd', 'a', 'm'};

            PalindromeProblem palindrome = new PalindromeProblem();

            System.out.println(palindrome.isPalindrome(s));

         */



        /*
            /dutchFlagSolve
            int[] a = new int[]{0, 1, 2, 1, 2, 0, 0};

            DutchFlagProblem Dutch = new DutchFlagProblem();

            dutch.dutchFlagSolve2(a);
            ||
            dutch.dutchFlagSolve1(a);

            for (int i : a) {
                System.out.println(i);
            }

         */


        //trappedRain
        TrappedRainProblem trapped = new TrappedRainProblem();
        int[] a = new int[]{1, 0, 2, 1, 3, 1, 2, 0, 3};
        System.out.println(trapped.trappedRain(a));

    }
}
