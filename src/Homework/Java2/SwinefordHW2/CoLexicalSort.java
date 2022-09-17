package Homework.Java2.SwinefordHW2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Asks the user to enter a line of text, then sorts the Strings
 * split by " " in coLexical order.
 *
 * @author Jacob Swineford
 */
public class CoLexicalSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String[] arr = in.nextLine().split(" ");

        class CoLexicalComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                o1 = new StringBuilder(o1).reverse().toString();
                o2 = new StringBuilder(o2).reverse().toString();
                return o1.compareTo(o2);
            }
        }

        Arrays.sort(arr, new CoLexicalComparator());
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
