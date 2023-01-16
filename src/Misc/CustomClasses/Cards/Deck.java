package Misc.CustomClasses.Cards;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Standard Deck of 52 playing cards, with 4 suites of 13.
 *
 * @author Jacob Swineford
 */
public class Deck {

    private final Stack<Integer> cards;

    /**
     * The value, suite, and color of a card in this deck depends on the value of these
     * variables. these are the values for a normal deck of 52 cards, with 4 suites of
     * 13.
     * <p>
     * I would recommend that the SUITE_SIZE fits evenly into DECK_SIZE, and that DECK_SIZE
     * is even to divide the colors of a deck in half.
     * <p>
     * WARNING: IF THESE VALUES ARE CHANGED, THE NAMES ARRAYS SHOULD BE CHANGED TO COMPENSATE
     * FOR THE NEW VALUES
     */
    private static final int DECK_SIZE = 52;
    private static final int SUITE_SIZE = 13;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = SUITE_SIZE;

    /**
     * various arrays that store string representations of numeric value, suite and color
     * values.
     */
    public static final String[] VALUE_NAMES = {null, "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King"};
    public static final String[] SUITE_NAMES = {"Diamonds", "Hearts", "Spades", "Clubs"};
    public static final String[] COLOR_NAMES = {"Red", "Black"};

    /**
     * a card's suite, color and number / value are represented by a single integer,
     * with Jacks, Queens and Kings having the values 11, 12, and 13 respectively.
     * <p>
     * This constructor constructs the deck of cards and assigns the necessary
     * values for parsing this deck of cards. these values are called deck values.
     */
    public Deck() {
        cards = new Stack<>();
        for (int i = 0; i < DECK_SIZE; i++)
            cards.add(i);
    }

    /**
     * Draws the top x cards from this deck, and reports this deck values in the order
     * they were revealed in. This will remove these cards from this deck.
     * <p>
     * If the number of cards to be drawn exceed the current number of cards in the
     * deck, then no cards are drawn and null is returned.
     *
     * @param x number of cards to draw
     * @return array of drawn cards, represented by deck values, in order. null on error
     */
    public int[] draw(int x) {
        if (cards.size() - x < 0) return null;

        int[] ret = new int[x];
        for (int i = 0; i < x; i++)
            ret[i] = cards.pop();
        return ret;
    }

    /**
     * Shuffles all the cards in this deck.
     */
    public void shuffle() {
        // Fisher Shuffling
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < cards.size(); i++) {
            int r = rand.nextInt(i, cards.size());
            int t = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, t);
        }
    }

    /**
     * Adds a card, represented by its deck value, to this deck.
     *
     * @param deckValue card, represented by its deck value, to add
     */
    public void add(int deckValue) {
        cards.add(deckValue);
    }

    /**
     * Clears the deck, so no cards are present.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Gets the suite of a card, given it's deck value.
     *
     * @param deckValue deck value of card
     * @return suite of card
     */
    public static int suite(int deckValue) {
        return deckValue / SUITE_SIZE;
    }

    /**
     * Gets the color of a card, given its deck value.
     *
     * @param deckValue deck value of card
     * @return color of card
     */
    public static int color(int deckValue) {
        return deckValue / (DECK_SIZE / COLOR_NAMES.length);
    }

    /**
     * Gets the value of a card, given its deck value.
     *
     * @param deckValue deck value of card
     * @return value of card
     */
    public static int value(int deckValue) {
        return (deckValue % SUITE_SIZE) + 1;
    }

    /**
     * Returns a Card object with the proper values, based off of the given deck value.
     *
     * @param deckValue given deckValue
     * @return Card representation from deck value
     */
    public static Card card(int deckValue) {
        return new Card(Deck.suite(deckValue), Deck.color(deckValue), Deck.value(deckValue), deckValue);
    }

    /**
     * Returns a Card object with the given suite, color, and value. This also
     * includes calculating its deck value.
     *
     * @param suite String representation of suite
     * @param color String representation of color
     * @param value numeric value for the card (generally 1 - 13)
     */
    public static Card card(String suite, String color, int value) {
        int suiteIndex = has(SUITE_NAMES, suite);
        int colorIndex = has(COLOR_NAMES, color);

        if (suiteIndex != -1 && colorIndex != -1 && value >= MIN_VALUE && value <= MAX_VALUE) {
            int su = suiteIndex * SUITE_SIZE;
            int va = value - 1;
            int deckValue = su + va;
            return new Card(suiteIndex, colorIndex, value, deckValue);
        }

        return null;
    }

    /**
     * Returns a String representation of this deck.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        int count = 1;
        for (int i = cards.size() - 1; i > -1; i--) {
            String toAppend = "\t" + count++ + " -> " + Deck.card(cards.get(i)) + "\n";
            sb.append(toAppend);
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Generic method to find if an object is contained within an array of the same type.
     *
     * @param arr array to search through
     * @param toFind object to test, to see if it is contained within arr
     * @param <T> Generic type T defining type of array to be searched
     *
     * @return index of the found element, -1 otherwise
     */
    private static <T> int has(T[] arr, T toFind) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == toFind) return i;
        return -1;
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        d.shuffle();
        System.out.println(d);
        System.out.println();
        for (int i = 0; i < d.cards.size(); i++) {
            int deckValue = d.cards.get(i);
            Card card = Deck.card(deckValue);
            Card cardSame = Deck.card(card.getSuiteName(), card.getColorName(), card.getValue());
            System.out.println(card);
            System.out.println("same: " + cardSame);
        }
        System.out.println("----");

        int[] ret = d.draw(3);
        System.out.println(Arrays.toString(ret));
    }
}
