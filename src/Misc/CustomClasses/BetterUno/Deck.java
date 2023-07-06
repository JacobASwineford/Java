package Misc.CustomClasses.BetterUno;

import Misc.CustomClasses.BetterUno.Cards.*;
import Misc.CustomClasses.BetterUno.Cards.Number;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import static Misc.CustomClasses.BetterUno.Color.*;

/**
 * Deck for a game of UNO. A Deck consists of a draw pile and
 * a discard pile.
 */
public class Deck {

    private final Stack<Card> draw;
    private final Stack<Card> discard;

    /**
     * Constructor for this deck.
     */
    public Deck() {
        draw = new Stack<>();
        discard = new Stack<>();
        defaultCards();
        shuffle(draw);
    }

    /**
     * Draws a card from the draw pile. If there are currently no cards
     * in the draw pile, then the discard pile is shuffled into the
     * draw pile.
     *
     * @return Top card of the draw stack.
     */
    public Card draw() {
        try {
            return draw.pop();
        } catch (EmptyStackException e) {
            shuffle(discard);
            draw.addAll(discard);
            discard.clear();
        }
        return draw.pop();
    }

    /**
     * Adds a card to the discard pile.
     *
     * @param card card to add.
     */
    public void discard(Card card) {discard.add(card);}

    /**
     * Randomly shuffles the given stack of cards.
     *
     * @param stack given stack.
     */
    public void shuffle(Stack<Card> stack) {
        Card tmp;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < stack.size(); i++) {
            int r = rand.nextInt(i, stack.size());
            tmp = stack.get(i);
            stack.set(i, stack.get(r));
            stack.set(r, tmp);
        }
    }

    /**
     * Resets this deck to its default state.
     */
    public void reset() {
        discard.clear();
        draw.clear();
        defaultCards();
    }

    /**
     * Adds the default set of cards to this deck. This includes:
     * 80 Number cards (20 for each color, 2 for each number {2 * 0, 2 * 1, ... 2 * 9})
     * 20 draw 2 cards (5 for each color)
     * 20 reverse cards (5 for each color)
     * 20 skip cards (5 for each color)
     * 20 wild cards
     * 10 wild draw 4 cards
     */
    private void defaultCards() {
        int val;

        // add color cards
        for (int i = 0; i < 5; i++) {
            val = i % 10;
            draw.add(new Number(val, BLUE));
            draw.add(new Number(val, GREEN));
            draw.add(new Number(val, YELLOW));
            draw.add(new Number(val, RED));
        }

        // add draw 2 cards, reverse, skip, wild
        for (int i = 0; i < 5; i++) {
            draw.add(new Draw2(BLUE));
            draw.add(new Draw2(GREEN));
            draw.add(new Draw2(RED));
            draw.add(new Draw2(YELLOW));

            draw.add(new Reverse(BLUE));
            draw.add(new Reverse(GREEN));
            draw.add(new Reverse(RED));
            draw.add(new Reverse(YELLOW));

            draw.add(new Skip(BLUE));
            draw.add(new Skip(GREEN));
            draw.add(new Skip(YELLOW));
            draw.add(new Skip(RED));

            draw.add(new Wild());
            draw.add(new Wild());
            draw.add(new Wild());
            draw.add(new Wild());
        }

        // add wild draw 4 cards
        for (int i = 0; i < 10; i++)
            draw.add(new Wild());
    }
}
