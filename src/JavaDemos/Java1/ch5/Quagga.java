package JavaDemos.Java1.ch5;

import java.util.Random;

/**
 * Simulates the game of Quagga.
 *
 * The game of Quagga is played with three dice. Certain combinations form
 * what is called a "quagga", and if you roll a quagga you win the game and
 * get to wear a special hat for the rest of the day.
 *
 * a quagga arises when it is possible to concatenate two of the dice to
 * obtain a number that is a multiple of the third. Example: 3, 6, and 4
 * is a quagga since 36 is a multiple of 4.
 *
 * one small catch: ones do not count. Any roll that contains a one loses.
 *
 * @author Jacob Swineford
 */
public class Quagga {

    public static void main(String[] args) {
        // Roll dice
        Random rand = new Random();
        int die1 = 1 + rand.nextInt(6);
        int die2 = 1 + rand.nextInt(6);
        int die3 = 1 + rand.nextInt(6);
        System.out.println(die1 + " " + die2 + " " + die3);

        // output a win/lose message
        if (isquagga(die1, die2, die3)) {
            System.out.println("That's a Quagga!");
        } else {
            System.out.println("Sorry, no Quagga for you!");
        }
    }

    /**
     * Returns true if three given dice values form a quagga.
     *
     */
    private static boolean isquagga(int x, int y, int z) {
        // Any roll that contains a one is a losing roll.
        if (x == 1 || y == 1 || z == 1) { // "||" expresses logical "or" == expresses "equals"
            return false;
        }
        if ((10 * x + y) % z == 0) { // can also use parse-int
            return true;
        }
        if ((10 * x + z) % y == 0) { // can also use parse-int
            return true;
        }
        if ((10 * z + x) % y == 0) { // can also use parse-int
            return true;
        }
        if ((10 * z + y) % x == 0) { // can also use parse-int
            return true;
        }
        if ((10 * y + x) % z == 0) { // can also use parse-int
            return true;
        }
        if ((10 * y + z) % x == 0) { // can also use parse-int
            return true;
        }
        return false;
    }
}
