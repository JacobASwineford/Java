package Homework.Java2.SwinefordHW2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Asks the user to enter a line of text, then sorts and
 * outputs the natural ordering and length ordering using the strings
 * split by " ".
 *
 * @author Jacob Swineford
 */
public class LengthSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String[] arr = in.nextLine().split(" ");

        Arrays.sort(arr);
        System.out.print("Natural ordering: ");
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();

        class LengthComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        }

        System.out.print("Length order: ");
        Arrays.sort(arr, new LengthComparator());
        for (String s : arr) {
            System.out.print(s + " ");
        }
    }
}
