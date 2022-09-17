package JavaDemos.Java1.ch6.ch6b;

/**
 * Approximates the value of pi using Euler's identity
 *
 *          pi = 4 - 4/3 + 4/5 - 4/7 + ...
 *
 * @author Jacob Swineford
 */
public class PiApproximatorv2 {

    public static void main(String[] args) {

        double pi = 4.0;
        final int TERMS = 100_000_000;
        int denominator = 3;

        int sign = -1;
        for (int i = 0; i < TERMS; i++) {
            pi += sign * 4.0 / denominator;
            sign *= -1;
            denominator += 2;
        }

        //System.out.println("\u03c0");

        System.out.println("      Estimated value of pi = " + pi);
        System.out.println("Correct rounded value of pi = " + Math.PI);
        System.out.println("Goodbye! \u263a");

    }
}
