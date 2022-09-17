package Misc.CustomClasses.DangerZones;

/**
 * @author Jacob Swineford
 */
public class DZPlayer {

    private int score;
    private int id;

    public DZPlayer(int id)
    {
        this.id = id;
        score = 0;
    }

    public void addScore(int amount) {score+=amount;}
    public void subtractScore(int amount) {score-=amount;}
    public int getScore() {return score;}
    public int getId() {return id;}
    public void setId(int i) {id = i;}

}
