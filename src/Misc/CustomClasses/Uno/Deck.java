package Misc.CustomClasses.Uno;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import static Misc.CustomClasses.Uno.Color.*;
import static Misc.CustomClasses.Uno.Effect.*;
import static Misc.CustomClasses.Uno.Card.*;
import static Misc.CustomClasses.Uno.Precedence.*;

public class Deck {

    private final Stack<Card> draw;
    private final Stack<Card> discard;

    Deck() {
        draw = new Stack<>();
        discard = new Stack<>();
        addInitialCards();
        shuffle(draw);
    }

    /**
     * Adds a card to this deck, to the draw pile.
     *
     * @param card card to add.
     */
    public void add(Card card) {draw.add(card);}

    /**
     * Draws a card from the top of the deck.
     *
     * @return drawn card.
     */
    public Card draw() {
        if (draw.size() == 0) {
            shuffle(discard);
            draw.addAll(discard);
            discard.clear();
        }
        return draw.pop();
    }

    /**
     * Discards a card from this deck.
     *
     * @param card card to discard.
     */
    public void discard(Card card) {discard.add(card);}

    /**
     * Resets this deck to it's inital values, and adds all
     * inital cards back into its draw pile.
     */
    public void reset() {
        draw.clear();
        discard.clear();
        addInitialCards();
    }

    /**
     * Returns the number of cards present in this deck, including the draw and
     * discard stacks.
     *
     * @return number of cards present in this deck
     */
    public int present() {
        return draw.size() + discard.size();
    }

    /**
     * Adds the cards to the draw stack that are initially in a deck of uno cards.
     */
    private void addInitialCards() {
        // add color cards
        draw.add(new Card(0, BLUE, 0, NUMBER_PRECEDENCE));
        draw.add(new Card(0, GREEN, 0, NUMBER_PRECEDENCE));
        draw.add(new Card(0, YELLOW, 0, NUMBER_PRECEDENCE));
        draw.add(new Card(0, RED, 0, NUMBER_PRECEDENCE));
        for (int i = 1; i < 9; i++) {
            draw.add(new Card(i, BLUE, 0, NUMBER_PRECEDENCE));
            draw.add(new Card(i, BLUE, 0, NUMBER_PRECEDENCE));
            draw.add(new Card(i, GREEN, 0, NUMBER_PRECEDENCE));
            draw.add(new Card(i, GREEN, 0, NUMBER_PRECEDENCE));
            draw.add(new Card(i, YELLOW, 0, NUMBER_PRECEDENCE));
            draw.add(new Card(i, YELLOW, 0, NUMBER_PRECEDENCE));
            draw.add(new Card(i, RED, 0, NUMBER_PRECEDENCE));
        }

        // add draw two cards, reverse cards, skip cards
        for (int i = 0; i < 2; i++) {
            // draw 2
            draw.add(new Card(NO_VALUE, BLUE, 2, DRAW_2_PRECEDENCE));
            draw.add(new Card(NO_VALUE, GREEN, 2, DRAW_2_PRECEDENCE));
            draw.add(new Card(NO_VALUE, YELLOW, 2, DRAW_2_PRECEDENCE));
            draw.add(new Card(NO_VALUE, RED, 2, DRAW_2_PRECEDENCE));

            // reverse
            Card rb = new Card(NO_VALUE, BLUE, 0, REVERSE_PRECEDENCE);
            Card rg = new Card(NO_VALUE, GREEN, 0, REVERSE_PRECEDENCE);
            Card ry = new Card(NO_VALUE, YELLOW, 0, REVERSE_PRECEDENCE);
            Card rr = new Card(NO_VALUE, RED, 0, REVERSE_PRECEDENCE);
            rb.addEffect(REVERSE);
            rg.addEffect(REVERSE);
            ry.addEffect(REVERSE);
            rr.addEffect(REVERSE);
            draw.add(rb);
            draw.add(rg);
            draw.add(ry);
            draw.add(rr);

            // skip
            Card sb = new Card(NO_VALUE, BLUE, 0, SKIP_PRECEDENCE);
            Card sg = new Card(NO_VALUE, GREEN, 0, SKIP_PRECEDENCE);
            Card sy = new Card(NO_VALUE, YELLOW, 0, SKIP_PRECEDENCE);
            Card sr = new Card(NO_VALUE, RED, 0, SKIP_PRECEDENCE);
            sb.addEffect(SKIP);
            sg.addEffect(SKIP);
            sy.addEffect(SKIP);
            sr.addEffect(SKIP);
            draw.add(sb);
            draw.add(sg);
            draw.add(sy);
            draw.add(sr);
        }

        // 4 wild cards, 4 wild draw 4 cards
        for (int i = 0; i < 4; i++) {
            draw.add(new Card(NO_VALUE, WILD, 0, WILD_PRECEDENCE));
            draw.add(new Card(NO_VALUE, WILD, 4, WILD_DRAW_4_PRECEDENCE));
        }

    }

    /**
     * Suhffles the given stack of cards.
     *
     * @param cards stack of cards to shuffle.
     */
    private void shuffle(Stack<Card> cards) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        Card tmp;
        for (int i = 0; i < cards.size(); i++) {
            int r = rand.nextInt(i, cards.size());
            tmp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, tmp);
        }
    }
}
