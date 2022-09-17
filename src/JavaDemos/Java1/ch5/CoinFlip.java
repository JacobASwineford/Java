package JavaDemos.Java1.ch5;

/**
 * Simulates a coin flip and outputs HEADS or TAILS with equal probability.
 *
 * @author Jacob Swineford
 */
public class CoinFlip {

    public static void main(String[] args) {

        // Version 1: using two if statements.
        double d = Math.random();
        if (d < 0.5) {
            System.out.println("HEADS");
        }
        if (d >= 0.5) {
            System.out.println("TAILS");
        }

        // Version 2: using an if-else statement.
        if (Math.random() < 0.5) {
            System.out.println("HEADS");
        } else {
            System.out.println("TAILS");
        }

    }
}

