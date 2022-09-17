package JavaDemos.Java2.ch9.ch9d;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Prompts the user for a sentence and outputs the words of the
 * sentence in ASCII order and in case-insensitive ASCII order.
 *
 * @author Jacob Swineford
 */
public class ComparatorDemo2 {

    public static void main(String[] args) {
        // Read a line of text and split into an array of words.
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String[] words  = in.nextLine().split(" ");
        System.out.println();

        // Remove punctuation from words.
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[,.?!]", "");
        }

        // StatisticalSort in ASCII order
        System.out.print("words in ASCII order: ");
        Arrays.sort(words);
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();

        /**
         * Compares strings ignoring case.
         */
        class MyStringComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        }

        System.out.print("words in case-insensitive ASCII order: ");
        Arrays.sort(words, new MyStringComparator());
        for (String word : words) {
            System.out.print(word + " ");
        }
    }
}

