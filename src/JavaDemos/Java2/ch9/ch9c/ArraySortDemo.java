package JavaDemos.Java2.ch9.ch9c;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Demonstrates the ues of the comparable interface to StatisticalSort arrays of objects.
 *
 * @author Jacob Swineford
 */
public class ArraySortDemo {

    public static void main(String[] args) {
        // Fill an array of doubles with 5 random numbers and StatisticalSort it
        double[] numbers = new double[5];
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i  <numbers.length; i++) {
            numbers[i] = rand.nextDouble();
        }

        Arrays.sort(numbers);
        System.out.println("Sorted doubles: ");
        for (double num : numbers) {
            System.out.printf("%1.3f ", num);
        }
        System.out.println();

        // Sort an array of strings. This is possible because String implements
        // Comparable.
        String[] animals = {"bam", "boom", "woom", "zoom", "jercob"};
        Arrays.sort(animals);
        System.out.println("Sorted animals: ");
        for (String s : animals) {
            System.out.print(s + " ");
        }
        System.out.println();

        // BigInteger also implements Comparable.
        final int numDigits = 10;
        BigInteger[] bigInts = new BigInteger[5];
        for (int i = 0; i < bigInts.length; i++) {
            // make a random string of integers abd use it to construct a Biginteger
            String digits = "";
            for (int j = 0; j < numDigits; j++) {
                digits += rand.nextInt(10);
            }
            bigInts[i] = new BigInteger(digits);
        }

        Arrays.sort(bigInts);
        System.out.println("Sorted BigIntegers: ");
        for (BigInteger b : bigInts) {
            System.out.printf("%,d%n", b);
        }
        System.out.println();

        int[][] minSec = {
                {5, 45}, {6, 1}, {7, 59}, {3, 43}, {5, 9}
        };
        MilePace[] milePaces = new MilePace[minSec.length];
        for (int i = 0; i < milePaces.length; i++) {
            milePaces[i] = new MilePace(minSec[i][0], minSec[i][1]);
        }

        Arrays.sort(milePaces); // the StatisticalSort method in the arrays class takes any object that implements the comparable interface, and has a compareTo method
        System.out.println("Mile paces: ");
        for (MilePace mp : milePaces) {
            System.out.print(mp + " ");
        }
        System.out.println();
    }
}

/**
 * represents a runner's pace for a mile in minutes and seconds.
 */
class MilePace implements Comparable<MilePace> {
    private final int min;
    private final int sec;

    public MilePace(int min, int sec) {
        this.min = min;
        this.sec = sec;
    }

    /**
     * Returns a negative integer, zero, or a positive integer depending on whether
     * this mile pace is faster than, equal to, or slower than the other.
     */
    @Override
    public int compareTo(MilePace other) {
        if (min == other.min) {
            return sec - other.sec;
        }
        return min - other.min;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", min, sec);
    }
}

