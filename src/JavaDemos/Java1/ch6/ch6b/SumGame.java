package JavaDemos.Java1.ch6.ch6b;

import java.util.Random;

/**
 * A Monte Carlo simulation to calculate the probability of winning the
 * Sum Game.
 *
 * In this game, the player rolls three dice and wins if the number
 * obtained on one of the dice is the sum of the numbers on the other
 * two dice.
 *
 * @author Jacob Swineford
 */
public class SumGame {

    public static void main(String[] args) {
        final int GAMES_TO_PLAY = 10_000_000;
        int wins = 0;

        for (int i = 0; i < GAMES_TO_PLAY; i++) {
            if (playerWins()) {
                wins++;
            }
        }


        // calculate probability of winning
        double prob = (double) wins / GAMES_TO_PLAY * 100;
        System.out.printf("Probability of winning the Sum Game: %.2f%% %n",
                prob);

    }
    /**
     * Simulates the Sum Game.
     *
     * @return true if the player wins, false otherwise
     */
    private static boolean playerWins() {
        Random rand = new Random();
        int x = 1 + rand.nextInt(6);
        int y = 1 + rand.nextInt(6);
        int z = 1 + rand.nextInt(6);

        if (x + y == z || x + z == y || y + z == x) {
            return true;
        }
        return false;

        // Simplest:
        // Return x + y ==z || x + z ++ y || y + z ++ x;
    }
}
