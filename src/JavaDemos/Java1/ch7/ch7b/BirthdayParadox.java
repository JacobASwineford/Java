package JavaDemos.Java1.ch7.ch7b;

import java.util.Random;

/**
 * Monte Carlo simulation to estimate the probability that at least two
 * people from a random sample of 23 will have the same birthday.
 *
 * According to the famous birthday Paradox, the probability is slightly
 * higher than 50%.
 *
 * @author Jacob Swineford
 */
public class BirthdayParadox {

    public static void main(String[] args) {

        Random rand = new Random();
        final int DAYS_IN_A_YEAR = 365;
        final int NUM_PEOPLE = 2;
        final int TRIALS = 10_000;

        // How many times does the random sample include two
        // people with the same birthday?
        int matches = 0;

        for (int i = 0; i < TRIALS; i++) {

            // Perform the experiment: generate random birthdays.
            int[] days = new int [DAYS_IN_A_YEAR];
            for (int j = 0; j < NUM_PEOPLE; j++) {
                int birthday = rand.nextInt(DAYS_IN_A_YEAR);
                days[birthday]++;
            }

            // Were there two birthdays that are the same?
            for (int k : days) {
                if (k > 1) {
                    matches++;
                    break; // all the question is asking is whether
                           // there is AT LEAST 1 match per year, so once the program
                           //finds one it will break from the loop and prevent matches
                           // from being buffed further than needed.
                }
            }
        }

        double prob = (double) matches / TRIALS * 100;
        System.out.printf("there were %d matching birthdays,\n" +
                        "for %d people,\nover %d trials.%n",
                matches, NUM_PEOPLE, TRIALS);
        System.out.printf("%.2f%% is the probability for those people having " +
                "the same birthday. %n", prob);

    }
}

