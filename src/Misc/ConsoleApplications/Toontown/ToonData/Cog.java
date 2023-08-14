package Misc.ConsoleApplications.Toontown.ToonData;

import Misc.ConsoleApplications.Toontown.ToonEnumerations.CogBase;

/**
 * Represents a cog in the game of Toontown.
 */
public class Cog {

    // cog information
    private final CogBase base;

    // battle information
    private final int level;
    private int health;

    public Cog(CogBase base, int level) {
        this.base = base;
        this.level = level;

        // level determines health
        if (level <= 11)
            health = (level + 1) * (level + 2);
        else
            health = (level + 1) * (level + 2) + 14;
    }

    /**
     * Returns the base information of this cog.
     *
     * @return base information
     */
    public CogBase getBase() {
        return base;
    }

    /**
     * Returns the level of this cog.
     *
     * @return cog
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the current health of the cog.
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Damages this cog by the given value.
     */
    public void damage(int value) {
        health -= value;

        // if health is less than zero, set it to 0
        if (health < 0)
            health = 0;
    }
}
