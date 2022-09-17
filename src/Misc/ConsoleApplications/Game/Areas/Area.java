package Misc.ConsoleApplications.Game.Areas;

import Misc.ConsoleApplications.Game.Enemies.Enemy;
import Misc.ConsoleApplications.Game.NPCs.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Areas can contain pathways to different areas, and NPCs to interact with.
 * Enemies may also occasionally show up to fight you, and you may start combat with
 * enemies.
 *
 * @author Jacob Swineford
 */
public class Area {

    HashMap<String, Area> areas;
    LinkedList<NPC> NPCs;
    ArrayList<Enemy> enemies;
    String name;

    Area() {
        areas = new HashMap<>();
        NPCs = new LinkedList<>();
        enemies = new ArrayList<>();
    }

    // tools for building the area
    public void setAdjacent(String direction, Area area) {
        areas.put(direction, area);
    }

    public HashMap<String, Area> getAreas() {
        return areas;
    }

    public void setAreas(HashMap<String, Area> areas) {
        this.areas = areas;
    }

    public LinkedList<NPC> getNPCs() {
        return NPCs;
    }

    public void setNPCs(LinkedList<NPC> NPCs) {
        this.NPCs = NPCs;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
