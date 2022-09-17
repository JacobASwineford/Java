package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * takes the red and blue base numbers and uses those
 * to pat out two 12-sided dice. Then, calculates the
 * values that commonly show up on both dice.
 *
 * This problem was intended to be a practice problem for
 * nested loops, but can be done without them.
 *
 * @author Jacob Swineford
 */
public class Dodecahedra {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Red base number: ");
        int redBase = in.nextInt();
        System.out.print("Blue base number: ");
        int blueBase = in.nextInt();
        int counter = 0;

        System.out.print("Numbers that are on both dice: ");

        // runs only for twelve sided dice
        for (int i = 1; i <= 12; i++) {
            int red = i * redBase;
            if (red % blueBase == 0) {
                System.out.print(red + " ");
                counter++;
            }
        }

        if (counter == 0) {
            System.out.print(" N/A ");
        }
    }
}
