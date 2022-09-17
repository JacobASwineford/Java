package Misc.CustomClasses.Monopoly.Spaces;

import Misc.CustomClasses.Monopoly.Player;

/**
 * A space that a player lands on in the game of monopoly.
 * Each space has an event that happens when a player lands
 * on it.
 *
 * @author Jacob Swineford
 */
public interface Space {

    /**
     * For spaces that provide an event that is automatic.
     */
    void doAutomaticEvent(Player eventTarget);
}
