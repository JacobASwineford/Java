package PracticeProblems.NotCompleted.Java1;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class SumSentences {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string of digits: ");
        String digits = in.nextLine();

        System.out.println(sumSentences(digits));

        // the numbers before must add up to the sum
        // helper method should return a boolean value
    }
    /**
     * Finds out if the given string of digits forms
     * a sum sentence or not. returns a boolean value
     * that states whether this is true or false.
     */
    private static String sumSentences(String str) {
        int maxSum = str.length() / 2;
        boolean odd = false;

        //if odd
        if (str.length() % 2 != 0) {
            maxSum = (str.length() / 2) + 1;
            odd = true;
        }

        for (int i = 0; i < str.length(); i++) {
             String maxStr = str.substring(maxSum); //max sum increases for less substring
             int parseSum = Integer.parseInt(maxStr); // decreases as substring gets lower

            maxSum++;
        }
        return "NOT VALID";
    }
}
