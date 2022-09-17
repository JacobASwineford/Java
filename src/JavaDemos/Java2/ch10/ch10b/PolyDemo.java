package JavaDemos.Java2.ch10.ch10b;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Illustrates runtime (dynamic) polymorphism with an interface type.
 *
 * @author Jacob Swineford
 */
public class PolyDemo {
    public static void main(String[] args) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        if (rand.nextBoolean()) {
            System.out.println(sorted("apple", "peach", "plum")); // output -> true
        } else {
            BigInteger x = new BigInteger("12121212");
            BigInteger y = new BigInteger("92121212");
            BigInteger z = new BigInteger("55555555");
            System.out.println(sorted(x, y, z)); // Output -> false
        }
    }

    /**
     * Returns true if the given arguments are in sorted order.
     */
    private static boolean sorted(Comparable x, Comparable y, Comparable z) {
        return x.compareTo(y) <= 0 && y.compareTo(z) <= 0;
    }
}

