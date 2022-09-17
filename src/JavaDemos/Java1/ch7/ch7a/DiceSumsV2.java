package JavaDemos.Java1.ch7.ch7a;

/**
 * Calculates the probability of each possible
 * sum when rolling a pair of dice.
 *
 * @author Jacob Swineford
 */
public class DiceSumsV2 {

    public static void main(String[] args) {

        int[] sums = new int[13]; // [] represents arrays

        // Iterate over all possible errors of dice values and count
        // the sums.
        for (int die1 = 1; die1 <= 6; die1++) {
            for (int die2 = 1; die2 <= 6; die2++) {
                sums[die1 + die2]++;
            }
        }

        // Display results.
        System.out.println("SUM \t PROBABILITY");
        for (int i = 2; i <= 12; i++) {
            double percent = (double) sums[i] / 36 * 100;

            // Add a line of stars of length equal to percentage.
            String stars = "";
            for (int j = 0; j < Math.round(percent); j++) {
                stars += "*"; // lets you add a string to an existing string
            }

            String str = "%2d. %11.4f%% %s %n";
            System.out.printf(str, i, percent, stars);
        }
    }
}

