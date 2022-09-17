package Homework.Java1.SwinefordHW3;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Takes three dice rolls and outputs the profit gained in
 * the game of Fatal Fives. the profit gained is the sum of the
 * numbers of the die, however 5's do not count, nor do any of the
 * numbers after that.
 *
 * This version of the program outputs the expected sum after
 * a set amount of games.
 *
 * @author Jacob Swineford
 */
public class FatalFives {
    public static void main(String[] args) {

        int totalSum = 0;

        final int GAMES_TO_PLAY = 1_000_000;

        for (int i = 0; i < GAMES_TO_PLAY; i++) {
            totalSum += fatalCalculation();
        }

        DecimalFormat double0 = new DecimalFormat(".00");
        double result = (double) totalSum / GAMES_TO_PLAY;
        System.out.println("The expected payout for fatal fives: " + "$" +
                double0.format(result));
    }

    /**
     * Takes three dice rolls from the main method and adds them up,
     * then calculates profit based on the game of Fatal Fives.
     * x is the first number, y is the second, and z is the third.
     *
     * @returns One game of fatal fives
     */
    private static int fatalCalculation() {

        Random rand = new Random();

        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int die3 = rand.nextInt(6) + 1;

        if (die1 == 5) {
            return (0);

        } else if (die2 == 5) {
            return (die1);

        } else if (die3 == 5) {
            return (die1 + die2);

        }else{
            return (die1 + die2 + die3);
        }
    }
}
