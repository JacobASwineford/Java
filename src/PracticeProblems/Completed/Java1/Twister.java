package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Takes a string and formats it into 4 equal strings, where the second and
 * the fourth are reversed. this allows the reader to read each segment of
 * the word in a zig-zag pattern (starting left).
 *
 * does not include the spacing at the end.
 *
 * Throws an exception when the word entered is one, two, or five characters long.
 *
 * @author Jacob Swineford
 */
public class Twister {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = in.nextLine();
        int wordLength = word.length();
        int digits = getDigits(wordLength);

        // four instances of substrings
        String substringOne = word.substring(0, digits);
        String substringTwo = word.substring(digits, digits * 2);
        String substringThree = word.substring(digits * 2, digits * 3);
        String substringFour = word.substring(digits * 3);

        StringBuilder twoReverse = new StringBuilder(substringTwo).reverse();
        StringBuilder fourReverse = new StringBuilder(substringFour).reverse();

        System.out.println(substringOne + "\n" + twoReverse + "\n" +
                substringThree + "\n" + Spacing(wordLength) + fourReverse);

    }

    /**
     * gets the amount of digits that the substrings are to be cut at.
     * 5 is a special case.
     */
    private static int getDigits(int x) {
        if (x > 0 && (x % 4) != 0) {
            return ((x / 4) + 1);

        } else if (x % 4 == 0) {
            return (x / 4);

        } else {
            return 0;
        }
    }

    /**
     * dictates the amount of spaces the last string of characters
     * receives if the wod length is not a multiple of 4.
     */
    private static String Spacing(int d) {

        int spacesNeeded = (getDigits(d) * 4) - d;

        while (spacesNeeded > 0) {
            spacesNeeded--;
            return " ";
        }
        return null;
    }
}
