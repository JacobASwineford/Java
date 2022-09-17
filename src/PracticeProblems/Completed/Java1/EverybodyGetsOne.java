package PracticeProblems.Completed.Java1;

import java.util.Random;
import java.util.Scanner;

/**
 * Determines the expected number of balls
 * that would have to be tossed at random
 * to fill a user specified number of bins.
 *
 * Reformation of the famous Coupon Collectors Problem.
 * @author Jacob Swineford
 */
public class EverybodyGetsOne {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many bins: ");
        int bins = in.nextInt();

        final int NUM_GAMES = 1_000_000;
        int ballsTossed = 0;

        for (int k = 0; k < NUM_GAMES; k++) {
            int x = binThrows(bins);
            ballsTossed += x;
        }

        int av = ballsTossed / NUM_GAMES;
        System.out.println("Expected number of balls tossed before " +
                "every bin has one: " + av);
    }
    /**
     * finds and returns the amount of balls thrown to
     * fill all the bins specified.
     */
    private static int binThrows(int numBins) {
        Random rand = new Random();
        boolean[] bins = new boolean[numBins];
        int tosses = 0;

        while (!allFull(bins)) {
            int r = rand.nextInt(numBins);
            bins[r] = true;
            tosses++;
        }

        return tosses;
    }
    /**
     * Checks if the given boolean[] has all true values
     * or not. returns false if values are not all true,
     * and returns true when they are true.
     */
    private static boolean allFull(boolean[] arr) {
        int truthTeller = 0;
        int truth = arr.length;
        for (boolean b : arr) {
            if (b) {
                truthTeller++;
            }
        }

        if (truth == truthTeller) {
            return true;
        }

        return false;
    }
}
