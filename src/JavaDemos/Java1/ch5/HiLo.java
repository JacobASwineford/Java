package JavaDemos.Java1.ch5;

import java.util.Random;
import java.util.Scanner;

/**
 * Prompts the user to guess a random number between 1 and 10.
 *
 * @author Jacob Swineford
 */
public class HiLo {
    public static void main(String[] args) {
        Random rand = new Random();
        int secretnumber = 1 + rand.nextInt(10);

        System.out.print("Guess my secret number (1-10): ");
        Scanner in = new Scanner(System.in);
        int guess = in.nextInt();

        System.out.println();
        System.out.println("Your guessed " + guess + ".");
        System.out.println("My secret number was " + secretnumber + ".");

        if (guess < secretnumber) {
            System.out.println("Your guess was to low.");
        } else if (guess > secretnumber) {
            System.out.println("Your guess was too high.");
        } else {
            System.out.println("Your guess was correct.");
        }
    }

}
