package PracticeProblems.Completed.Java1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Prompts the user for two integers, k and n, and
 * outputs a kth amount of integers in the range [0, n-1].
 *
 * DISCLAIMER: DOES NOT WORK PROPERLY IF K IS 2 OR MORE THAN N.
 * THERE WILL NOT BE ENOUGH SLOTS IN THE ARRAY TO COPY OVER.
 *
 * @author Jacob Swineford
 */
public class DistinctRandomNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter two integers: ");
        int k = in.nextInt();
        int n = in.nextInt();

        System.out.println(Arrays.toString(getRandomArray(k, n)));

    }

    /**
     * gets a random array that randomizes the initial
     * values of that array (i = 1 at pos 1, i = 2 at pos 2, etc.)
     * and randomizes, formats and returns the random values in
     * ascending order.
     *
     */
    private static int[] getRandomArray(int k, int n) {
        int[] nArray = new int[n + 1]; // n = 15, values are then 0 - 15

        for (int i = 1; i <= n; i++) { // ascending numbers
            nArray[i] += i;
        }

         //shuffle the array
        for (int j = 0; j < n; j++) {
            Random rand = new Random();
            int r = rand.nextInt(n);
            int t = nArray[j]; // temporary character for when position k is overwritten.
            nArray[j] = nArray[r];
            nArray[r] = t;
        }

        int[] kArray = new int[k];
        System.arraycopy(nArray, 0, kArray, 0, k);
        Arrays.sort(kArray);
        return kArray;
    }
}
