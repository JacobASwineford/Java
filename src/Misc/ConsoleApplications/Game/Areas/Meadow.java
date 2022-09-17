package Misc.ConsoleApplications.Game.Areas;

import Misc.ConsoleApplications.Game.Enemies.Slime;

/**
 * The meadow, the starting area for any adventurer.
 *
 * @author Jacob Swineford
 */
public class Meadow extends Area {

    public Meadow() {
        name = "Meadow - West";
        enemies.add(new Slime());
    }
}
