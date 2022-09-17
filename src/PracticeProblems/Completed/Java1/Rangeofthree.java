package PracticeProblems.Completed.Java1;

import java.util.Random;

/**
 * Takes 3 random scores on a 0 - 100 scale and outputs the average score.
 *
 * @author Jacob Swineford
 */

public class Rangeofthree {

    public static void main(String[] args) {

        // Initialize Random constructor
        Random rand = new Random();

        // Record random scores and range
        int score1 = rand.nextInt(100);
        int score2 = rand.nextInt(100);
        int score3 = rand.nextInt(100);
        int range = (score1 + score2 + score3) / 3;

        // Output answers
        System.out.println("Random Numbers: " + score1 + " " + score2 + " " + score3);
        System.out.println("Range: " + range);

    }
}
