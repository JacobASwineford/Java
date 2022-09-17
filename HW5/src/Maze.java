import javafx.geometry.Point2D;
import java.util.ArrayList;

/**
 * A 2d maze that can have many solutions. The start of the maze
 * is represented by 'b', the walls by '1', empty space by '0',
 * and the end by 'e'.
 *
 * @author Jacob Swineford
 */
public class Maze {

    // board will be initialized by the caller
    private char[][] board;

    // dimensions of the board
    private int xDim;
    private int yDim;

    public Maze(char[][] board)
    {
        this.board=board;
        xDim=board[0].length;
        yDim=board.length;
    }

    /**
     * Outputs the solutions for this maze, if any. For a maze
     * to have a solution, they must have a valid beginning and
     * and ending position, as well as at least one valid path
     * between the two.
     *
     * When solving a maze for it's solutions, call this method
     * using the index of the beginning as the parameter.
     *
     * @param x x position
     * @param y y position
     */
    public void solve(int x, int y)
    {
        if (adjToEnd(x, y))
        {
            board[y][x] = 'p';
            System.out.println(this.toString());
            board[y][x] = '0';
        } else
            {
            for (Point2D p : validAdj(x, y))
            {
                if (board[y][x] != 'b')
                    board[y][x] = 'p';

                solve((int) p.getX(),(int) p.getY());
                board[y][x] = '0'; // backtrack over all iterations in loop
            }
        }
    }

    /**
     * Checks to see if the given space is next to 'e'.
     * Returns true if so, false otherwise.
     *
     * @param x x position
     * @param y y position
     */
    private boolean adjToEnd(int x, int y)
    {
        return isEnd(x, y - 1) || isEnd(x, y + 1) || isEnd(x - 1, y) || isEnd(x + 1, y);
    }

    /**
     * Checks to see if the given space is 'e'.
     * Returns true if so, false otherwise.
     *
     * @param x x position
     * @param y y position
     */
    private boolean isEnd(int x, int y)
    {
        return withinRange(x, y) && board[y][x] == 'e';
    }


    /**
     * Returns a list of Point2D objects detailing the valid adjacent
     * movements at a point in this maze.
     *
     * @param x x position
     * @param y y position
     */
    private ArrayList<Point2D> validAdj(int x, int y)
    {
        ArrayList<Point2D> r = new ArrayList<>();
        if (validMovePt(x, y + 1))
            r.add(new Point2D(x, y + 1));
        if (validMovePt(x, y - 1))
            r.add(new Point2D(x, y - 1));
        if (validMovePt(x + 1, y))
            r.add(new Point2D(x + 1, y));
        if (validMovePt(x - 1, y))
            r.add(new Point2D(x - 1, y));
        return r;
    }

    /**
     * Finds whether there is empty space at the specified position.
     * Returns true if so, false otherwise.
     *
     * @param x x position
     * @param y y position
     */
    private boolean validMovePt(int x, int y)
    {
        return withinRange(x, y) && board[y][x] == '0';
    }

    /**
     * Finds whether the given point is within range of the maze.
     * Returns true if so, false otherwise.
     *
     * @param x x position
     * @param y y position
     */
    private boolean withinRange(int x, int y)
    {
        return x >= 0 && x < xDim && y >= 0 && y < yDim;
    }

    @Override
    public String toString()
    {
        String b = "";
        for (char[] arr : board)
        {
            for (char c : arr)
            {
                b += c + " ";
            }
            b += "\n";
        }
        return b;
    }

    public static void main(String[] args)
    {
        char[][] maze1 = {
                {'1', '1', '1', '1', '1'},
                {'b', '0', '1', '0', '1'},
                {'1', '0', '1', '0', 'e'},
                {'0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1'}
        };

        char[][] maze2 = {
                {'1', '1', '1', '1', '1'},
                {'b', '0', '1', '0', '1'},
                {'1', '0', '1', '0', 'e'},
                {'0', '0', '0', '0', '1'},
                {'0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1'}
        };
        Maze m = new Maze(maze1);
        Maze m2 = new Maze(maze2);
        m.solve(0, 1);
        System.out.println();
        System.out.println();
        System.out.println();
        m2.solve(0, 1);
    }
}


