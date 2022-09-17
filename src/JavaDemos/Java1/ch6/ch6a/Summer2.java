package JavaDemos.Java1.ch6.ch6a;

import java.util.Scanner;

/**
 * Calculates the dum of positive integers up to a limit specified by
 * the user.
 *
 * @author Jacob Swineford
 */
public class Summer2 {

    public static void main(String[] args) {
        System.out.print("Enter a positive integer: ");
        int n = new Scanner(System.in).nextInt();

        int sum = 0;
        int numToAdd = 1;

        while (numToAdd <= n) { // while initiates a loop
            sum = sum + numToAdd * numToAdd;
            numToAdd = numToAdd + 1;
        }

        System.out.println("1^2 + 2^2 + ... + " + n + "^2 = " + sum);

    }
}
