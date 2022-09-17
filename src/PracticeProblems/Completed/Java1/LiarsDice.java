package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Random dice generator for the game of Perudo.
 *
 * CALZA can be called by anyone except the turn player
 * to see if the number called is exact. if it is, then
 * the player who called CALZA gains back a dice. if not,
 * then that player loses a dice.
 *
 * @author Jacob Swineford
 */
public class LiarsDice {

    public static void main(String[] args) {
        Random rand = new Random();
        int numDice = 5;
        for (int i = 0; i < numDice; i++) {
            int roll = rand.nextInt(6) + 1;
            System.out.print(roll + " ");
        }
    }
}
