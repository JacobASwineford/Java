package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Plays the game of Eleven-Seventeen, where the player rolls
 * three dice and wins if the sum is between 11 and 17. (inclusive)
 * The player loses either if the sum of the numbers rolled is
 * not within that range, or the player rolls three identical numbers.
 *
 * @author Jacob Swineford
 */
public class ElevenSeventeen {

    public static void main(String[] args) {

        final int NUM_OF_GAMES = 1_000_000;
        int wins = 0;

        for (int i = 0; i < NUM_OF_GAMES; i++) {
            String winLoseMessage = getESWinMessage();
            if (winLoseMessage.contains("WIN")) {
                wins++;
            }
        }

        double winningPercentage = ((double) wins /
                NUM_OF_GAMES) * 100;
        System.out.printf("The winning probability is: %.2f %n",
                winningPercentage);




    }
    /**
     * Condenses the if statements for the game of Eleven-Seventeen
     * and returns the string of whether the player lost or not.
     */
    private static String getESWinMessage() {
        Random rand = new Random();

        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int die3 = rand.nextInt(6) + 1;
        int dieSum = die1 + die2 + die3;

        if (dieSum >= 11 && dieSum <= 17) {
            return ("WIN");
        } else if (die1 == die2 && die1 == die3) {
            return ("LOSE");
        } else {
            return ("LOSE");
        }
    }
}
