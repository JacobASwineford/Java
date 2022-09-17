package JavaDemos.Java1.ch7.ch7a;

import java.util.Random;

/**
 * Monte Carlo simulation to calculate the probability of each possible
 * sum when rolling a pair of dice.
 *
 * @author Jacob Swineford
 */
public class DiceRoller {

    public static void main(String[] args) {

        int[] sums = new int[13]; // [] represents arrays

        // Roll the dice many times and increment the counters
        final int NUM_ROLLS = 10_000_000;
        for (int i = 0; i < NUM_ROLLS; i++) {
            int k = rollDice();
            sums[k]++;
        }

        // Display results.
        System.out.println("SUM \t PROBABILITY");
        for (int i = 2; i <= 12; i++) {
            double percent = (double) sums[i] / NUM_ROLLS * 100;

            // Add a line of stars of length equal to percentage.
            String stars = "";
            for (int j = 0; j < Math.round(percent); j++) {
                stars += "*";
            }

            String str = "%3d %4.1f%% %s %n";
            System.out.printf(str, i, percent, stars);
        }

    }

    /**
     *
     *
     */
    private static int rollDice() {
        Random rand = new Random();
        int x = rand.nextInt(6) + 1;
        int y = rand.nextInt(6) + 1;
        return x + y;
    }
}

