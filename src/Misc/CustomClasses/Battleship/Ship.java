package Misc.CustomClasses.Battleship;

import javafx.scene.paint.Color;

/**
 * A ship in the game of Battleship. This ship may have a set length,
 * a name, a rotation (vertical or horizontal) and a color associated
 * with it.
 *
 * @author Jacob Swineford
 */
class Ship {

    private int length;
    private String name;
    private boolean vertical;
    private Color color;

    Ship(String name, int length, Color color) {
        this.name = name;
        this.length = length;
        this.color = color;
        vertical = false;
    }

    int getLength() {
        return length;
    }

    String getName() {
        return name;
    }

    boolean isVertical() {
        return vertical;
    }

    Color getColor() {return color;}

    void rotate() {
        vertical = !vertical;
    }
}
