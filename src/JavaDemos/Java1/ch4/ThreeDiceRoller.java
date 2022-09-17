package JavaDemos.Java1.ch4;

import java.util.Random;

/**
 *
 * @author Jacob Swineford
 */
public class ThreeDiceRoller {

    public static void main(String[] args) {
        Random rand = new Random();

        //roll dice for the first player
        int die1 = 1 + rand.nextInt(6);
        int die2 = 1 + rand.nextInt(6);
        int die3 = 1 + rand.nextInt(6);

        // caluclate the smallest and largest value
        int smallest = Math.min(Math.min(die1, die2), die3);
        int largest = Math.max(Math.max(die1, die2), die3);

        // calculate the middle value
        int sum = die1 + die2 + die3;
        int middle = sum - smallest - largest;

        //output result for the first player
        String roll = smallest + "-" + middle + "-" + largest;
        System.out.println("player 1 rolls " + roll + ".");

        //roll dice for second player
        die1 = 1 + rand.nextInt(6);
        die2 = 1 + rand.nextInt(6);
        die3 = 1 + rand.nextInt(6);

        // caluclate the smallest and largest value
        smallest = Math.min(Math.min(die1, die2), die3);
        largest = Math.max(Math.max(die1, die2), die3);

        // calculate the middle value
        sum = die1 + die2 + die3;
        middle = sum - smallest - largest;

        //output result for the first player
        roll = smallest + "-" + middle + "-" + largest;
        System.out.println("player 2 rolls " + roll + ".");
    }
}
