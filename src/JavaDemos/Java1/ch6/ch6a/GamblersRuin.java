package JavaDemos.Java1.ch6.ch6a;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Simulates the Gamble's ruin.
 *
 * A gambler wins or loses a dollar depending on the outcome of a coin flip.
 * He will continue playing as long as he has at least a dollar to wager.
 * It can be shown the gambler will go broke eventually, regardless of the
 * starting balance.
 *
 * @author Jacob Swineford
 */
public class GamblersRuin {

    public static void main(String[] args) {
        String prompt = "The Gambler's ruin. \nEnter initial balance.";
        int initBalance = Integer.parseInt(JOptionPane.showInputDialog(prompt));

        int balance = initBalance;
        int coinFlips = 0;

        // simulate the game -- each iteration loop represent ne coin flip
        Random rand = new Random();
        int maxBalance = initBalance;
        while (balance > 0) {
            coinFlips++;
            if (rand.nextBoolean()) {
                balance++; // adds 1
            } else {
                balance--; // subtracts 1
            }
            if (balance > maxBalance) {
                maxBalance = balance;
            }
        }

        String s1 = "Initial balance: %,d %n";
        String s2 = "Maximum balance: %,d %n";
        String s3 = "Length of the game: %,d coin flips %n";
        String output = String.format(s1 + s2 + s3, initBalance, maxBalance, coinFlips);
        JOptionPane.showMessageDialog(null, output);


    }
}
