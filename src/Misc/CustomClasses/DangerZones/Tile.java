package Misc.CustomClasses.DangerZones;

/**
 * @author Jacob Swineford
 */
public class Tile {

    private DZPlayer player;
    private int value;

    public Tile(DZPlayer p, int v){player=p; value=v;}

    public DZPlayer getPlayer() {
        return player;
    }

    public void setPlayer(DZPlayer player) {
        this.player = player;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
