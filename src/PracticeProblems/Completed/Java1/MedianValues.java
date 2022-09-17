package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Prompts the user to enter the number of exams and
 * exam scores, then with those scores the median
 * is calculated.
 *
 * If the number of exams entered is more than the number of
 * exams, then the program only detects the first three inputs
 * and ignores the rest.
 *
 * EX.
 * Enter the number of exams: 3
 * Enter exam scores: 23 24 25 66
 * The median score is: 24.
 *
 * @author Jacob Swineford
 */
public class MedianValues {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of exams: ");
        int exams = in.nextInt();
        int[] exScores = new int[exams];
        System.out.print("Enter exam scores: ");

        // taking the input and adding it to the int[]
        for (int i = 0; i < exams; i++) {
            int x = in.nextInt();
            exScores[i] += x;
        }

        String str = "The median score is: %d.%n";
        System.out.printf(str, medianScore(exScores, exams));
    }
    /**
     * takes the scores received from the numExams array
     * and averages them out.
     */
    private static int medianScore(int[] exScores, int exams) {
        int total = 0;
        for (int i = 0; i < exams; i++) {
            total += exScores[i];
        }
        return total / exams;
    }
}
