package Misc.ConsoleApplications.Game.Enemies;

/**
 * @author Jacob Swineford
 */
public class Enemy {

    String name;
    double hp;
    double defense;
    double attack;

    private int mode;
    public static int ROAMING = 0;
    public static int ATTACK = 1;

    Enemy() {
        mode = ROAMING;
    }

    public String getName() {return name;}
    public double getHp() {return hp;}
    public double getDefense() {return defense;}
    public double getAttack() {return attack;}
    public int getMode() {return mode;}


}
