package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Prompts the user to enter the number of exams and
 * exam scores, then with those scores the median
 * is calculated.
 *
 * This is version 2, which only requires the user to
 * enter the exam scores.
 *
 * @author Jacob Swineford
 */
public class MedianValuesStrings {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter exam scores: ");
        String[] exams = in.nextLine().split(" ");

        String str = "The median score is: %d.%n";
        System.out.printf(str, medianScore(exams));
    }
    /**
     * receives the string array containing the exams and
     * the length
     */
    private static int medianScore(String[] exams) {
        int total = 0;
        for (String y : exams) {
            int score = Integer.parseInt(y);
            total += score;
        }
        return total / exams.length;
    }
}
