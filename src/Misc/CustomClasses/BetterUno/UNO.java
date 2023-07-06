package Misc.CustomClasses.BetterUno;

import Misc.CustomClasses.BetterUno.Cards.Card;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Engine for the game of UNO. In UNO, the turn player
 * plays a card that is valid to play with the current
 * top card showing on the play stack. Effects from cards are
 * resolved, and the turn player is the next player in the
 * turn order. Play continues until someone runs out of cards in their hand.
 */
public class UNO {

    /**
     * Player information.
     */
    private final ArrayList<Player> players;
    private Player current;
    private int currentIndex;
    private static final int startingHand = 7;

    /**
     * Game information.
     */
    private final Deck deck;
    private Card top;

    /**
     * Effect information.
     */
    private int order = 1;

    /**
     * Constructor for this game of UNO.
     */
    public UNO() {
        int playerNum = 4;
        deck = new Deck();
        players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            Player p = new Player();
            for (int j = 0; j < startingHand; j++)
                p.addCard(deck.draw());
            players.add(p);
        }
        currentIndex = 0;
        current = players.get(currentIndex);
        top = deck.draw();
    }

    /**
     * Runs the simulation, and returns the number of turns that was needed for it to complete.
     *
     * @return number of turns taken.
     */
    public int run() {
        int turns = 0;
        while (true) {
            turns++;
            Card toPlay = current.retrieveHighestPrecedence(top);
            if (toPlay == null) {
                while (toPlay == null) {
                    current.addCard(deck.draw());
                    toPlay = current.retrieveHighestPrecedence(top);
                }
            }

            // play the selected card
            boolean over = play(toPlay);
            if (over) break;
        }
        return turns;
    }

    /**
     * Plays the given card over the top card, resolving its effects.
     * This method then returns whether the game is over by determining
     * if the current player has 0 cards.
     *
     * @param card card to play.
     */
    public boolean play(Card card) {
        current.removeCard(card);
        deck.discard(card);
        top = card;
        if (current.count() == 0)
            return true;
        card.resolve(this);
        return false;
    }

    /**
     * Returns the next player in the turn sequence based on the given shift.
     *
     * @param shift how many players to move it the turn order.
     */
    public int next(int shift) {
        return ((shift % players.size()) + currentIndex + players.size()) % players.size();
    }

    /**
     * Advances the game, shifting control to the next player.
     */
    public void advance(int shift) {
        currentIndex = next(shift);
        current = players.get(currentIndex);
    }

    /**
     * Gets a list of valid colors.
     */
    public ArrayList<Color> validColors() {
        ArrayList<Color> re = new ArrayList<>(Arrays.asList(Color.values()));
        re.remove(Color.BLACK);
        return re;
    }

    /**
     * Resets this game back to its initial values.
     */
    public void reset() {
        order = 1;
        deck.reset();
        for (Player p : players) {
            p.reset();
            for (int i = 0; i < startingHand; i++)
                p.addCard(deck.draw());
        }
        top = deck.draw();
        currentIndex = 0;
        current = players.get(currentIndex);
    }

    /**
     * Returns the deck in this game.
     *
     * @return deck in this game.
     */
    public Deck getDeck() {return deck;}

    /**
     * Returns the number of players in this game.
     *
     * @return number of players in this game
     */
    public ArrayList<Player> getPlayers() {return players;}

    /**
     * Returns the turn order in this game.
     *
     * @return turn order in this game.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order within this game.
     *
     * @param order new order.
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Monte Carlo Simulation.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        UNO game = new UNO();
        int sims = 100;
        int turns = 0;
        int tmp;
        for (int i = 0; i < sims; i++) {
            tmp = game.run();
            turns += tmp;
            System.out.printf("\tgame %d had %d turns.\n", i + 1, tmp);
            game.reset();
        }
        System.out.printf("\nThe average amount of turns among %d games is %d!\n", sims, turns / sims);
    }
}
