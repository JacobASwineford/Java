package Misc;

import java.util.LinkedList;

public class Lesson {

    public static void main(String[] args) {
        System.out.println("The average is: " + calculateAverage(1, 2, 3, 4));

        String sentence = "Understanding the importance of programming languages";
        String delims = " ";
        LinkedList<String> tokens = tokenize(sentence, delims);
        for (String token : tokens)
            System.out.println(token);
    }

    /**
     * Calculates the average of a given list of numbers.
     *
     * @param numbs list of numbers
     *
     * @return average of numbers
     */
    private static double calculateAverage(double ...numbs) {
        double sum = 0;
        for (double num : numbs)
            sum += num;
        return sum / numbs.length;
    }

    /**
     * Splits a String based on a set of delimiters.
     *
     * @param sentence String to split
     * @param delims list of delimiter characters
     *
     * @return List of tokens
     */
    private static LinkedList<String> tokenize(String sentence, String delims) {
        LinkedList<String> tokens = new LinkedList<>();
        StringBuilder cur = new StringBuilder();

        for (char c : sentence.toCharArray()) {
            if (has(delims, c)) {
                if (cur.length() != 0) {
                    tokens.add(cur.toString());
                    cur.delete(0, cur.length());
                }
            } else {
                cur.append(c);
            }
        }

        if (cur.length() != 0)
            tokens.add(cur.toString());

        return tokens;
    }

    /**
     * Returns true if a particular character is in a string. false otherwise.
     *
     * @param str String to peek
     * @param c character to find
     *
     * @return true if found, false otherwise.
     */
    private static boolean has(String str, char c) {
        for (char cha : str.toCharArray()) {
            if (c == cha)
                return true;
        }
        return false;
    }
}
