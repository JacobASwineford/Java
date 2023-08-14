package Misc.ConsoleApplications.Toontown.ToonData;

import Misc.ConsoleApplications.Toontown.ToonEnumerations.GagBase;
import Misc.ConsoleApplications.Toontown.ToonEnumerations.GagTrack;

import java.util.Objects;

/**
 * Represents a gag in the game of Toontown.
 */
public class Gag {

    private final GagBase base;

    private int damage;
    private int accuracy;

    public Gag(GagBase base, boolean isOrganic) {
        this.base = base;

        // if the gag is organic, then change base damage
        if (isOrganic)
            damage = base.getOrganicMinimumDamage();
        else
            damage = base.getBaseMinimumDamage();

        // if the gag is lure and organic, then change base accuracy to medium (+10)
        if (isOrganic && base.getTrack() == GagTrack.Lure)
            accuracy = base.getLureOrganicAccuracy();
        else
            accuracy = base.getBaseAccuracy();
    }

    /**
     * Returns the base information of this gag.
     *
     * @return base information
     */
    public GagBase getBase() {
        return base;
    }

    /**
     * Returns the current damage of this gag.
     *
     * @return current damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the current accuracy of this gag.
     *
     * @return current accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Assigns additional damage to this gag according to the toon that is using it.
     */
    public void assignAdditional(Toon toon) {
        int needed = base.getNeededSkillPoints();
        int afterNeeded;
        if (base.after() != null)
            afterNeeded = Objects.requireNonNull(base.after()).getNeededSkillPoints(); // I don't know why this is required
        else
            return;
        int min = base.getBaseMinimumDamage();
        int max = base.getBaseMaximumDamage();
        int per = (afterNeeded - needed) / (max - min);
        damage += (toon.getTrackProgress(base.getTrack()) - needed) / per;
    }

    /**
     * Overridden toString method.
     *
     * @return string representation of this gag
     */
    @Override
    public String toString() {
        return this.base.toString();
    }

    /**
     * Overridden equals method.
     *
     * @param obj gag to compare to
     *
     * @return comparison
     */
    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Gag;
        return this.base.toString().equals(((Gag) obj).getBase().toString());
    }
}
