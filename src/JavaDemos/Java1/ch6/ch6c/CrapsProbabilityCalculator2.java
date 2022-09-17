package JavaDemos.Java1.ch6.ch6c;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * A Monte Carlo simulation to estimate the probability of winning Craps
 * and the expected length of a game.
 *
 * @author Jacob Swineford
 */
public class CrapsProbabilityCalculator2 {

    public static void main(String[] args) {

        final int NUM_GAMES = 1_000_000;
        int wins = 0;
        int numrolls = 0; // total number rolls in all games played

        for (int i = 0; i < NUM_GAMES; i++) {
            int k = playCraps();
            if (k > 0) {
                wins++;
            }
            numrolls += Math.abs(k);
        }

        // calculate probability and construct output strings
        double winProb = (double) wins / NUM_GAMES * 100;
        DecimalFormat df = new DecimalFormat(".000"); // ### makes stuff like .3 stay .3 instead of .300
        String s1 = String.format("%,d/%,d", wins, NUM_GAMES);
        String s2 = "Winning percentage: " + df.format(winProb) + "%";

        // calculate expected length of the game
        double expectedLength = (double) numrolls / NUM_GAMES;
        String s3 = "Expected length of game: " + df.format(expectedLength);

        String output = s1 + "\n" + s2 + "\n" + s3;
        System.out.println(output);
        //JOptionPane.showMessageDialog(null, output);

        /*// customize the message dialog
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        UIManager.put("OptionPane.messageFont", font);
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon("dice2.jpg");
        JOptionPane.showMessageDialog(null, output, "Craps",
                JOptionPane.INFORMATION_MESSAGE, icon);*/

    }

    /**
     * Simulate the game of Craps.
     *
     * @returns the number of rolls (positive if the player wins, negative
     * if player loses)
     */
    private static int playCraps() {
        int numRolls = 1;
        int firstSum = rollDice();
        if (firstSum == 7 || firstSum == 11) {
            return 1;
        }
        if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
            return -1;
        }

        int newSum = rollDice();
        numRolls++;
        while (newSum != firstSum && newSum != 7) {
            numRolls++;
            newSum = rollDice();
        }

        if (newSum == firstSum) {
            return numRolls;
        }
        return -numRolls;
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
