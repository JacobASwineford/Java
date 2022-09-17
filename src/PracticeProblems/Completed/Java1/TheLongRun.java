package PracticeProblems.Completed.Java1;

import java.util.Random;
import java.util.Scanner;

/**
 * Given the amount of coins, outputs the
 * expected length of the longest run.
 *
 * This done through the use of a Monte Carlo Simulation.
 * The expected length is rounded down.
 *
 * WARNING: THIS VERSION COUNTS THE LONGEST RUN AS A MINIMUM
 * OF ONE, BUT THIS IS AN EXTREME CASE AND WONT COME Up OFTEN.
 *
 * @author Jacob Swineford
 */
public class TheLongRun {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of coins: ");
        int numCoins = in.nextInt();

        final int NUM_TESTS = 1_000_000;
        int total = 0;

        for (int k = 0; k < NUM_TESTS; k++) {
            int run = longestRun(randomArray(numCoins));
            total += run;
        }

        int per = total / NUM_TESTS;
        System.out.println("Expected length of the run: " + per);

    }
    /**
     * Simulates the flips of coins and determine whether
     * they land on heads or tails. Then, finds the longest run
     * of either heads or tails and returns it.
     */
    private static int[] randomArray(int numCoins) {
        Random rand = new Random();
        int[] x = new int[numCoins];

        // make random alternating values
        for (int i = 0; i < numCoins; i++) {
            int r = rand.nextInt(2);
            if (r == 0) {
                x[i] = 1;
            }
        }
        return x;
    }
    /**
     * finds the longest run of the random array and
     * returns the integer length of the run.
     */
    private static int longestRun(int[] r) {

        int longestRun = 1;
        int tempMax = 0;

        // check how long the longest run is
        for (int k = 0; k < r.length - 1; k++) {
            if (r[k] == r[k  + 1]) {
                longestRun++;
            } else {
                longestRun = 1;
            }
            if (longestRun > tempMax) {
                tempMax = longestRun;
            }
        }
        return tempMax;
    }
}
