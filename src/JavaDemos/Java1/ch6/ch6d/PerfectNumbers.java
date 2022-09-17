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
public class PerfectNumbers {

    public static void main(String[] args) {
        System.out.println("Perfect Numbers");
        final int MAX = 4;

        int numToTest = 1;
        int numberOfSolutions = 0; //found so far
        while (numberOfSolutions < MAX) {

            // check if numToTest is perfect by calculating the
            // sum of it's divisors
            int sumOfDivisors = 0;
            for (int k = 1; k < numToTest; k++) {
                if (numToTest % k == 0) {
                    sumOfDivisors += k;
                }
            }

            if (sumOfDivisors == numToTest) {
                numberOfSolutions++;
                System.out.println(numberOfSolutions + ". " + numToTest);
            }

            numToTest++;
        }
    }
}
