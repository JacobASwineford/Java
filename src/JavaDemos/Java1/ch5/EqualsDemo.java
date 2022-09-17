package JavaDemos.Java1.ch5;

import java.math.BigInteger;

/**
 * Demostrates a difference between primitive type variables and
 * object references.
 *
 * @author Jacob Swineford
 */
public class EqualsDemo {
    public static void main(String[] args) {
        int x1 = 12_345; // _ is ignored by the compiler, has no grammatical use
        int y1 = 12_345;
        System.out.printf("x1 is %,d and y1 %,d. %n", x1, y1);

        System.err.println("Something bad happened.");

        if (x1 == y1) {
            System.out.println("They are equal.");
        } else {
            System.out.println("They are not equal.");
        }

        BigInteger x2 = new BigInteger("12345");
        BigInteger y2 = new BigInteger("12345");
        System.out.printf("x2 is %,d and y2 is %,d. %n", x2, y2);

        if (x2 == y2) { //comparing memory locations of objects, they will not be equal, alternatively use x2.equals(y2)
            System.out.println("They are equal.");
        } else {
            System.out.println("They are not equal.");
        }
    }
}
