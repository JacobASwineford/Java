package Misc.ConsoleApplications.Game.Areas;

import Misc.ConsoleApplications.Game.Enemies.Rabbit;

/**
 * @author Jacob Swineford
 */
public class MeadowEast extends Area {

    public MeadowEast() {
        name = "Meadow - East";
        enemies.add(new Rabbit());
    }
}
