package PracticeProblems.Completed.Java1;

import java.util.Random;
import java.util.Scanner;

/**
 * Uses a monte carlo simulation to estimate how much a certain
 * number is rolled on a dice. how many dice there are and the number
 * of sides on those dice is determined by the user.
 *
 * if the numbers of sides per dice is really high, there is a chance
 * that the probability will show up as 0. this is a rounding problem with
 * the output and should be noted that these are just really small numbers
 * instead of inaccurate code.
 *
 * @author Jacob Swineford
 */
public class DiceSumsDeluxe {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of dice: ");
        int dice = in.nextInt();
        System.out.println("Enter the sides per die: ");
        int sides = in.nextInt();

        int[] sums = new int[dice * sides + 1]; // [] represents arrays

        final int NUM_ROLLS = 1_000_000;
        for (int i = 0; i < NUM_ROLLS; i++) {
            int x = rollDice(dice, sides);
            sums[x]++;
        }

        // Display results.
        System.out.println("SUM \t PROBABILITY");
        for (int i = dice; i <= (dice * sides); i++) {
            double percent = (double) sums[i] / NUM_ROLLS * 100;

            // Add a line of stars of length equal to percentage.
            String stars = "";
            for (int j = 0; j < Math.round(percent); j++) {
                stars += "*"; // lets you add a string to an existing string
            }

            String str = "%2d. %11.1f%% %s %n";
            System.out.printf(str, i, percent, stars);
        }

    }

    /**
     * Rolls the given number of dice at random and returns
     * the sum.
     */
    private static int rollDice(int dice, int sides) {
        Random rand = new Random();
        int total = 0;
        for (int i = 0; i < dice; i++) {
            int roll = rand.nextInt(sides) + 1;
            total += roll;
        }
        return total;
    }
}
