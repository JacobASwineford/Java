package Misc.CustomClasses.ShutTheBox;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Shut the box is an english pub game where the goal is to
 * get the lowest score possible via flipping numerically numbered pegs.
 *
 * This class keeps track of the board state (un-flipped numerical values)
 * and the current sum of the dice, as to compare to the numerical values
 * on the pegs.
 *
 * @author Jacob Swineford
 */
public class ShutTheBox {

    private ArrayList<Integer> board;
    private int sum;
    public static final int ORIGINAL_BOARD_LENGTH = 9;

    /**
     * Initializes the ArrayList board to contain the values 1 - 9,
     * and the value of sum = 0;
     */
    public ShutTheBox() {
        board = new ArrayList<>();
        sum = 0;
        for (int i = 1; i < 10; i++) {
            board.add(i);
        }
    }

    /**
     * Allows the caller to specify an peg number to flip,
     * one that is currently face-up on the board. if the number
     * specified is higher than the sum, this method is ignored.
     */
    public void flip(int i) {
        if (isValidFlip(i)) {
            board.remove(indexOf(i));
            sum -= i;
        }
    }

    /**
     * Rolls a pair of dice makes sum (instance variable)
     * = to the sum of the two dice.
     */
    public void roll() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int d = rand.nextInt(6) + 1;
        int d2 = rand.nextInt(6) + 1;
        sum = d + d2;
    }

    /**
     * Resets the game to the default position:
     *  - sum = 0;
     *  - ArrayList board all to 1 - 9;
     */
    public void reset() {
        sum = 0;
        board.clear();
        for (int i = 1; i < 10; i++) {
            board.add(i);
        }
    }

    /**
     * Checks if a flip is valid according to the current conditions
     * of the game. This includes being higher or lower than the extrema
     * values of the board ArrayList, and being higher than the current
     * sum.
     *
     * Returns false if these conditions are not meet.
     * otherwise, returns true.
     */
    public boolean isValidFlip(int flip) {
        if (flip > sum || flip < lowestNum(board) || !board.contains(flip)) {
            return false;
        }
        return true;
    }



    /**
     * returns varied amounts of information about the current
     * game state. this includes:
     *  - the numerical values face-up
     *  - the numerical values flipped face-down
     *  - the current sum
     */
    @Override
    public String toString() {

        StringBuilder faceUp = new StringBuilder();
        for (int i : board) {
            faceUp.append(i);
            faceUp.append(" ");
        }

        if (faceUp.length() != 0) {
            faceUp.delete(faceUp.length() - 1, faceUp.length());
        }

        StringBuilder faceDown = new StringBuilder() ;
        for (int g = 1; g < 10; g++) {
            if (!board.contains(g)) {
                faceDown.append(g);
                faceDown.append(" ");
            }
        }

        if (faceDown.length() != 0) {
            faceDown.delete(faceDown.length() - 1, faceDown.length());
        }

        return "Face-up: [" + faceUp + "]\nFace-down: [" + faceDown
                + "]\nCurrent sum: [" + sum + "]";
    }

    /**
     * Finds the 0-based index of the number specified
     * in the ArrayList board.
     */
    private int indexOf(int i) {
        int in = 0;
        for (int e : board) {
            if (e == i) {
                return in;
            }
            in++;
        }
        return in;
    }

    /**
     * returns true if the current sum value updated by dice = 0.
     * returns false otherwise.
     */
    public boolean sumIsZero() {
        if (sum == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the current sum.
     */
    public int getSum() {
        return sum;
    }

    /**
     * Returns a copy of the current ArrayList board.
     *
     * This method has the intention that the ArrayList will only
     * be returned as to be displayed. If, used by external code to
     * create a new ArrayList, that ArrayList will not be reached
     * by the methods of this class.
     */
    public ArrayList<Integer> getBoard() {
      return (ArrayList<Integer>) board.clone();
    }

    /**
     * Returns the sum of all the numbered pegs on the ArrayList board.
     */
    public int getBoardSum() {
        int s = 0;
        for (int i : board) {
            s += i;
        }
        return s;
    }

    /**
     * Checks if the player can make the sum using the current board.
     * if not, this method returns false. returns true otherwise.
     *
     * The player is allowed to use as many pegs as they want.
     * However, in the current state of the game
     * the player will never need to flip more than 4
     * for any given sum.
     *
     * This exemplifies the minimum values that can be used for calculations.
     * (1 + 2 + 3 + 4 = 10) & (1 + 2 + 3 + 4 + 5) = 15
     * The maximum sum is 12, and that can't be made with 5 or more flips.
     */
    public boolean isPossible() {
        ArrayList<Integer> copy = new ArrayList<>();
        copy.add(0);
        for (int i : board) {
            copy.add(i);
        }

        for (int i : copy) {
            for (int k : copy) {
                for (int j : copy) {
                    for (int t : copy) {
                        if (negationChain(i, k, j, t) &&
                        i + k + j + t == sum) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Out of the numbers given, checks if any of the values are the same.
     * in the case that 0 is the integer, it is ignored.
     *
     * if all the numbers are different, this method returns true. otherwise,
     * false.
     */
    private boolean negationChain(int... i) {
        int[] checker = new int[biggestNum(i)];
        for (int j : i) {
            if (j != 0) {
                checker[j - 1]++;
            }
        }

        for (int e : checker) {
            if (e > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes an int array and returns the biggest numerical value.
     */
    private static int biggestNum(int[] arr) {
        int b = 0;
        for (int i : arr) {
            if (i > b) {
                b = i;
            }
        }
        return b;
    }

    /**
     * Takes an int array and returns the biggest numerical value.
     */
    private static int biggestNum(ArrayList<Integer> arr) {
        int b = 0;
        for (int i : arr) {
            if (i > b) {
                b = i;
            }
        }
        return b;
    }

    /**
     * Takes an int array and returns the lowest numerical value.
     */
    private static int lowestNum(ArrayList<Integer> arr) {
        int s = biggestNum(arr);
        for (int i : arr) {
            if (s - i > 0) {
                s = i;
            }
        }
        return s;
    }


    /**
     * Test Program featuring one round for one player.
     *
     * This also an example of how the class can be used to effectively
     * play the game via these methods.
     */
    public static void main(String[] args) {
        ShutTheBox s = new ShutTheBox();
        final int NUM_ROUNDS = 3;
        int[] score = new int[NUM_ROUNDS];
        for (int i = 0; i < NUM_ROUNDS; i++) {
            System.out.println("ROUND " + (i + 1));
            System.out.println("-------");
            s.roll();
            while (s.isPossible()) {
                Scanner in = new Scanner(System.in);
                System.out.println(s.toString());
                System.out.print("\nWhats the flip?: ");
                int g = in.nextInt();
                if (!s.isValidFlip(g)) {
                    System.out.print("Invalid input. Try again.");
                    System.out.println();
                }
                System.out.println();
                s.flip(g);
                if (s.sumIsZero()) {
                    s.roll();
                }
            }
            System.out.println(s.toString() +"\n");
            score[i] = s.getBoardSum();
            System.out.println("score: " + score[i]);
            s.reset();
            System.out.println();
        }

        int g = 0;
        System.out.println("These are your scores over"
                + NUM_ROUNDS + "rounds: ");
        for (int h : score) {
            System.out.print(h + " ");
            g += h;
        }
        System.out.println("\nThis is your total score over "
                + NUM_ROUNDS + " Rounds: " +  g);
    }
}
