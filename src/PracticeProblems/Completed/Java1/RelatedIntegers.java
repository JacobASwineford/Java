package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Prompts the user to enter three integers, and checks if
 * they are related or not. Integers are related if the two other
 * integers add up to be the third one.
 *
 * @author Jacob Swineford
 */
public class RelatedIntegers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter three integers: ");

        int num1 = in.nextInt();
        int num2 = in.nextInt();
        int num3 = in.nextInt();

        System.out.println(relatedInts(num1, num2, num3));
    }
    /**
     * Checks if the input of three numbers are related or not,
     * and formats them from lowest to highest value.
     *
     * @param x first integer
     * @param y second integer
     * @param z third integer
     * @returns string
     */
    private static String relatedInts(int x, int y, int z) {

        if (x + y == z) {
            int smallestBoi = Math.min(x, y);
            int middleBoi = Math.max(x, y);
            return (smallestBoi + " + " + middleBoi + " = " + z);

        } else if (x + z == y) {
            int smallestBoi2 = Math.min(x, z);
            int middleBoi2 = Math.max(x, z);
            return (smallestBoi2 + " + " + middleBoi2 + " = " + y);

        } else if (z + y == x) {
            int smallestBoi3 = Math.min(y, z);
            int middleBoi3 = Math.max(y, z);
            return (smallestBoi3 + " + " + middleBoi3 + " = " + x);
        }
            return ("NOT RELATED");
    }
}
