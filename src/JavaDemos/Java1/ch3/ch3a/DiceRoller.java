package JavaDemos.Java1.ch3.ch3a;

import java.util.Random;

/**
 * Simulates Rolling a pair of dice.
 *
 * @author Jacob Swineford
 */
public class DiceRoller {

    public static void main(String[] args) {

        // roll dice and calculate the sum
        Random rand = new Random();
        int die1 = rand.nextInt(6 + 1); //Random number between 0 and the bound. 0-5 (which is why +1)
        int die2 = rand.nextInt(6 + 1);
        int sum = die1 + die2;

        //output results
        System.out.println("First die: " + die1);
        System.out.println("Second die: " + die2);
        System.out.println("The sum is: " + sum);

    }
}
