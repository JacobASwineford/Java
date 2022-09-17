package Misc.CustomClasses.DangerZones;

import javafx.geometry.Point2D;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * An extended engine for a game in mario party.
 *
 * The original version of this game involves a 3x3
 * square with players 1, 2, 3, and 4 on the middle-most
 * northward, eastward, southward, and westward squares respectively.
 * the tiles in the corners and the middle contain points.
 * On a turn, players choose what tile to "jump" to, and if
 * two players pick the same tile then neither player gets the points
 * associated with that tile. Hence, they "bump" into each other.
 *
 * This class aims to simulate this game with extended features.
 * the board may be any size square, and there may be as many players
 * as the caller sees fit. Points in the squares will also be custom.
 *
 * @author Jacob Swineford
 */
public class DangerZones {

    private Tile[][] board;
    private DZPlayer[] players;

    public DangerZones()
    {
        board = new Tile[3][3];
        players = new DZPlayer[]
                {new DZPlayer(1), new DZPlayer(2),
                 new DZPlayer(3), new DZPlayer(4)};
        board[0][0] = new Tile(null, 1);
        board[0][1] = new Tile(players[0], 0);
        board[0][2] = new Tile(null, 1);
        board[1][0] = new Tile(players[3], 0);
        board[1][1] = new Tile(null, 3);
        board[1][2] = new Tile(players[1], 0);
        board[2][0] = new Tile(null, 1);
        board[2][1] = new Tile(players[2], 0);
        board[2][2] = new Tile(null, 1);
    }

    /**
     * Moves all the players at once, to the point of their choosing.
     * if two or more players pick the same point to jump to,
     * then neither players gets the points on that square. This method
     * only allows for players to jump to squares that are adjacent to them,
     * otherwise a GameViolation Exception is thrown. A GameViolation
     * Exception is also thrown if the given Point2D[] does not contain
     * movements for all players.
     *
     * This method will not change the state of the board, rather assign points
     * to players based on the spaces they choose.
     *
     * @param movement points for each respective player to jump to
     */
    public void move(Point2D[] movement) throws GameViolation
    {
        if (movement.length != players.length)
            throw new GameViolation("All players must move instantaneously.");
        if (containsInvalidMovements(movement))
            throw new GameViolation("One or more players cannot move to" +
                    " the space(s) selected.");
        removeDuplicates(movement); // turns invalid positions into null
        for (int i = 0; i < movement.length; i++)
        {
            if (movement[i] != null) players[i].addScore(
                    board[(int) movement[i].getY()][(int) movement[i].getX()].getValue());
        }
    }

    /**
     * Given the current state of the board, checks to see what would be a valid
     * place to move and returns the locations of those tiles in a linked list.
     * Currently, A player can move to any adjacent space, including one
     * occupied by another player.
     * @param id working player id
     */
    public LinkedList<Point2D> possibleMovementsFor(int id)
    {
        LinkedList<Point2D> l = new LinkedList<>();
        Point2D location = locationOf(id);
        int y = (int) location.getY();
        int x = (int) location.getX();
        if (validLocation(x,y-1)) l.add(new Point2D(x,y-1));
        if (validLocation(x+1,y)) l.add(new Point2D(x+1,y));
        if (validLocation(x,y+1)) l.add(new Point2D(x,y+1));
        if (validLocation(x-1,y)) l.add(new Point2D(x-1,y));
        return l;
    }

    /**
     * Returns the location of the given player on the board.
     * @param id working player id
     * @return location of the player
     */
    public Point2D locationOf(int id)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getPlayer() == null) continue;
                if (board[i][j].getPlayer().getId() == id) return new Point2D(j, i);
            }
        }
        return null;
    }

    public void print()
    {
        for (Tile[] arr : board)
        {
            for (Tile t : arr)
            {
                if (t.getPlayer()==null)
                    System.out.print("| " + t.getValue() + " |");
                else System.out.print("| P" + t.getPlayer().getId() + " |");
            }
            System.out.println();
        }
    }

    private boolean validLocation(int x, int y)
    {
        try {Tile t = board[y][x];}
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean containsInvalidMovements(Point2D[] movement)
    {
        for (Point2D p : movement)
        {
            if (!validLocation((int) p.getX(), (int) p.getY())) return true;
        }
        return false;
    }

    private static void removeDuplicates(Point2D[] movement)
    {
        HashSet<Integer> indexes = new HashSet<>();
        for (int i = 0; i < movement.length; i++)
        {
            Point2D cur = movement[i];
            for (int j = 0; j < movement.length; j++)
            {
                Point2D p = movement[j];
                if (cur.getX() == p.getX() && cur.getY() == p.getY() && i != j)
                {
                    indexes.add(i);
                    indexes.add(j);
                }
            }
        }
        for (int i : indexes) movement[i]=null;
    }

    public static void main(String[] args) {
        DangerZones dz = new DangerZones();
        dz.print();
        // test possible movements and remove duplicate methods
        Point2D[] m = new Point2D[] {new Point2D(1, 1), new Point2D(1, 2),
                                     new Point2D(1, 1), new Point2D(2, 2),
                                     new Point2D(1, 1), new Point2D(1, 1)};
        for (Point2D p : m) System.out.println(p.getX() + ", " + p.getY());
        removeDuplicates(m);
        System.out.println();
        for (Point2D p : m)
        {
            if (p == null)
            {
                System.out.println("NULL");
                continue;
            }
            System.out.println(p.getX() + ", " + p.getY());
        }
    }
}


