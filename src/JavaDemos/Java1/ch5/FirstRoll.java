package JavaDemos.Java1.ch5;

import java.util.Random;

/**
 * Simulates the first roll of the game of Craps.
 *
 * The player rolls two dice/ If the dum is 7 or 11, the player wins;
 * if the sum is 2, 3, or 12, the player loses. In any other case, the
 * game continues following a different rule (not simulated here).
 *
 * @author Jacob Swineford
 */
public class FirstRoll {
    public static void main(String[] args) {

        // roll a pair of dice
        Random rand = new Random();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int sum = die1 + die2;

        System.out.println(die1 + " + " + die2 + " = " + sum);
        System.out.printf("%d + %d = %d %n", die1, die2, sum);

        String result = "The game continues.";
        if (sum == 7) {
            result = "YOU WIN.";
        }
        if (sum == 11) {
            result = "YOU WIN.";
        }
        if (sum == 2) {
            result = "YOU LOSE.";
        }
        if (sum == 3) {
            result = "YOU LOSE.";
        }
        if (sum == 12) {
            result = "YOU LOSE.";
        }
        System.out.println(result);


    }
}
