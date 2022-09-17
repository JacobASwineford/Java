package Misc.CustomClasses.Other;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Mastermind is a game where the objective is to find the correct
 * combination of characters, usually given 4 or 5 options
 * to choose from.
 *
 * @author Jacob Swineford
 */
public class Mastermind {

    // Combination and Options
    private char[] opt;
    private char[] com;

    // blackList for positions while testing guesses
    private ArrayList<Integer> blPos = new ArrayList<>();

    public Mastermind(char[] com, char[] opt) throws Exception {
        if (optContainsCom(com, opt)) {
            this.com = com;
            this.opt = opt;
        } else {
            throw new DimensionMismatchException(
                    "Characters comprising combination must be contained in options");
        }
    }


    /**
     * Tests the given Adventurer array for equality to this object's combination.
     * a Adventurer array is returned detailing how close the guess is to this
     * object's combination. Every character is checked for correctness:
     *
     * 1. a question mark [?] is returned if this combination contains the given
     *    character in the incorrect position.
     * 2. an asterisk [*] is returned if this combination contains the given
     *    character in the correct position.
     * 3. an empty character [] is returned if the character is not in the combination.
     *
     * @param guess given Adventurer array
     * @return data about how close the guess is to the combination
     */
    public String testForCom(char[] guess) throws Exception {
        if (guess.length != com.length) {
            throw new DimensionMismatchException(
                    "guess and combination must be same size");
        }
        String r = "";
        r += addExactMatches(guess);
        r += addExtraneousMatches(guess);
        blPos.clear();
        return r;
    }

    public char[] getCombination() {return com;}
    public char[] getOptions() {return opt;}

    /**
     * Returns true if opt contains all the characters in com.
     * Returns false otherwise.
     * @param com combination
     * @param opt options
     * @return boolean value
     */
    private boolean optContainsCom(char[] com, char[] opt) {
        String optStr = strRep(opt);
        for (char c : com) {
            String rep = "";
            rep += c;
            if (!optStr.contains(rep)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a String representation of a character array, whereas
     * the characters are stand-alone and next to each other.
     * @param arr character array
     * @return String representation of arr
     */
    private static String strRep(char[] arr) {
        String r = "";
        for (char c : arr) {
            r += c;
        }
        return r;
    }

    private String addExactMatches(char[] guess) {
        String r = "";
        int pos = 0;
        int gPos = 0;
        for (char c : guess) {
            for (char cha : com) {
                if (cha == c && !blPos.contains(pos) && gPos == pos) {
                    r += "*";
                    blPos.add(pos); // block position from being queried
                    break;
                }
                pos++;
            }
            gPos++;
            pos = 0;
        }
        return r;
    }

    private String addExtraneousMatches(char[] guess) {
        String r = "";
        int pos = 0;
        for (char c : guess) {
            for (char cha : com) {
                if (cha == c && !blPos.contains(pos)) {
                    r += "?";
                    blPos.add(pos); // block position from being queried
                    break;
                }
                pos++;
            }
            pos = 0;
        }
        return r;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        char[] code = {'b', 'a', 'a', 'b'};
        char[] opt = {'a', 'b', 'c', 'd'};
        char[] guess;
        Mastermind mm = new Mastermind(code, opt);
        System.out.println("code: " + strRep(code));
        System.out.println("options: " + strRep(opt));
        while (true) {
            System.out.print("Please Enter a guess: ");
            guess = in.nextLine().toCharArray();
            try {
                System.out.println("correctness: " + mm.testForCom(guess));
            } catch (DimensionMismatchException e) {
                System.out.println("Incorrect input. Try again.");
            }
        }
    }
}
