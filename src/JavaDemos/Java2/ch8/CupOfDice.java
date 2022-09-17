package JavaDemos.Java2.ch8;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A collection of dice that can be rolled individually or all at once.
 *
 * Serves as a model for a specified amount of dice, with all sides
 * the same.
 *
 * @author Jacob Swineford
 */
public class CupOfDice {

    private final int[] dice; // once initialized, the size cannot be changed
    private final int sides; // instance variable
    public static int test; // initializes at 0.
    public static final int test2 = 0; // final implies that the variable be
    static int i = 0; // implies that the package access is public


    /**
     * Creates a cup of k-sided dice with random initial values between 1 and k.
     *
     * Initializer using 2 parameters.
     * overloaded method.
     *
     * @param n the number of dice in the cup
     * @param k the number of sides per die
     */
    public CupOfDice(int n, int k) {
        dice = new int[n];
        sides = k;
        roll();
    }

    /**
     * Creates a cup of 6-sided dice with random initial values between 1 and 6.
     *
     * Initializer using 1 parameter.
     * overloaded method.
     *
     * @param n the number of dice in the cup
     */
    public CupOfDice(int n) {
        this(n, 6);
    }

    /**
     * Rolls each die in the cup.
     *
     * a method to be called on a CupOfDice object.
     * manipulates it's instance variables.
     *
     * @return the current dice values
     */
    public final int[] roll() {
        for (int i = 0; i < dice.length; i++) {
            roll(i); // returned int[] with a modified value for i
        }
        return dice.clone(); // defensive copy
    }

    /**
     * Rolls one of the dice.
     *
     * a method to be called on a CupOfDice object.
     * manipulates it's instance variables.
     *
     * @param i zero-based index of die to roll
     * @return the current dice values
     */
    public final int[] roll(int i) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        dice[i] = 1 + rand.nextInt(sides);
        return dice.clone(); // defensive copy
    }

    /**
     * Prints the int[] of dice, formatted with a space.
     *
     * A method to be called on a CupOfDice object.
     * prints out values of the instance int[] dice.
     *
     */
    public void print() {
        for (int i : dice) {
            System.out.print(i + " ");
        }
     }
}

