package JavaDemos.Java1.ch7.ch7a;

import java.util.Random;
import java.util.Scanner;

/**
 * Outputs a love letter with randomly selected words.
 *
 * @author Jacob Swineford
 */
public class LoveLetterGenerator {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of the recipient: ");
        String name = in.next();

        String[] verbs = {"seek", "crave", "fear", "envy", "embrace",
                "behold"};

        String[] adverbs = {"dreamily", "ominously", "strangely",
                "tearfully", "tenderly"};

        String[] adjectives = {"dazzling", "shimmering", "blinding", "pulsating",
                "infinite", "shattered", "overflowing", "melancholy", "lunar"};

        String[] nouns = {"moon", "heart", "mind", "core", "mist", "life", "ghost",
                "field", "shadow", "form"};

        // Dear [name], I [verb] your [adverb] [adjective] [noun] forever!
        String verb = get(verbs);
        String adv = get(adverbs);
        String adj = get(adjectives);
        String noun = get(nouns);

        String letter = "Dear %s, I %s your %s %s %s forever! %n";
        System.out.printf(letter, name, verb, adv, adj, noun);

    }
    /**
     * Returns a randomly chosen element from a given array of strings.
     */
    private static String get(String[] arr) {
        Random rand = new Random();
        int k = rand.nextInt(arr.length);
        return arr[k];
    }
}
