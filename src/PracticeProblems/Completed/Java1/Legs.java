package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Calculates the number of combinations of human,
 * spider and cockroach legs can be used to make the
 * given amount of legs by the user.
 *
 * if the given number of legs is negative, then the
 * program returns that the number of legs must be odd.
 *
 * @author Jacob Swineford
 */
public class Legs {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Number of legs: ");
        int legs = in.nextInt();

        if (legs % 2 != 0) {
            System.out.println("There needs to be an even number of legs.");
        } else if (legs < 2){
            System.out.println("\nThere needs to be more legs.");
        }

        System.out.printf("%d possible combinations of people," +
                " spiders and cockroaches.", legCombinations(legs));
    }

    /**
     * Calculates the maximum number of leg combinations given the
     * number of legs and the given creatures.
     *
     * People: 2 legs
     * Spiders: 8 legs
     * cockroaches: 6 legs
     */
    private static int legCombinations(int maxLegs) {
        final int P = 2;
        final int S = 8;
        final int C = 6;
        int combinations = 0;

        // ill be honest, im not sure why maxLegs works for a stopping point
        for (int i = 0; i <= maxLegs; i++) {
            for (int k = 0; k <= maxLegs; k++) {
                for (int j = 0; j <= maxLegs; j++) {
                    if ((P * i) + (k * S) + (j * C) == maxLegs) {
                        combinations++;
                    }
                }
            }
        }
        return combinations;
    }
}
