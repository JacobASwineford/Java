package PracticeProblems.Completed.Java1;

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
 * In the deluxe version, the multiples that have been calculated from the
 * three dice rolls are shown.
 *
 * In various cases, there are multiple sets of multiples that you can obtain
 * from the dice rolls, depending on the order of input.
 * Ex. 2 4 3
 * 24 is a multiple of 3.
 * 42 is a multiple of 3.
 * 34 is a multiple of 2.
 * 32 is a multiple of 4.
 *
 * The code does not display this. instead, the instance it draws from is the
 * one from the input die1, die2, die3 into the helper method.
 *
 * @author Jacob Swineford
 */
public class QuaggaDeluxe {

    public static void main(String[] args) {
        // Roll dice
        Random rand = new Random();
        int die1 = 1 + rand.nextInt(6);
        int die2 = 1 + rand.nextInt(6);
        int die3 = 1 + rand.nextInt(6);
        System.out.println(die1 + " " + die2 + " " + die3);

        // output a win/lose message
        System.out.println(isquagga(die1, die2, die3));
        System.out.println(isquagga(die2, die3, die1));
        System.out.println(isquagga(die3, die2, die1));
        System.out.println(isquagga(die2, die1, die3));
        System.out.println(isquagga(die1, die3, die2));
        System.out.println(isquagga(die3, die1, die2));
    }

    /**
     * Returns true if three given dice values form a quagga.
     *
     */
    private static String isquagga(int x, int y, int z) {
        // Any roll that contains a one is a losing roll.
        if (x == 1 || y == 1 || z == 1) { // "||" expresses logical "or" == expresses "equals"
            return ("No quaggas for you today.");
        }
        if ((10 * x + y) % z == 0) {
            return ((10 * x + y) + " is a multiple of " + z + ".");
        }
        if ((10 * x + z) % y == 0) {
            return ((10 * x + z) + " is a multiple of " + y + ".");
        }
        if ((10 * z + x) % y == 0) {
            return ((10 * z + x) + " is a multiple of " + y + ".");
        }
        if ((10 * z + y) % x == 0) {
            return ((10 * z + y) + " is a multiple of " + x + ".");
        }
        if ((10 * y + x) % z == 0) {
            return ((10 * y + x) + " is a multiple of " + z + ".");
        }
        if ((10 * y + z) % x == 0) {
            return ((10 * y + z) + " is a multiple of " + x + ".");
        }
        return ("Those numbers are bad! No quagga!");
    }
}
