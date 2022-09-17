package JavaDemos.Java1.ch6.ch6c;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * A Monte Carlo simulation to estimate the probability of winning Craps
 * and the expected length of a game.
 *
 * @author Jacob Swineford
 */
public class CrapsProbabilityCalculator3 {

    private static int wins = 0; // class or static variables not part of class
    private static int numRolls = 0; // global for all methods for the class
    private static int maxRolls = 0; // maximum number of rolls for all games

    public static void main(String[] args) {

        final int NUM_GAMES = 1_000_000;

        for (int i = 0; i < NUM_GAMES; i++) {
            playCraps();
        }

        // calculate probability and construct output strings
        double winProb = (double) wins / NUM_GAMES * 100;
        DecimalFormat df = new DecimalFormat(".000"); // ### makes stuff like .3 stay .3 instead of .300
        String s1 = String.format("%,d/%,d", wins, NUM_GAMES);
        String s2 = "Winning percentage: " + df.format(winProb) + "%";

        // calculate expected length of the game
        double expectedLength = (double) numRolls / NUM_GAMES;
        String s3 = "Expected length of game: " + df.format(expectedLength);

        String s4 = String.format("Maximum number of rolls: %d", maxRolls);

        String output = s1 + "\n" + s2 + "\n" + s3 + "\n" + s4;
        System.out.println(output);
    }

    /**
     * Simulate the game of Craps.
     */
    private static void playCraps() {
        int firstSum = rollDice();
        numRolls++;
        if (firstSum == 7 || firstSum == 11) {
            wins++;
            return;
        }
        if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
            return;
        }

        int newSum = rollDice();
        int rollsInThisGame = 2;
        numRolls++;
        while (newSum != firstSum && newSum != 7) {
            numRolls++;
            newSum = rollDice();
        }

        numRolls += rollsInThisGame;

        if (newSum == firstSum) {
            wins++;
        }

        if (rollsInThisGame > maxRolls); {
            maxRolls = rollsInThisGame;
        }
    }

    /**
     * Returns the sum of two dice rolls.
     */
    private static int rollDice() {
        Random rand = new Random();
        int d1 = 1 + rand.nextInt(6);
        int d2 = 1 + rand.nextInt(6);
        return d1 + d2;
    }
}
