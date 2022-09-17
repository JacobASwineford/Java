package PracticeProblems.Completed.Java1;

import java.util.Random;
import java.util.Scanner;

/**
 * Prompts the user for a line of text and outputs
 * that text in a completely random order. This includes
 * the spaces that count as whitespace.
 *
 * @author Jacob Swineford
 */
public class RandomAnagram {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String phrase = in.nextLine();

        char[] pa = phrase.toCharArray();

        for (int i = 0; i < phrase.length() - 1; i++) {
            Random rand = new Random();
            int r = rand.nextInt(phrase.length() - i) + i;
            char t = pa[i]; // temporary character for when position i is overwritten.
            pa[i] = pa[r];
            pa[r] = t;
        }

        String anagram = new String(pa);
        System.out.println(anagram);
    }
}
