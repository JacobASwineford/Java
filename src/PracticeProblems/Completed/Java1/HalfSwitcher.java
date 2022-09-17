package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Takes the first and second half of a given word and flips their positions.
 *
 * @author Jacob Swineford
 */
public class HalfSwitcher {

    public static void main(String[] args) {

        // Initialize scanner and user instructions
        System.out.print("Enter a word: ");
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();

        // Formats the first and second half of the word
        String firstHalf = word.substring(0, (word.length() / 2));
        String secondHalf = word.substring(word.length() / 2);

        // Outputs result with the second half of the string first
        System.out.println(secondHalf + firstHalf);

    }
}
