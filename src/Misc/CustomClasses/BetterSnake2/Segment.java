package Misc.CustomClasses.BetterSnake2;

/**
 * Segment of a snake in the game of snake. Segments have positions in the board
 * that are saved for future use in growing and movement.
 *
 * @author Jacob Swineford
 */
class Segment {
    private int x;
    private int y;

    Segment(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {return x;}
    int getY() {return y;}
    void setX(int x) {this.x = x;}
    void setY(int y) {this.y = y;}
}
