package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class BabylonianMethod {

    public static void main(String[] args) {

        // Initialize Scanner and prompt user for input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a positive number: "); //n
        double n = in.nextDouble();
        System.out.print("Guess the Square root: "); //k
        double k = in.nextDouble();

        // Calculate Babylonian square root average
        double accurateApprox = ((k + (n/k)) / 2);

        // Output result
        System.out.println("Better guess: " + accurateApprox);

    }

}
