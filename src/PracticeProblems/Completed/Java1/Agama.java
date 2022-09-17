package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Plays the game of Agama, a game where a player
 * repeatedly rolls a pair of dice and wins a dollar amount
 * equal to the sum. however, when the sum of the two rolls is
 * 7, the game ends and the 7 is not counted towards the sum.
 *
 * @author Jacob Swineford
 */

public class Agama {

    public static void main(String[] args) {

        // takes the initial information and formats the first line
        String initialInformation = agamaInformation();
        int die1 = Integer.parseInt(initialInformation.substring(0,1));
        int die2 = Integer.parseInt(initialInformation.substring(2,3));
        int sum = Integer.parseInt(initialInformation.substring(4));
        System.out.println(die1 + " + " + die2 + " = " + sum);

        int totalMoney = sum - 7;

        // the game continues as long as you don't roll a 7. which ends the game
        while (sum != 7) {
            String informationLooped = agamaInformation();
            int die1Looped = Integer.parseInt(informationLooped.substring(0, 1));
            int die2Looped = Integer.parseInt(informationLooped.substring(2, 3));
            int sumLooped = Integer.parseInt(informationLooped.substring(4));
            sum = sumLooped;
            totalMoney += sumLooped;
            System.out.println(die1Looped + " + " + die2Looped + " = " + sumLooped);
        }
        System.out.println("\nThe Player wins $" + totalMoney + ".");
    }

    /**
     * Plays the game of Agama and outputs the information needed
     * to be displayed.
     */
    private static String agamaInformation() {
        Random rand = new Random();
        int x = rand.nextInt(6) + 1;
        int y = rand.nextInt(6) + 1;
        return x + " " + y + " " + (x + y);
    }
}
