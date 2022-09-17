package JavaDemos.Java1.ch6.ch6a;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * A Monte Carlo simulation of a random walk from the center of a circle to
 * its boundary.
 *
 * The user specifies the radius and the program outputs the expected length
 * of a random walk.
 *
 * @author Jacob Swineford
 */
public class RandomWalk {

    public static void main(String[] args) {
        String prompt = "Enter radius of the circle.";
        String input = JOptionPane.showInputDialog(prompt);
        int radius = Integer.parseInt(input);

        final int NUM_WALKS = 10_000;
        int totalLength = 0;
        int count = 0;

        // Each iteration of the loop performs one random walk.
        while (count < NUM_WALKS) { // start at 0, <, start at 1, <=
            totalLength += doWalk(radius);
            count++;
        }

        // Calculate the expected length of a random walk by averaging
        // over all walks.
        int avgLength = totalLength / NUM_WALKS;
        String output = "expected length of a random walk from\n"
                + "the center to the boundary: " + avgLength + " Steps.";
        JOptionPane.showMessageDialog(null, output);

    }

    /**
     * Simulates a random walk from the center of a circle to its boundary.
     *
     * @returns the length of the walk
     */
    private static int doWalk(int radius) {
        int steps = 0;
        int x = 0; // x-coordinate of current location
        int y = 0; // y-coordinate of current location
        Random rand = new Random();

        while (Math.sqrt(x*x + y*y) < radius) { // while the z from the pythagorean theorem is less than the radius
            steps++;
            int k = rand.nextInt(4);
            if (k == 0) {
                x++;
            }
            if (k == 1) {
                x--;
            }
            if (k == 2) {
                y++;
            }
            if (k == 3) {
                y--;
            }

        }

        return steps;
    }

    /**
     * Returns distance of a given point from the origin.
     */

}
