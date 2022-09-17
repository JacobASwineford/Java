package JavaDemos.Java1.ch7.ch7b;

import java.util.Random;
import java.util.Scanner;

/**
 * A music-playing device stores n songs and plays them in random order. This program
 * calculates the expected number of songs that would be played before one is
 * repeated.
 *
 * @author Jacob Swineford
 */
public class RandomPlayList {

    public static void main(String[] args) {
        System.out.print("How many songs on your playlist? ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        final int TRIALS = 1;
        int total = 0; // number of songs heard over all trials

        for (int i = 0; i < TRIALS; i++) {
            total += playSongs(n);
        }

        int result = total / TRIALS;
        String s = "Expected number of songs played before a repeat: ";
        System.out.println(s + " " + result);
    }

    /**
     * Selects songs to play at random until one is repeated.
     *
     * @param n the number of songs on the playlist
     * @returns the number of songs played
     */
    private static int playSongs(int n) {

        // songsPlayed[k] = true means the k-th song has been played
        boolean[] songsPlayed = new boolean[n];

        int numPlayed = 0;
        Random rand = new Random();
        while (true) {

            // choose a song to play.
            int k = rand.nextInt(n);
            numPlayed++;

            // has the k-th song already been played?
            if (songsPlayed[k]) { // if songsPlayed at k equals true. boolean array initializes at false
                return numPlayed;
            }
            songsPlayed[k] = true; // buffs the values in the array from false to true
        }
    }
}
