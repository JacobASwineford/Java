package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Calculates the probable profit for the game
 * of fatal fives. this is done through the amount of
 * stars shown next to the integer profit.
 *
 * @author Jacob Swineford
 */
public class FatalFivesStars {

    public static void main(String[] args) {

        final int NUM_GAMES = 1_000_000;
        int[] values = new int[19];


        // output the possible profits min = 0 max = 18
        for (int i = 0; i < 19; i++) {
            for (int k = 0; k < NUM_GAMES; k++) {
                int fivesSum = playFatalFives();
                if (fivesSum == i) {
                    values[i]++;
                }
            }
            double per = (double) values[i] / NUM_GAMES * 100;
            String s = "";
            if (per < .5) {
                s = "< 1%";
            }
            for (int j = 0; j < Math.round(per); j++) {
                s += "*";
            }
            System.out.println(i + " " + s);
        }

    }
    /**
     * Plays the game of fatal fives and
     * returns the profit.
     */
    private static int playFatalFives() {
        Random rand = new Random();
        int rolls = 0;
        int maxRolls = 3;
        int profit = 0;
        while (rolls < maxRolls) {
            int x = rand.nextInt(6) + 1;
            if (x != 5) {
                profit += x;
            } else {
                break;
            }
            rolls++;
        }
        return profit;
    }
}
