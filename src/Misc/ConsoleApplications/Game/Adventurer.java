package Misc.ConsoleApplications.Game;

import Misc.ConsoleApplications.Game.Areas.Area;
import Misc.ConsoleApplications.Game.Classes.Class;
import Misc.ConsoleApplications.Game.Equipment.Charms.Charm;
import Misc.ConsoleApplications.Game.Equipment.Equipment;
import Misc.ConsoleApplications.Game.Equipment.Gloves.Gloves;
import Misc.ConsoleApplications.Game.Equipment.Hats.Hat;
import Misc.ConsoleApplications.Game.Equipment.Shields.Shield;
import Misc.ConsoleApplications.Game.Equipment.Shirts.Shirt;
import Misc.ConsoleApplications.Game.Equipment.Trousers.Trousers;
import Misc.ConsoleApplications.Game.Equipment.Weapons.Weapon;

/**
 * The player character in this text-based game.
 * A character has a class, a level, equipment, currency,
 * and has the ability to execute commands.
 *
 * COMMANDS:
 *  fight (enemy) - attacks with weapon using the attack stat
 *  run - runs away from a fight (must be in a fight)
 *  give up - surrender to the enemy, leaving some money and
 *            returning to the nearest safe area
 *  equip (equipment name) - equips a piece of equipment, if
 *                           it is present in the inventory
 *  dequip (equipment name) - dequips a piece of equipment, if
 *                            it is currently equipped
 *  check - check the current area for NPCs, enemies, and different
 *          objects the character can interact with
 *  talk (name) - talks to the NPC or enemy with the specified name
 *  move (area name) - moves the character to the specified area
 *  give (item name) (name) - gives the specified item to the specified
 *                            person
 *  take (item name) - takes an item available to the player within their
 *                     area. if multiple of this item are present, then
 *                     all of the items will be taken
 *
 * @author Jacob Swineford
 */
public class Adventurer {

    private String name;

    private Hat hat;
    private Shirt shirt;
    private Gloves gloves;
    private Trousers trousers;
    private Charm charm;
    private Shield shield;
    private Weapon weapon;

    private Class cls;
    private double money;
    private Area in;

    public Adventurer(String name, Class cls) {
        this.name = name;
        this.cls = cls;
        money = 0;
    }

    public void equip(Equipment e) {
        String ename = e.getClass().getInterfaces()[0].getSimpleName();
        switch (ename) {
            case "Weapon": weapon = (Weapon) e; break;
            case "Shield": shield = (Shield) e; break;
            case "Charm": charm = (Charm) e; break;
            case "Trousers": trousers = (Trousers) e; break;
            case "Gloves": gloves = (Gloves) e; break;
            case "Shirt": shirt = (Shirt) e; break;
            case "Hat": hat = (Hat) e; break;
            default:
                System.out.println("Equipment not recognized.");
        }
    }

    public void dequip(String ename) {
        switch (ename) {
            case "Weapon": weapon = null; break;
            case "Shield": shield = null; break;
            case "Charm": charm = null; break;
            case "Trousers": trousers = null; break;
            case "Gloves": gloves = null; break;
            case "Shirt": shirt = null; break;
            case "Hat": hat = null;
            default:
                System.out.println("Equipment type not recognized.");
        }
    }

    public String getName() {
        return name;
    }

    public Hat getHat() {
        return hat;
    }

    public Shirt getShirt() {
        return shirt;
    }

    public Gloves getGloves() {
        return gloves;
    }

    public Trousers getTrousers() {
        return trousers;
    }

    public Charm getCharm() {
        return charm;
    }

    public Shield getShield() {
        return shield;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setIn(Area to) {in = to;}
    public Area getIn() {return in;}
}
