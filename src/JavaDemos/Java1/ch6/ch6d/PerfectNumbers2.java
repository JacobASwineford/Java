package JavaDemos.Java1.ch6.ch6d;

/**
 * Computes the first four perfect numbers.
 *
 * A perfect number is an integer that is equal to the sum of it's
 * positive divisors. The first four perfect numbers were known to
 * the ancient greeks.
 *
 * @author Jacob Swineford
 */
public class PerfectNumbers2 {

    public static void main(String[] args) {
        System.out.println("Perfect Numbers");
        final int MAX = 4;

        int numToTest = 1;
        int numberOfSolutions = 0; //found so far
        while (numberOfSolutions < MAX) {

            if (sumOfDivisors(numToTest) == numToTest) {
                numberOfSolutions++;
                System.out.println(numberOfSolutions + ". " + numToTest);
            }

            numToTest++;
        }
    }

    /**
     * Returns the sum of the divisors of a given integer.
     */
    private static int sumOfDivisors(int n) {
        int sum = 0;
        for (int k = 1; k < n; k++) {
            if (n % k == 0) {
                sum += k;
            }
        }
        return sum;
    }
}

