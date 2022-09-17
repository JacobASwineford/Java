package Misc.ConsoleApplications.Game.Equipment.Charms;

import Misc.ConsoleApplications.Game.Equipment.Equipment;

/**
 * @author Jacob Swineford
 */
public class CrappyCharm extends Equipment implements Charm {

    public CrappyCharm() {
        name = "Crappy Charm";
        attack = 0;
        defense = 0;
    }

    // execute effect somehow - built it in for when a thing attacks
}
