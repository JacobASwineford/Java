package Homework.Java1.SwinefordHW1;

import java.util.Random;
import java.util.Scanner;

/**
 * The user enters a word and an anagram is made by swapping two random
 * portions of the word. In the event that the word count after being randomized
 * (line 23, first portion) is 0, there is a +1 to prevent the situation of
 * division over zero on line 25.
 *
 * @author Jacob Swineford
 */

public class RandomAnagram {

    public static void main(String[] args) {

        System.out.print("Enter a word: ");
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        String word = in.next();

        // integers calculated for use in substring positions
        int wordLength = word.length();
        int randomWordCount = rand.nextInt(word.length()) + 1;
        int randomPosition = (word.length() / randomWordCount) + 1;

        // Strings made from random int outputs
        String firstHalf = word.substring(0, randomPosition);
        String secondHalf = word.substring(randomPosition, wordLength);

        // output anagram
        System.out.println(secondHalf + firstHalf);

    }
}
