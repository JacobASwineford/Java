package JavaDemos.Java2.ch9.ch9d;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Compares a list of random integers and outputs the contents in sorted order.
 * sorted numerically and sorted by magnitude.
 *
 *
 * @author Jacob Swineford
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        final int minvalue = -99;
        final int maxvalue = 99;

        final int size = 10;
        Integer[] a = new Integer[size];

        // 3 - 5 inclusive == 2 + 1
        System.out.print("Unsorted: ");
        for (int i = 0; i < size; i++) {
            a[i] = rand.nextInt(maxvalue - minvalue + 1) + minvalue;
            System.out.print(a[i] + " ");
        }
        System.out.println();

        System.out.print("Sorted numerically: ");
        Arrays.sort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();

        // for comparing integers by magnitude
        class MyComparator implements Comparator<Integer> { // nested class, callback
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(o1), Math.abs(o2));
            }
        }

        System.out.print("Sorted by magnitude: ");
        Arrays.sort(a, new MyComparator());
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

