package JavaDemos.Java1.ch6.ch6c;

import java.awt.Font;
import javax.swing.UIManager;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * A Monte Carlo simulation to estimate the probability of winning Craps.
 *
 * @author Jacob Swineford
 */
public class CrapsProbabilityCalculator {

    public static void main(String[] args) {

        final int NUM_GAMES = 1_000_000;
        int wins = 0;

        for (int i = 0; i < NUM_GAMES; i++) {
            if (playerWins()) {
                wins++;
            }
        }

        // calculate probability and construct output strings
        double winProb = (double) wins / NUM_GAMES * 100;
        DecimalFormat df = new DecimalFormat(".000"); // ### makes stuff like .3 stay .3 instead of .300
        //String sl = wins + " / " + NUM_GAMES + " wins.";
        String s1 = String.format("%,d/%,d", wins, NUM_GAMES);
        String s2 = "Winning percentage: " + df.format(winProb) + "%";
        String output = s1 + "\n" + s2;
        //JOptionPane.showMessageDialog(null, output);

        // customize the message dialog
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        UIManager.put("OptionPane.messageFont", font);
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon("dice2.jpg");
        JOptionPane.showMessageDialog(null, output, "Craps",
                JOptionPane.INFORMATION_MESSAGE, icon);

    }

    /**
     * Simulate the game of Craps.
     *
     * @returns true if the player wins, false otherwise.
     */
    private static boolean playerWins() {
        int firstSum = rollDice();
        if (firstSum == 7 || firstSum == 11) {
            return true;
        }
        if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
            return false;
        }

        int newSum = rollDice();
        while (newSum != firstSum && newSum != 7) {
            newSum = rollDice();
        }

        return newSum == firstSum;
    }

    /**
     * Returns the sum of two dice rolls.
     */
    private static int rollDice() {
        Random rand = new Random();
        int d1 = 1 + rand.nextInt(6);
        int d2 = 1 + rand.nextInt(6);
        return d1 + d2;
    }
}
