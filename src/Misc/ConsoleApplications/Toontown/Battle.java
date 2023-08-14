package Misc.ConsoleApplications.Toontown;

import Misc.ConsoleApplications.Toontown.ToonData.Cog;
import Misc.ConsoleApplications.Toontown.ToonData.Toon;

import java.util.LinkedList;

/**
 * Represents a battle in the game of Toontown. A battle consists of a
 * set of toons fighting a set of cogs.
 */
public class Battle {

    private final LinkedList<Toon> toons;
    private final LinkedList<Cog> cogs;

    public Battle() {
        toons = new LinkedList<>();
        cogs = new LinkedList<>();
    }

    /**
     * Adds the given toon into this battle.
     *
     * @param toon given toon
     */
    public void addToon(Toon toon) {
        toons.add(toon);
    }

    /**
     * Removes the given toon from this battle.
     *
     * @param toon given toon
     */
    public void removeToon(Toon toon) {
        toons.remove(toon);
    }

    /**
     * Adds the given cog into this battle.
     *
     * @param cog given cog
     */
    public void addCog(Cog cog) {
        cogs.add(cog);
    }

    /**
     * Removes the given cog from this battle.
     *
     * @param cog given cog
     */
    public void removeCog(Cog cog) {
        cogs.remove(cog);
    }
}
