package PracticeProblems.Completed.Java1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Takes 3 values and outputs the minimum, maximum, and average
 *
 * @author Jacob Swineford
 */
public class ExamStatistics {

    public static void main(String[] args) {

        // Initialize Input, DecimalFormat for average, user instructions
        Scanner in = new Scanner(System.in);
        DecimalFormat dou0 = new DecimalFormat(".00");
        System.out.print("Enter three exam scores (0-100): ");

        // Calculate the average, minimum, maximum
        int firstScore = in.nextInt();
        int secondScore = in.nextInt();
        int thirdScore = in.nextInt();
        int minimum = Math.min(Math.min(firstScore, secondScore), thirdScore);
        int maximum = Math.max(Math.max(firstScore, secondScore), thirdScore);
        double average = (firstScore + secondScore + thirdScore) / 3.0;

        // output results
        System.out.println("Minimum: " + minimum);
        System.out.println("Average: " + dou0.format(average));
        System.out.println("Maximum: " + maximum);

    }

}
