package Misc.CustomClasses.BetterUno;

import Misc.CustomClasses.BetterUno.Cards.Card;

import java.util.ArrayList;

/**
 * A player in the game of UNO.
 */
public class Player {

    private final ArrayList<Card> cards;

    /**
     * Constructor for a player.
     */
    Player() {cards = new ArrayList<>();}

    /**
     * returns the first instance of the highest precedence
     * card in this players hand, checking for valid cards
     * via the given card. If no card is found that can be
     * played, null is returned.
     *
     * @param card given card to check against.
     */
    public Card retrieveHighestPrecedence(Card card) {
        Card re = null;
        int p = -10;
        for (Card c : cards) {
            if (c.valid(card) && c.getPrecedence() > p) {
                re = c;
                p = c.getPrecedence();
            }
        }
        return re;
    }

    /**
     * Adds a card to this player's hand.
     *
     * @param card card to add.
     */
    public void addCard(Card card) {cards.add(card);}

    /**
     * Removes a card from this player's hand.
     *
     * @param card card to remove.
     */
    public void removeCard(Card card) {cards.remove(card);}

    /**
     * Resets this player to its default state.
     */
    public void reset() {cards.clear();}

    /**
     * Returns the size of this players hand.
     *
     * @return size of this players hand.
     */
    public int count() {return cards.size();}
}
