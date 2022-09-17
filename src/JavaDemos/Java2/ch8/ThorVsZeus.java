package JavaDemos.Java2.ch8;

/**
 * Calculates the probability of victory of Thor.
 *
 * Thor has 9 4-sides dice and Zeus has six 6-sided dice. They roll and add up
 * the numbers. The highest total wins. The game is a draw if the totals are the
 * same.
 *
 * @author Jacob Swineford
 */
public class ThorVsZeus {

    public static void main(String[] args) {
        CupOfDice thorDice = new CupOfDice(9, 4);
        CupOfDice zeusDice = new CupOfDice(6);

        // Monte carlo Simulation
        final int GAMES  = 10_000_000;
        int thorWins = 0;
        for (int i = 0; i < GAMES; i++) {
            int thorSum = sum(thorDice.roll());
            int zeusSum = sum(zeusDice.roll());

            if (thorSum > zeusSum) {
                thorWins++;
            }
        }

        // Display probability with 2 digits of precision
        double prob = ((double) thorWins / GAMES) * 100;
        String s ="Thor beats Zeus with probability %.2f%% %n";
        System.out.printf(s, prob);
    }

    /**
     * Returns the sum of elements given an array of integers.
     */
    private static int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }
}

