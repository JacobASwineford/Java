package JavaDemos.Java1.ch6.ch6b;

import java.util.Scanner;

/**
 * Calculates the dum of positive integers up to a limit specified by
 * the user.
 *
 * @author Jacob Swineford
 */
public class SummerFor {

    public static void main(String[] args) {
        System.out.print("Enter a positive integer: ");
        int n = new Scanner(System.in).nextInt();

        int sum = 0;
        for (int numToAdd = 1; numToAdd <= n; numToAdd++) { // the integer numToAdd is one. as long as num to add is less than or equal to n,
            sum += numToAdd; // sum equals sum + numToAdd
        }
        System.out.println("1 + 2 + ... + " + n + " = " + sum);

    }
}
