package c4;

import java.util.Random;

/**
 *  Simulates two players rolling three dice each. Results of each roll are
 *  displayed in ascending order
 *
 *  This version uses a helper method to simplify the main method.
 *
 * @author Jacob Swineford
 */
public class ThreeDiceRoller2 {

    public static void main(String[] args) {
        System.out.println("Player 1 rolls " + rolldice() + ".");
        System.out.println("Player 2 rolls " + rolldice() + ".");
        System.out.println("Player 3 rolls " + rolldice() + ".");
        System.out.println(4%15); //4
        System.out.println(4/15); //0
        System.out.println(4/3); //1
        System.out.println(4%3); //1
        System.out.println(7/60.0);

    }

    /**
     * Simulates a roll of three dice.
     *
     * @return a string with the rolled values in ascending order.
     */
    private static String rolldice() {  // private vs public - private means this class is only for main class
        Random rand = new Random();
        int die1 = 1 + rand.nextInt(6);
        int die2 = 1 + rand.nextInt(6);
        int die3 = 1 + rand.nextInt(6);

        // calculate smallest, middle, and largest values
        int smallest = Math.min(Math.min(die1, die2), die3); //take minimum of die 1 and 2, then do that with the result and die 3
        int largest = Math.max(Math.max(die1, die2), die3); //same process as above, only maximum is calculated
        int sum = die1 + die2 + die3;
        int middle = sum - smallest - largest;

        return smallest + "-" + middle + "-" + largest;
    }


}
