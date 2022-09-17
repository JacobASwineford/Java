package JavaDemos.Java1.ch6.ch6c;

import java.util.Random;

/**
 * Plays the game of Craps.
 *
 * The player rolls a pair of dice. I the sum is 7 or 11 the player
 * wins, and if it is 2, 3, or 12 then the player loses. In any other
 * case, the player continues rolling until matching the first sum (wins)
 * or rolling a 7 (loses).
 *
 * @author Jacob Swineford
 */
public class Craps {

    public static void main(String[] args) {

        Random rand = new Random();

        //first roll
        int die1 = 1 + rand.nextInt(6);
        int die2 = 1 + rand.nextInt(6);
        int firstSum = die1 + die2;
        System.out.printf("%d + %d = %d %n", die1, die2, firstSum);

        // check if the player wins or loses on the first roll
        if (firstSum == 7 || firstSum == 11) {
            System.out.println("You WIN!");
            return;
        }
        if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
            System.out.println("You LOSE!");
            return;
        }

        // continue rolling until matching the first sum or rolling 7
        int newSum = 0;
        while (firstSum != newSum && newSum != 7) {
            die1 = 1 + rand.nextInt(6);
            die2 = 1 + rand.nextInt(6);
            newSum = die1 + die2;
            System.out.printf("%d + %d = %d %n", die1, die2, newSum);
        }

        if (newSum == firstSum) {
            System.out.println("You WIN!");
        } else {
            System.out.println("You LOSE!");
        }

    }
}
