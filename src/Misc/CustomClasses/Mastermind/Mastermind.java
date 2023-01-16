package Misc.CustomClasses.Mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Mastermind {

    private int guesses;
    private final char[] code;
    private final ArrayList<char[]> previous; // previous guesses

    // variables related to coloring / checking a guess
    private final int[] coloring;
    private final int[] coloringNum;
    private static final int GREEN = 0;
    private static final int YELLOW = 1;
    private static final int RED = 2;

    /**
     * Constructor for the game of Mastermind. Initializes the code to guess
     * with the given length, using any particular char from the given keys array.
     * The number of guesses this instance of Mastermind will allow is also stored.
     *
     * @param keys arrays of char used to make the code
     * @param length length of the code
     */
    public Mastermind(char[] keys, int length) {
        guesses = 0;
        code = new char[length];
        coloring = new int[length];
        coloringNum = new int[3];
        previous = new ArrayList<>();

        // randomly generate and store the code
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int lo = 0;
        while (length-- != 0) {
            int r = rand.nextInt(keys.length);
            code[lo++] = keys[r];
        }
    }

    /**
     * Guesses the code using the given array as a reference.
     * This method assumes that the array given matches the length of the code.
     * <p>
     * Previous guesses are allocated as hard copies of the given array, so this method
     * can take the same reference to an array of guesses without fault.
     *
     * @param guess array used to guess the code
     * @return true if the guess is correct, false otherwise.
     */
    public boolean predict(char[] guess) {
        color(guess);
        char[] copy = new char[guess.length];
        System.arraycopy(guess, 0, copy, 0, copy.length);
        previous.add(copy);
        guesses++;
        return coloringNum[GREEN] == code.length;
    }

    /**
     * Gets the list of char[] containing the previous guesses, in order.
     *
     * @return List of previous guesses
     */
    public ArrayList<char[]> getPrevious() {return previous;}

    /**
     * Gets the current number of guesses associated with this object.
     *
     * @return current number of guesses
     */
    public int getGuesses() {return guesses;}

    /**
     * When guessing incorrectly, a code is formed showing how close the
     * guess is to the generated code. There are three levels of validity:
     * <p>
     * If the coloring is ____ at position X:
     * Green - The guess at X matches the code at X
     * Yellow - The guess at X is present in the code somewhere, but the code at X is not already Green
     * Red - The guess at X is not present in the code
     *
     * @param guess array used to guess the code
     */
    private void color(char[] guess) {
        // reset values of coloring for each iteration of guessing
        Arrays.fill(coloring, RED);
        Arrays.fill(coloringNum, 0);

        // check for green
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == code[i])
                coloring[i] = GREEN;
        }

        // check for yellow
        for (int i = 0; i < guess.length; i++) {
            if (coloring[i] == GREEN) continue;

            for (int j = 0; j < guess.length; j++) {
                if (guess[i] == code[j] && coloring[j] != GREEN) {
                    coloring[i] = YELLOW;
                    break;
                }
            }
        }

        // list the number of greens, yellows, and reds
        for (int i = 0; i < guess.length; i++)
            coloringNum[coloring[i]]++;
    }

    /**
     * Main method to play simple game of Mastermind.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        String keys = "abc";
        Mastermind m = new Mastermind(keys.toCharArray(), 4);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            char[] guess = getGuess(in, m, keys);
            if (m.predict(guess)) break;
            printColoring(m);
            System.out.println();
        }
        System.out.println("\nWell Done! You Guessed the Code! -> ");
        printCode(m);
    }

    /**
     * Prints the coloring array of the given Mastermind object.
     *
     * @param m Mastermind object
     */
    private static void printColoring(Mastermind m) {
        System.out.println("Coloring: " + Arrays.toString(m.coloring));
    }

    /**
     * Prints the Code array of the given Mastermind object.
     *
     * @param m Mastermind object
     */
    private static void printCode(Mastermind m) {
        System.out.println("Code: " + Arrays.toString(m.code));
    }

    /**
     * Prompts the user for a guess, then uses teh given Mastermind object to test
     * that guess against the internal randomized code.
     *
     * @param scan given Scanner
     * @param m given Mastermind Object
     * @param keys set of keys used for the game
     *
     * @return extracted guess
     */
    private static char[] getGuess(Scanner scan, Mastermind m, String keys) {
        System.out.print("Guess #" + (m.getGuesses() + 1) + " " + Arrays.toString(keys.toCharArray()) + ": ");
        String[] g = scan.nextLine().split(" ");
        char[] guess = new char[g.length];
        int i = 0;
        for  (String str : g)
            guess[i++] = str.charAt(0);
        return guess;
    }
}
