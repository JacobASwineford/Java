package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Thor has 9 4-sided dice and Zeus has 6 6-sided dice
 * that they each roll every game. whoever has the highest
 * total wins, and if the sums are equal than its a draw.
 *
 * This version includes a Monte Carlo simulation that
 * simulates the probability that Thor or Zeus will win,
 * as well as the average percentage of draws between
 * Thor and Zeus.
 *
 * @author Jacob Swineford
 */
public class ThorVsZeus {

    public static void main(String[] args) {

        final int NUM_OF_GAMES = 1_000_000;
        int thorWins = 0;
        int zeusWins = 0;
        int draws = 0;

        for (int i = 0; i < NUM_OF_GAMES; i++) {
            int thorSum = getThorSum();
            int zeusSum = getZeusSum();
            if (thorSum > zeusSum) {
                thorWins++;
            } else if (thorSum < zeusSum) {
                zeusWins++;
            } else {
                draws++;
            }
        }

        double thorWinningPer = ((double) thorWins /
                NUM_OF_GAMES) * 100;
        double zeusWinningPer = ((double) zeusWins /
                NUM_OF_GAMES) * 100;
        double drawPer = ((double) draws /
                NUM_OF_GAMES) * 100;

        System.out.printf("Winning percentages:\n%.2f%% THOR" +
                        "\n%.2f%% ZEUS \n%.2f%% DRAW %n",
                thorWinningPer, zeusWinningPer, drawPer);
    }
    /**
     * Simulates the rolls of thor and returns their sum.
     */
    private static int getThorSum() {
        Random rand = new Random();
        int totalSum = 0;
        for (int i = 0; i < 9; i++) {
            int roll = rand.nextInt(4) + 1;
            totalSum += roll;
        }
        return totalSum;
    }
    /**
     * Simulates the rolls of Zeus and returns their sum.
     */
    private static int getZeusSum() {
        Random rand = new Random();
        int totalSum = 0;
        for (int i = 0; i < 6; i++) {
            int roll = rand.nextInt(6) + 1;
            totalSum += roll;
        }
        return totalSum;
    }
}
