package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Outputs a word input by the user having the first and last letters switched.
 *
 * @author Jacob Swineford
 */
public class EndSwitcher {

    public static void main(String[] args) {

        // initialize scanner and user instructions
        System.out.print("Enter a word: ");
        Scanner in = new Scanner(System.in);

        // takes the value from the input word
        String word = in.next();
        int wordLength = word.length();

        // make Strings of the first, middle, and last characters
        String firstLetter = word.substring(0, 1);
        String cutWord = word.substring(1, (wordLength - 1));
        String lastLetter = word.substring((wordLength - 1));

        // Outputs word
        System.out.println(lastLetter + cutWord + firstLetter);
    }
}
