package JavaDemos.Java2.ch8;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The classic Game of Fifteen. A 4x4 grid contains 15 numbered tiles and an empty
 * space. A move in the game consists of sliding a tile into the empty space. The
 * goal is to rearrange the tiles into sorted order with the empty space in the
 * lower right corner.
 *
 * @author Jacob Swineford
 */
public class Fifteen {

    public static final int ROWS = 4;
    public static final int COLS = 4;
    public static final int EMPTY_SPACE = 0;

    private static final int DEFAULT_SHUFFLE_COUNT = 50;
    private int[][] grid;

    /**
     * Initializes the grid to a random solvable configuration.
     */
    public Fifteen() {
        this(DEFAULT_SHUFFLE_COUNT);
    }

    /**
     * Initializes the grid to a random configuration that can be solved a specified
     * number of moves.
     *
     * @param n an upper bound on the number of moves needed to solve the puzzle
     */
    public Fifteen(int n) {
        grid = new int[ROWS][COLS];

        // First put the grid into the winning configuration and then make a
        // series of legal ove sat random locations to shuffle it.
        int tile = 1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = tile++;
            }
        }
        grid[ROWS - 1][COLS - 1] = EMPTY_SPACE;
        shuffle(n);
    }

    /**
     * Returns the current grid configuration as a string.
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == EMPTY_SPACE) {
                    s += "   ";
                } else {
                    s += String.format("%02d ", grid[i][j]);
                }
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Shuffles the grid by making a series of random moves/
     * @param numMoves the number of moves
     */
    private void shuffle(int numMoves) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (numMoves > 0) {
            int tileToMove = 1 + rand.nextInt(ROWS * COLS - 1);
            boolean tileMoved = slide(tileToMove);
            if (tileMoved) {
                numMoves--;
            }
        }
    }

    /**
     * Moves a tile into the adjacent empty square. Invalid moves are ignored.
     *
     * @param tile the tile to move
     * @return true if the move is valid, false otherwise.
     */
    public boolean slide(int tile) {
        if (tile < 1 || tile >= ROWS * COLS) {
            return false;
        }

        // Find the position of the tile to slide and of the empty space
        int tileRow = 0;
        int tileCol = 0;
        int spaceRow = 0;
        int spaceCol = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == tile) {
                    tileRow = i;
                    tileCol = j;
                }
                if (grid[i][j] == EMPTY_SPACE) {
                    spaceRow = i;
                    spaceCol = j;
                }
            }
        }

        // if the tile is next to the empty space, swap them. If they are next
        // to each other, the rows are the same and the columns differ by one, or
        // vice versa
        if (Math.abs(tileRow - spaceRow) + Math.abs(tileCol - spaceCol) == 1) {
            grid[tileRow][tileCol] = EMPTY_SPACE;
            grid[spaceRow][spaceCol] = tile;
            return true;
        }
        return false;
    }

    /**
     * Returns true if the game is in the winning configuration/
     */
    public boolean over() {
        int tile = 1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                if (i == ROWS - 1 && j == COLS - 1) {
                    return true;
                }
                if (grid[i][j] != tile) {
                    return false;
                }
                tile++;
            }
        }
        return true;
    }

    /**
     * Returns the tile and a specified position.
     */
    public int tileAt(int row, int col) {
        return grid[row][col];
    }
}

