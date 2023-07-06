package Misc.CustomClasses.Uno;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private Deck deck;
    private Card cur;

    private static final int DEFAULT_HANDSIZE = 7;

    // players
    private final LinkedList<Hand> players;
    private int player;
    private int order = 1;

    Game(int players) {
        deck = new Deck();
        cur = deck.draw();
        this.players = new LinkedList<>();
        for (int i = 0; i < players; i++) {
            Hand h = new Hand();
            this.players.add(h);
            for (int j = 0; j < DEFAULT_HANDSIZE; j++)
                h.addCard(deck.draw());
        }

        player = 0;
    }

    /**
     * Runs the simulation, and returns the number of turns that were taken before the game was completed.
     *
     * @return the number of turns the simulation took.
     */
    public int run() {
        int turns = 0;
        while (true) {
            turns++;
            //System.out.printf("{%d} It's %d's turn! (hand size: %d, turn: %d)\n",game, player, players.get(player).getCards().size(), turns);
//            try {
//                Thread.sleep(50);
//            } catch (Exception e) {
//                System.out.println("Oopsies!\n");
//                break;
//            }

            // find what cards can be played. if no cards can currently
            // be played, then can are drawn until one is found than can
            // be played.
            Hand curHand = players.get(player);
            LinkedList<Card> canPlay;
            while ((canPlay = canPlay(curHand)).size() == 0) {
                if (deck.present() == 0)
                    return turns;
                curHand.addCard(deck.draw());
            }


            Card hp = highestPrecedence(canPlay);
            //System.out.printf("Player %d plays a %s!\n", player, hp);

            // resolve the effects of the selected card.
            boolean over = play(hp);

            // if the previous player has no cards, the game is over.
            if (over) break;
        }

        return turns;
    }

    /**
     * Resets this game to its initial values.
     */
    public void reset() {
        deck.reset();
        cur = deck.draw();
        for (Hand h : players) {
            h.getCards().clear();
            for (int i = 0; i < DEFAULT_HANDSIZE; i++)
                h.addCard(deck.draw());
        }
        player = 0;
    }

    /**
     * Compiles a list of cards that can be played, and returns them as a list.
     *
     * @param hand given hand to search through
     * @return list of cards that can be played.
     */
    private LinkedList<Card> canPlay(Hand hand) {
        LinkedList<Card> cards = new LinkedList<>();
        LinkedList<Card> handCards = hand.getCards();
        Card handCard;
        for (Card card : handCards) {
            handCard = card;
            if (handCard.getColor() == cur.getColor() || handCard.getColor() == Color.WILD) // colors match, wild
                cards.add(handCard);
            else if (handCard.getEffect() == cur.getEffect()) // effects match
                cards.add(handCard);
            else if (cur.getValue() != Card.NO_VALUE && cur.getValue() == handCard.getValue()) // values match, special values don't count
                cards.add(handCard);
            else if (cur.getDrawValue() == handCard.getDrawValue()) // draw values match
                cards.add(handCard);
        }
        return cards;
    }

    /**
     * Gets and returns the card with the highest precedence.
     * If there are multiple cards with the same amount of precedence,
     * the first card with that precedence is returned.
     *
     * @param cards set of cards to search through
     * @return the card with the highest precedence.
     */
    private Card highestPrecedence(LinkedList<Card> cards) {
        int h = -100; // for lack of internet and getting the lowest integer value.
        Card re = null;
        for (Card card : cards) {
            if (card.getPrecedence().getPrecedenceValue() > h) {
                re = card;
                h = card.getPrecedence().getPrecedenceValue();
            }
        }
        return re;
    }

    /**
     * Plays the given cards, and resolves its effects. Then, it updates the
     * game accordingly.
     *
     * @param card given card to play
     * @return whether the current player has 0 cards or not, after playing
     */
    private boolean play(Card card) {
        boolean over = false;

        // handle reverse effects
        if (card.getEffect() == Effect.REVERSE)
            order = -order;
        int nextIndex = (player + order + players.size()) % players.size();

        // handle skip effects
        if (card.getEffect() == Effect.SKIP) {
            deck.discard(cur);
            cur = card;
            players.get(player).removeCard(card);
            over = players.get(player).getCards().size() == 0;
            player = nextIndex;
            return over;
        }

        // handle draw effects
        if (deck.present() < card.getDrawValue())
            return true;
        for (int i = 0; i < card.getDrawValue(); i++)
            players.get(nextIndex).addCard(deck.draw());


        // handle wild effects
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        LinkedList<Color> validColors = validColors();
        if (card.getColor() == Color.WILD)
            card.setColor(validColors.get(rand.nextInt(0, validColors.size())));

        // handle normal effects, changing cur and player
        players.get(player).removeCard(card);
        deck.discard(cur);
        cur = card;
        over = players.get(player).getCards().size() == 0;
        player = nextIndex;

        return over;
    }

    /**
     * Returns the colors that are currently valid for handling wild cards.
     * This may be expanded in the future.
     *
     * @return list of valid colors.
     */
    private LinkedList<Color> validColors() {
        LinkedList<Color> colors = new LinkedList<>(Arrays.asList(Color.values()));
        colors.remove(Color.WILD);
        return colors;
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        int[] turns = new int[20];
        int av = 0;
        for (int i = 0; i < turns.length; i++) {
            turns[i] = game.run();
            av += turns[i];
            game.reset();
        }

        for (int i = 0; i < turns.length; i++) {
                System.out.printf("Game %d ran for %d turns.\n", i + 1, turns[i]);
        }
        av /= turns.length;
        System.out.printf("\nThe average length of a game of Uno is %d turns.\n", av);
    }
}
