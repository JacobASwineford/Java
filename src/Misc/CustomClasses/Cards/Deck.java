package Misc.CustomClasses.Cards;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A deck of cards, which can be initialized to be a standard 52 pack of cards
 * and can be drawn from, shuffled, or appended with other cards of any value.
 *
 * @author Jacob Swineford
 */
public class Deck {

    private ArrayList<Integer> cards;

    /**
     * Constructor for a deck of cards. This constructor initializes
     * the Deck to have the default amount of cards, 52. Values for this
     * deck will be initialized such that the values of cards and their respective
     * suites will be values of 0 - 51.
     */
    public Deck() {
        cards = new ArrayList<>();
        reset();
    }

    /**
     * Resets this deck to have values 0 - 51, un-shuffled.
     */
    public void reset() {
        cards.clear();
        for (int i = 0; i < 52; i++) {
            cards.add(i);
        }
    }

    /**
     * Draws the top card from the deck, and removes that card from this deck.
     *
     * @return number representing card, -1 if deck is empty
     */
    public int draw() {
        if (cards.size() > 0) {
            int ret = cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
            return ret;
        }
        int i = 0;
        return -1;
    }

    /**
     * Shuffles the deck any particular number of times.
     *
     * @param times amount of times to perform the shuffle operation on this deck
     */
    public void shuffle(int times) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while(times-- != 0) {
            int from = rand.nextInt(cards.size());
            int to = rand.nextInt(cards.size());
            int temp = cards.get(from);
            cards.set(from, cards.get(to));
            cards.set(to, temp);
        }
    }

    /**
     * Adds a card to the deck.
     *
     * @param card number of card
     */
    public void add(int card) {
        cards.add(card);
    }

}
