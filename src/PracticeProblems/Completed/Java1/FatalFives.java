package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Takes three dice rolls and outputs the profit gained in
 * the game of Fatal Fives. the profit gained is the sum of the
 * numbers of the die, however 5's do not count, nor do any of the
 * numbers after that.
 *
 * @author Jacob Swineford
 */
public class FatalFives {
    public static void main(String[] args) {
        Random rand = new Random();

        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int die3 = rand.nextInt(6) + 1;

        System.out.println("Dice Rolls: " + die1 + " " + die2 + " " + die3);
        System.out.println(FatalCalculation(die1, die2, die3));
    }

    /**
     * Takes three dice rolls from the main method and adds them up,
     * then calculates profit based on the game of Fatal Fives.
     * x is the first number, y is the second, and z is the third.
     *
     */
    private static String FatalCalculation(int x, int y, int z) {
        int allAdded = x + y + z; // in order its supposed to be

        if (x == 5) {
            return ("Profit: = $0");

        } else if (y == 5) {
            return ("Profit: = $" + x);

        } else if (z == 5) {
            return ("Profit: = $" + (x + y));

        }else{
           return ("Profit: = $" + allAdded);
        }
    }
}
