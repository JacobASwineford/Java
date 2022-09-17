package Misc.CustomClasses.Checkers;

import javafx.geometry.Point2D;
import java.util.ArrayList;

/**
 * @author Jacob Swineford
 */
public class Checkers2{

    private int[][] board;

    private Checkers2(int length) throws Exception {
        if (length % 2 != 0 || length < 7) throw new Exception();
        board = new int[length][length];
        setPieces();
    }

    private void setPieces() {
        int length = board.length;
        int unitRows = (length - 2) / 2;
        int stagger = 1;
        for (int i = 0; i < unitRows; i++) {
            for (int j = 0; j < length / 2; j++) {
                if (stagger == 1) board[i][(j*2)+1] = 1;
                else board[i][j*2] = 1;
            }
            stagger = -stagger;
        }

        if (unitRows % 2 == 0) stagger = 1;
        else stagger = -1;
        for (int i = unitRows + 2; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                if (stagger == 1) board[i][(j*2)+1] = 2;
                else board[i][j*2] = 2;
            }
            stagger = -stagger;
        }
    }

    private void printBoard() {
        for (int[] arr : board) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * @return 1 if a piece was moved, 2 if a piece was attacked,
     *         0 if the selected space was not valid
     */
    private int move(int sx, int sy, int ex, int ey) {
        ArrayList<Point2D> moves = validMoves(sx, sy);
        ArrayList<Point2D> attacks = validAttacks(sx, sy);
        Point2D end = new Point2D(ex, ey);
        if (moves.contains(end)) {
            board[ey][ex] = board[sy][sx];
            board[sy][sx] = 0;
            return 1;
        }
        if (attacks.contains(end)) {
            attack(sx, sy, ex, ey);
            return 2;
        }
        return 0;
    }

    private void attack(int sx, int sy, int ex, int ey) {
        int xd = ex - sx;
        int yd = ey - sy;
        int x = 0; int y = 0;
        if (xd == -2 && yd == -2) {x = sx-1; y = sy-1;}
        if (xd == 2 && yd == -2) {x = sx+1; y = sy-1;}
        if (xd == 2 && yd == 2) {x = sx+1; y = sy+1;}
        if (xd == -2 && yd == 2) {x = sx-1; y = sy+1;}
        board[ey][ex] = board[sy][sx];
        board[sy][sx] = 0;
        board[y][x] = 0;
    }

    private boolean withinRange(int x, int y) {
        return y >= 0 && y < board.length && x >= 0 && x < board.length;
    }

    private boolean isEmpty(int x, int y) {return board[y][x] == 0;}

    private ArrayList<Point2D> validMoves(int x, int y) {
        ArrayList<Point2D> arr = new ArrayList<>();
        if (!withinRange(x, y)) return arr; // empty list
        int pieceType = board[y][x];
        if (pieceType == 1) {
            if (withinRange(x-1, y+1) && isEmpty(x-1,y+1)) arr.add(new Point2D(x-1,y+1));
            if (withinRange(x+1, y+1) && isEmpty(x+1,y+1)) arr.add(new Point2D(x+1,y+1));
        } else if (pieceType == 2) {
            if (withinRange(x-1, y-1) && isEmpty(x-1,y-1)) arr.add(new Point2D(x-1,y-1));
            if (withinRange(x+1, y-1) && isEmpty(x+1,y-1)) arr.add(new Point2D(x+1,y-1));
        } else if (pieceType == 3 || pieceType == 4) {
            if (withinRange(x-1, y+1) && isEmpty(x-1,y+1)) arr.add(new Point2D(x-1,y+1));
            if (withinRange(x+1, y+1) && isEmpty(x+1,y+1)) arr.add(new Point2D(x+1,y+1));
            if (withinRange(x-1, y-1) && isEmpty(x-1,y-1)) arr.add(new Point2D(x-1,y-1));
            if (withinRange(x+1, y-1) && isEmpty(x+1,y-1)) arr.add(new Point2D(x+1,y-1));
        }
        return arr;
    }

    private ArrayList<Point2D> validAttacks(int x, int y) {
        ArrayList<Point2D> arr = new ArrayList<>();
        if (!withinRange(x, y)) return arr; // empty list
        int pieceType = board[y][x];
        if (pieceType == 1 || pieceType == 3) {
            if (withinRange(x-2,y-2) && board[y-1][x-1] % 2 == 0 && board[y-1][x-1]  != 0 && isEmpty(x-2,y-2)) arr.add(new Point2D(x-2,y-2));
            if (withinRange(x+2,y-2) && board[y-1][x+1] % 2 == 0 && board[y-1][x+1]  != 0 && isEmpty(x+2,y-2)) arr.add(new Point2D(x+2,y-2));
            if (withinRange(x+2,y+2) && board[y+1][x+1] % 2 == 0 && board[y+1][x+1]  != 0 && isEmpty(x+2,y+2)) arr.add(new Point2D(x+2,y+2));
            if (withinRange(x-2,y+2) && board[y+1][x-1] % 2 == 0 && board[y+1][x-1]  != 0 && isEmpty(x-2,y+2)) arr.add(new Point2D(x-2,y+2));
        }
        if (pieceType == 2 || pieceType == 4) {
            if (withinRange(x-2,y-2) && board[y-1][x-1] % 2 == 1 && isEmpty(x-2,y-2)) arr.add(new Point2D(x-2,y-2));
            if (withinRange(x+2,y-2) && board[y-1][x+1] % 2 == 1 && isEmpty(x+2,y-2)) arr.add(new Point2D(x+2,y-2));
            if (withinRange(x+2,y+2) && board[y+1][x+1] % 2 == 1 && isEmpty(x+2,y+2)) arr.add(new Point2D(x+2,y+2));
            if (withinRange(x-2,y+2) && board[y+1][x-1] % 2 == 1 && isEmpty(x-2,y+2)) arr.add(new Point2D(x-2,y+2));
        }
        return arr;
    }

    public static void main(String[] args) throws Exception {
        Checkers2 c = new Checkers2(8);
        c.printBoard();
        System.out.println(c.move(1, 2, 2, 3));
        c.printBoard();
    }
}
