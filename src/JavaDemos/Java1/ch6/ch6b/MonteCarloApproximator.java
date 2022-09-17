package JavaDemos.Java1.ch6.ch6b;

/**
 * Monte Carlo simulation to approximate the value of pi.
 *
 * @author Jacob Swineford
 */

public class MonteCarloApproximator {

    public static void main(String[] args) {
        final int NUM_DARTS = 1_000_000;
        int hits = 0;

        //simulate throwing the darts onto the square
        for (int i = 0; i < NUM_DARTS; i++) {

            // throw a dart
            double x = Math.random();
            double y = Math.random();

            if (Math.hypot(x, y) < 1) {
                hits++;
            }
        }

            // Area of bullseye: pi/4
            // Area of square: 1
            // Probability that a random dart lands in the bullseye: pi/4
            // Expected number of hits in the bullseye: (n/4) * NUM_DARTS
            // We can use the actual number of hits and solve for pi.
            double pi = 4.0 * hits / NUM_DARTS;
            System.out.println("      Estimated value of pi = " + pi);
            System.out.println("Correct rounded value of pi = " + Math.PI);
            System.out.println("Goodbye! \u263a");
    }
}




