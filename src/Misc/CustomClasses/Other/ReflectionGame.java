package Misc.CustomClasses.Other;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Engine for the Reflection game. The game goes as follows:
 *
 * 1. from the generated gameState, two letters are chosen.
 * 2. the String in between (inclusive) is reversed.
 * 3. the game ends when the letters are in alphabetical order.
 *
 * @author Jacob Swineford
 */
public class ReflectionGame {

    private String gameState = "";
    private ArrayList<String> preGameStates = new ArrayList<>();
    private final int DEFAULT_SHUFFLE = 3;
    private String oGGameState;

    /**
     * Initializes the reflection game. This is done
     * by having the player specify the number of letters
     * to be generated.
     *
     * I couldn't find a better way than a long series of
     * if statements. I feel as though there is a better,
     * simpler way (like the information for the english alphabet
     * is stored elsewhere).
     */
    public ReflectionGame(int n) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                gameState += "A";
            }if (i == 1) {
                gameState += "B";
            }if (i == 2) {
                gameState += "C";
            }if (i == 3) {
                gameState += "D";
            }if (i == 4) {
                gameState += "E";
            }if (i == 5) {
                gameState += "F";
            }if (i == 6) {
                gameState += "G";
            }if (i == 7) {
                gameState += "H";
            }if (i == 8) {
                gameState += "I";
            }if (i == 9) {
                gameState += "J";
            }if (i == 10) {
                gameState += "K";
            }if (i == 11) {
                gameState += "L";
            }if (i == 12) {
                gameState += "M";
            }if (i == 13) {
                gameState += "N";
            }if (i == 14) {
                gameState += "O";
            }if (i == 15) {
                gameState += "P";
            }if (i == 16) {
                gameState += "Q";
            }if (i == 17) {
                gameState += "R";
            }if (i == 18) {
                gameState += "S";
            }if (i == 19) {
                gameState += "T";
            }if (i == 20) {
                gameState += "U";
            }if (i == 21) {
                gameState += "V";
            }if (i == 22) {
                gameState += "W";
            }if (i == 23) {
                gameState += "X";
            }if (i == 24) {
                gameState += "Y";
            }if (i == 25) {
                gameState += "Z";
            }
        }

        oGGameState = gameState;
        for (int j = 0; j < DEFAULT_SHUFFLE; j++) {
            reverse(gameState.charAt(rand.nextInt(n)), gameState.charAt(rand.nextInt(n)));
        }
        preGameStates.clear();
    }

    /**
     * Takes two characters and flips the string in between them (inclusive).
     */
    public void reverse(char c1, char c2) {
        preGameStates.add(gameState);
        char first;
        char second;
        if (gameState.indexOf(c1) == gameState.indexOf(c2)) {
            return;
        } else if (gameState.indexOf(c1) > gameState.indexOf(c2)) {
            first = c2;
            second = c1;
        } else {
            first = c1;
            second = c2;
        }

        String beginning = gameState.substring(0, gameState.indexOf(first));
        StringBuilder middle = new StringBuilder(
                gameState.substring(gameState.indexOf(first),
                        gameState.indexOf(second) + 1));
        String end = gameState.substring(gameState.indexOf(second) + 1);
        middle.reverse();

        gameState = beginning + middle + end;
    }

    /**
     * Determines of the game is over, via checking of the letters
     * are in alphabetical order. returns true if so, false otherwise.
     */
    public boolean over() {
        if (gameState.equals(oGGameState)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder gSSpaces = new StringBuilder();
        for (char c : gameState.toCharArray()) {
            gSSpaces.append(c + " ");
        }
        gSSpaces.deleteCharAt(gSSpaces.length() - 1); // delete extraneous space
        return gSSpaces.toString();
    }

    /**
     * returns all the Strings of the previous gameStates.
     */
    public String history() {
        String history = "";
        for (String s : preGameStates) {
            StringBuilder hSSpaces = new StringBuilder();
            for (char c : s.toCharArray()) {
                hSSpaces.append(c + " ");
            }
            hSSpaces.deleteCharAt(hSSpaces.length() - 1);
            history += hSSpaces + "\n";
        }
        return history;
    }

    /**
     * @author Drue Coles
     */
    public static void main(String[] args) {

        System.out.print("Enter puzzle size: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ReflectionGame game = new ReflectionGame(n);
        System.out.println("\n" + game);

        // Play the game until the player wins or quits.
        while (!game.over()) {

            // The user can enter lowercase or uppercase letters.
            // The two letters must be separated by a space.
            System.out.print("Enter move (0 to quit): ");
            char first = sc.next().toUpperCase().charAt(0);

            if (!Character.isLetter(first)) { // player quits
                break;
            }

            char second = sc.next().toUpperCase().charAt(0);

            if (!Character.isLetter(second)) { // player quits
                break;
            }

            game.reverse(first, second);
            System.out.println("\n" + game);
        }

        System.out.println();
        if (game.over()) {
            System.out.println("GAME OVER. CONGRATULATIONS!");
        }

        System.out.println("\nGame history: ");
        System.out.println(game.history());
    }
}
