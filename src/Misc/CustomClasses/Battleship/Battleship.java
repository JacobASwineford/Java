package Misc.CustomClasses.Battleship;

import java.util.LinkedList;

/**
 * Engine for the game of Battleship. Battleship is a two player game
 * where two players take turns trying to shoot down the opposing
 * players' battleships. This is done by having each player take turns
 * guessing a spot on a two-dimensional board where they think the enemy
 * battleships are, and if there is an opposing battleship there it is
 * considered a hit on one part of that ship. if a ship is hit in every
 * location it is placed, it is sunk. A player wins when all the opposing
 * battleships have sunk.
 *
 * @author Jacob Swineford
 */
class Battleship {

    // guessed coordinates for each player
    private LinkedList<int[]> correct1;
    private LinkedList<int[]> incorrect1;

    private LinkedList<int[]> correct2;
    private LinkedList<int[]> incorrect2;

    // ship boards for each player
    private Ship[][] board1;
    private Ship[][] board2;

    /**
     * Constructor for the game of Battleship.
     *
     * @param width width of the board
     * @param height height of the board
     */
    Battleship(int width, int height) {
        board1 = new Ship[height][width];
        board2 = new Ship[height][width];
        correct1 = new LinkedList<>();
        correct2 = new LinkedList<>();
        incorrect1 = new LinkedList<>();
        incorrect2 = new LinkedList<>();
    }

    /**
     * Determines if a ship can be placed in the specified location.
     *
     * @param player player that is placing the ship
     * @param ship ship to use for testing
     * @param x x-coordinate
     * @param y y-coordinate
     *
     * @return The list of coordinates associated with a ship's placement,
     *         null otherwise
     */
    LinkedList<int[]> canPlace(int player, Ship ship, int x, int y) {
        Ship[][] arr;
        if (player == 1) arr = board1;
        else arr = board2;

        LinkedList<int[]> coor = new LinkedList<>();
        int l = ship.getLength();
        try {
            while (l != 0) {
                if (arr[y][x] != null) return null;
                coor.add(new int[] {x, y});
                if (ship.isVertical()) {y++;}
                else x++;
                l--;
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return coor;
    }

    /**
     * Places ships in the locations specified by the given
     * <code>LinkedList</code>. Those ships are all produced by
     * the given shipyard, and thus they are independent.
     *
     * @param player player placing a ship on their field
     * @param shipyard shipyard to produce ships of a particular type
     * @param coor coordinates to place ships at
     */
    void place(int player, GUI.Shipyard shipyard, LinkedList<int[]> coor) {
        Ship[][] working;
        if (player == 1) working = board1;
        else working = board2;

        for (int[] arr : coor) {
            int x = arr[0];
            int y = arr[1];
            working[y][x] = shipyard.produce(); // ehh
        }
    }

    LinkedList<int[]> getCorrect1() {return correct1;}
    LinkedList<int[]> getCorrect2() {return correct2;}
    LinkedList<int[]> getIncorrect1() {return incorrect1;}
    LinkedList<int[]> getIncorrect2() {return incorrect2;}
}
