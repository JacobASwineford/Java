package Misc.CustomClasses.Monopoly;

import Misc.CustomClasses.Monopoly.Enum.EventCard;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Provides a shuffle-able list of Community Chest and Chance spaces.
 *
 * @author Jacob Swineford
 */
public class EventDeck {

    private ArrayList<EventCard> communityChestDeck = new ArrayList<>();
    private ArrayList<EventCard> chanceDeck = new ArrayList<>();

    /**
     * Initializes both decks and shuffles them.
     */
    public EventDeck() {
        iterateCC();
        iterateChance();
        shuffleCC();
        shuffleChance();
    }

    /**
     * Shuffles the current deck via the Fisher-Yate method.
     */
    private void shuffleCC() {
        for (int i = 0; i < communityChestDeck.size(); i++) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            int r = rand.nextInt(communityChestDeck.size() - i) + i;
            EventCard t = communityChestDeck.get(i);
            EventCard g = communityChestDeck.get(r);
            communityChestDeck.remove(i);
            communityChestDeck.add(i, g);
            communityChestDeck.remove(r);
            communityChestDeck.add(r, t);
        }
    }

    /**
     * Shuffles the current deck via the Fisher-Yate method.
     */
    private void shuffleChance() {
        for (int i = 0; i < chanceDeck.size(); i++) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            int r = rand.nextInt(chanceDeck.size() - i) + i;
            EventCard t = chanceDeck.get(i);
            EventCard g = chanceDeck.get(r);
            chanceDeck.remove(i);
            chanceDeck.add(i, g);
            chanceDeck.remove(r);
            chanceDeck.add(r, t);
        }
    }

    public EventCard drawFromCommunityChest() {
        EventCard e = communityChestDeck.get(0);
        communityChestDeck.remove(0);
        return e;
    }

    public EventCard drawFromChance() {
        EventCard e = communityChestDeck.get(0);
        communityChestDeck.remove(0);
        return e;
    }

    /**
     * Reloads the Community Chest deck and shuffles it.
     */
    public void reloadCC() {
        communityChestDeck.clear();
        iterateCC();
        shuffleCC();
    }

    /**
     * Reloads the Chance deck and shuffles it.
     */
    public void reloadChance() {
        chanceDeck.clear();
        iterateChance();
        shuffleChance();
    }

    private void iterateCC() {
        for (EventCard e : EventCard.values()) {
            if (e.getIndex() == 0) {
                communityChestDeck.add(e);
            }
            if (e.getIndex() == 2) {
                communityChestDeck.add(e);
            }
        }
    }

    private void iterateChance() {
        for (EventCard e : EventCard.values()) {
            if (e.getIndex() == 1) {
                chanceDeck.add(e);
            }
            if (e.getIndex() == 2) {
                chanceDeck.add(e);
            }
        }
    }
}