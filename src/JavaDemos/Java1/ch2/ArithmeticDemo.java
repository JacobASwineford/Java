package JavaDemos.Java1.ch2;

/**
 * Illustrates basic arithmetic and the concept of variables.
 * @author Jacob Swineford
 */
public class ArithmeticDemo {

    public static void main(String[] args) {

        // declare variables
        int x = 3;
        int y = 5;
        int z = (x + y) * (x - y);

        System.out.println(z);
        System.out.println(3 + x * 8);
        System.out.println(23 / 5); // remainder: integer after division (4.6 is the actual, system outputs 4)
        System.out.println(5 / 23);
        System.out.println(23.0 / 5.0);

        System.out.println();
        System.out.println(23 % 5); // modulus operator (remainder after division) (3)

        int windspeed = 10; // mph
        int maxpoints = 0;
    }
}
