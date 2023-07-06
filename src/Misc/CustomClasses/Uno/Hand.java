package Misc.CustomClasses.Uno;

import java.util.LinkedList;

public class Hand {

    private final LinkedList<Card> cards;

    Hand() {
        cards = new LinkedList<>();
    }

    public LinkedList<Card> getCards() {return cards;}
    public void addCard(Card card) {cards.add(card);}
    public void removeCard(Card card) {cards.remove(card);}
}
