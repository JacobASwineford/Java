package Misc.ConsoleApplications.Toontown.ToonData;

import Misc.ConsoleApplications.Toontown.ToonEnumerations.GagBase;
import Misc.ConsoleApplications.Toontown.ToonEnumerations.GagTrack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Represents a toon in the game of Toontown.
 */
public class Toon {

    // information about the toon
    private final String name;
    private int[] trackProgress;

    // battle information
    private int maxLaff;
    private int currentLaff;
    private final LinkedList<Gag> gags;
    private final int[] gagCapacity;
    private Gag chosenGag;

    public Toon(String name, int maxLaff) {
        this.name = name;
        this.maxLaff = maxLaff;
        currentLaff = maxLaff;
        gags = new LinkedList<>();
        gagCapacity = new int[GagBase.values().length];
        this.chosenGag = null;
    }

    /**
     * Gets the name of this toon.
     *
     * @return name of toon
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns a toons track progress.
     */
    public void assignProgress(int[] trackProgress) {
        this.trackProgress = trackProgress;
    }

    /**
     * Gets the track progress of this toon, based on the given track.
     *
     * @param track given track
     * @return track progress
     */
    public int getTrackProgress(GagTrack track) {
        return trackProgress[track.getProgressIndex()];
    }

    /**
     * Gets the current laff of this toon.
     *
     * @return current laff of toon
     */
    public int getCurrentLaff() {
        return currentLaff;
    }

    /**
     * Adds the given value to the current laff of this toon.
     *
     * @param value given value
     */
    public void addLaff(int value) {
        currentLaff += value;

        // if current laff is more than max, set it to max
        if (currentLaff > maxLaff)
            currentLaff = maxLaff;
    }

    /**
     * Subtracts the given value from the current laff of this toon.
     *
     * @param value given value
     */
    public void subtractLaff(int value) {
        currentLaff -= value;

        // if current laff is less than 0, set it to 0
        if (currentLaff < 0)
            currentLaff = 0;
    }

    /**
     * Increases the max laff of this toon by the given value.
     *
     * @param value given value
     */
    public void increaseMaxLaff(int value) {
        maxLaff += value;
        currentLaff = maxLaff; // full heal after max increase
    }

    /**
     * Returns the gag pool of this toon.
     *
     * @return gag pool
     */
    public LinkedList<Gag> getGags() {
        return gags;
    }

    /**
     * Adds the given gag to this toons gag list.
     *
     * @param gag given gags
     */
    public void addGag(Gag gag) {
        this.gags.add(gag);
        gagCapacity[gag.getBase().ordinal()]++;
    }

    /**
     * Removes the given gag from this toons gag list.
     *
     * @param gag given gag
     */
    public void removeGag(Gag gag) {
        gags.remove(gag);
        gagCapacity[gag.getBase().ordinal()]--;
    }

    /**
     * Chooses the given gag for use in a battle.
     */
    public void chooseGag(Gag gag) {
        chosenGag = gag;
    }

    /**
     * Un-chooses the currently chosen gag.
     */
    public void UnchooseGag() {
        chosenGag = null;
    }

    /**
     * Returns the chosen gag for this toon.
     *
     * @return chosen gag
     */
    public Gag getChosenGag() {
        return chosenGag;
    }

    /**
     * Gets the unique gags from this toons gag pool.
     *
     * @return unique gags
     */
    public LinkedList<Gag> getUniqueGags() {
        LinkedList<Gag> re = new LinkedList<>();
        for (Gag g : gags)
            if (!re.contains(g))
                re.add(g);
        return re;
    }

    /**
     * Gets the count of the chosen gag from this toons
     * gag list.
     *
     * @param gag given gag
     * @return gag count
     */
    public int getGagCount(Gag gag) {
        int c = 0;
        for (Gag g : gags) {
            if (g.equals(gag))
                c++;
        }
        return c;
    }
}
