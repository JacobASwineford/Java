package Homework.Java1.SwinefordHW3;

import java.util.Scanner;

/**
 * given a string of digits and an integer from the user, gives
 * the greatest valued substring of numbers with the integer amount
 * of digits.
 *
 * My version also includes if the user fails to meet the conditions,
 * where a message will pop up saying how many more digits the first
 * value must contain for the program to work.
 *
 * Does not work if the substring value (integer) entered is negative,
 * or if the biggest substring is proceeded by a 0. the program will
 * run if the first input string is as such, but will not output the
 * 0.
 *
 * @author Jacob Swineford
 */
public class GreatestSubstring {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string of digits and an integer: ");
        String inputString = in.next();

        // values that always stay the same
        final int STARTING_POSITIONS = in.nextInt();
        int charInString = inputString.length();

        // values that get manipulated by the loop
        int positions = STARTING_POSITIONS;
        int instances = charInString - (STARTING_POSITIONS - 1);
        int greatestValue = 0;
        int startOfSubstring = 0;

        if (instances <= 0) {
            System.out.println("There are not enough digits for" +
                    " that integer.\n" +
                    "The string of digits must have at least " +
                    (positions - charInString) + " more digit(s).");
        } else {
            while (instances >= 1) {
                int cutString = Integer.parseInt(
                        inputString.substring(
                                startOfSubstring, positions));
                if (cutString > greatestValue) {
                    greatestValue = cutString;
                }
                startOfSubstring++;
                positions++;
                instances--;
            }
            System.out.println("Greatest " + STARTING_POSITIONS +
                    "-digit substring: " + greatestValue);

        }
    }
}
