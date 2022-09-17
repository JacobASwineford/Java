package PracticeProblems.Completed.Java2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Plays the game of hangman.
 *
 * @author Jacob Swineford
 */
public class HangMan {

    private final String phrase;
    private String shadow;
    private ArrayList<Character> correctGuesses;
    private ArrayList<Character> incorrectGuesses;

    /**
     * Public Constructor enabling a user to enter a String
     * for the game of hangMan.
     * Initializes
     */
    public HangMan(String phrase) {
        this.phrase = phrase;
        shadow = "";
        correctGuesses = new ArrayList<>();
        incorrectGuesses = new ArrayList<>();
        for (char c : phrase.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                shadow += "#";
            } else {
                shadow += " ";
            }
        }
    }

    /**
     * displays the question marks and letters for the entered
     * word, and is to be used throughout execution.
     */
    public String getPhraseShadow() {
        return shadow;
    }

    /**
     * Enables the user to guess a character in shadow.
     * The character is then cached into the appropriate
     * char[].
     *
     * If the guess is correct, then shadow is concatenated
     * to display the correct characters in their given positions.
     * Else, shadow is not manipulated.
     *
     * This method discriminates upper and lower case characters.
     */
    public void guess(char c) {
        char[] pArr = phrase.toCharArray();
        char[] sArr = shadow.toCharArray();
        int cor = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (pArr[i] == c) {
                sArr[i] = c;
                cor++;
            }
        }

        if (cor > 0) {
            correctGuesses.add(c);
        } else if (cor == 0) {
            incorrectGuesses.add(c);
        }

        // re-concatenate shadow according to the changed characters
        shadow = "";
        for (char cha : sArr) {
            shadow += cha;
        }
    }

    /**
     * Checks if the phrase is fully represented by shadow.
     */
    public boolean gameOver() {
        if (shadow.equals(phrase)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of correct guesses.
     */
    public int getCorrectGuesses() {
        return correctGuesses.size();
    }

    /**
     * Returns the number of correct guesses.
     */
    public int getIncorrectGuesses() {
        return incorrectGuesses.size();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a String for hangman: ");
        String s = in.nextLine();
        HangMan h = new HangMan(s);
        while (!h.gameOver()) {
            System.out.println(h.getPhraseShadow() + "\n");
            System.out.print("Enter your Guess: ");
            String g = in.next();
            h.guess(g.charAt(0));
        }

        System.out.println(h.getPhraseShadow());
        System.out.println("\nCorrect guesses [" + h.getCorrectGuesses() +
                "]\nIncorrect Guesses [" +h.getIncorrectGuesses() + "]");
    }
}
