package Misc.CustomClasses.Other;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of cards that can be shuffled and used for
 * classic card games. These are represented as Strings with
 * the names of cards.
 *
 * Serves as a model for a classic deck of cards, containing
 * 52 cards with 4 suites of spades, diamonds, hearts, and clubs.
 * each suite contains:
 * Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, and King.
 *
 *
 * @author Jacob Swineford
 */
public class DeckOfCards {

    // in general, instance variables can be initialized here if all
    // the constructors would initialize it the same way
    private ArrayList<String> deck = new ArrayList<>();

    /**
     * Initializes a deck of cards in a non-shuffled format.
     * Default shuffle calls for Heart, Spade, diamond, clubs
     * suites in that order.
     */
    public DeckOfCards() {
        String s;
        for (int i = 0; i < 4; i++) {
            for (int k = 1; k <= 13; k++) {
                if (i == 0) {
                    s = "Hearts";
                } else if (i == 1) {
                    s = "Spades";
                } else if (i == 2) {
                    s = "Diamonds";
                } else {
                    s = "Clubs";
                }

                if (k == 1) {
                    deck.add("Ace of " + s);
                } else if (k < 11) {
                    deck.add(k + " of " + s);
                } else if (k == 11) {
                    deck.add("Jack of " + s);
                } else if (k == 12) {
                    deck.add("Queen of " + s);
                } else {
                    deck.add("King of " + s);
                }
            }
        }
    }

    /**
     * Shuffles the current deck via the Fisher-Yate method.
     */
    public void shuffle() {
        for (int i = 0; i < deck.size(); i++) {
            Random rand = new Random();
            int r = rand.nextInt(deck.size() - i) + i;
            String t = deck.get(i);
            String g = deck.get(r);
            deck.remove(i);
            deck.add(i, g);
            deck.remove(r);
            deck.add(r, t);
        }
    }

    /**
     * Returns a string representation of the current deck ArrayList.
     *
     * This method relies on the use of factory methods for returning
     * an ArrayList object as a string.
     */
    @Override
    public String toString() {
        return deck.toString();
    }

    /**
     * Returns a copy of the current ArrayList board.
     *
     * This method has the intention that the ArrayList will only
     * be returned as to be displayed. If, used by external code to
     * create a new ArrayList, that ArrayList will not be reached
     * by the methods of this class.
     */
    public ArrayList<String> getDeck() {
        return (ArrayList<String>) deck.clone();
    }

    /**
     * Returns the current index of the specified card.
     *
     * This method requires the following specific arguments:
     * 1. The specific rank: 2-10, Jack, Queen, King, Ace
     * 2. One of the four suits: Hearts, Spades, Clubs, Diamonds
     *
     * if the card does not exist within the deck, then -1 is returned.
     */
    public int getIndexOf(String rank, String suit) {
        int index = 0;
        for (String s : deck) {
            if(s.contains(rank) && s.contains(suit)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns the current card occupying the current index.
     * if the card does not exist within the deck, then an IndexOutOfBounds
     * exception is thrown.
     */
    public String getCardOf(int index) {
        return deck.get(index);
    }

    /**
     * Removes the specified card from the deck.
     *
     * This method requires the following specific arguments:
     * 1. The specific rank: 2-10, Jack, Queen, King, Ace
     * 2. One of the four suits: Hearts, Spades, Clubs, Diamonds
     */
    public void remove(String rank, String suit) {
        deck.remove(getIndexOf(rank, suit));
    }

    /**
     * Removes the specified card from the deck.
     *
     * This method requires the following specific arguments:
     * 1. The specific rank: 2-10, Jack, Queen, King, Ace
     * 2. One of the four suits: Hearts, Spades, Clubs, Diamonds
     */
    public void remove(int index) {
        deck.remove(index);
    }

    /**
     * Adds the specified card to the deck, at the specified position.
     */
    public void add(int index, String cardName) {
        deck.add(index, cardName);
    }

    /**
     * Adds the specified card to the deck, at position 0.
     */

    public void add(String cardName) {
        this.add(0, cardName); // "this" for clarity
    }

    /**
     * Returns an ArrayList<String> of all the cards
     * in the ArrayList<String> deck
     * that contain the given String.
     */
    public ArrayList<String> containing(String str) {
        ArrayList<String> returned = new ArrayList<>();
        for (String s : deck) {
            if (s.contains(str)) {
                returned.add(s);
            }
        }
        return returned;
    }

}
