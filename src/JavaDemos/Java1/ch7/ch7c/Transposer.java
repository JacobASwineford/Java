package JavaDemos.Java1.ch7.ch7c;

import java.util.Scanner;

/**
 * Accepts a line of text as input and then displays each "word" vertically. A
 * "word" is a maximal sequence of non-whitespace characters.
 *
 * @author Jacob Swineford
 */
public class Transposer {

    public static void main(String[] args) {

        System.out.print("Enter a line of text: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        String[] words = input.split(" "); // does not include the separator
        int numberOfWords = words.length;

        // find length of the longest word
        int max = 0;
        for (String w : words) {
            if (w.length() > max) {
                max = w.length();
            }
        }

        // fill 2D array of characters with the input
        char[][] c = new char[numberOfWords][max];
        for (int i = 0; i < numberOfWords; i++) {
            for (int j = 0; j < max; j++) {
                if (words[i].length() > j) {
                    c[i][j] = words[i].charAt(j);
                } else {
                    c[i][j] = '*';
                }
            }
        }

        // print the characters column by column
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < numberOfWords; j++) {
                System.out.print(c[j][i] + " ");
            }
            System.out.println();
        }

        System.out.println();
        printArray(c);

    }

    /**
     * Prints the contents of a given 2D array of strings.
     */
    private static void printArray(char[][] acts) {
        for (char[] row : acts) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}



