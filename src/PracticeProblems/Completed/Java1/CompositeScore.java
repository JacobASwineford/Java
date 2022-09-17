package PracticeProblems.Completed.Java1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class CompositeScore {

    public static void main(String[] args) {

        // Initialize Scanner, DecimalFormat, out user instructions
        Scanner in = new Scanner(System.in);
        DecimalFormat single0 = new DecimalFormat(".0");
        System.out.print("Enter three exam scores: ");

        // Calculate the average, minimum, maximum
        int firstScore = in.nextInt(); //28
        int secondScore = in.nextInt(); //29
        int thirdScore = in.nextInt(); //30
        int min = Math.min(Math.min(firstScore, secondScore), thirdScore);
        double compositeAverage = ((firstScore + secondScore + thirdScore) - min) / 2.0;

        // output result
        System.out.println("Composite score: " + single0.format(compositeAverage));
    }
}
