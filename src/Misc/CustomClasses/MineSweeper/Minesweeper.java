package Misc.CustomClasses.MineSweeper;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.Random;

/**
 * Engine for the game of mine-sweeper. a Player tries to identify
 * all of the grid spaces in a field without hitting a mine.
 * Specifically, this class keeps track of the players moves and
 * the spaces that have been revealed.
 *
 * @author Jacob Swineford
 */
class Minesweeper {

    private final int[][] field;
    private String[][] hiddenField;

    private Pane grid;
    private Pane[][] val;

    Minesweeper(int rows, int cols, int numMines, int width, int height) {
        field = new int[rows][cols];
        grid = new Pane();
        val = new Pane[rows][cols];
        hiddenField = new String[rows][cols];
        placeMines(numMines);
        makeGrid(width, height);
    }

    private void makeGrid(int width, int height) {
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                final int ie = i;
                final int je = j;
                hiddenField[i][j] = "*";
                val[i][j] = new Pane();
                Rectangle mini = new Rectangle();
                mini.setWidth(width);
                mini.setHeight(height);
                mini.setFill(Color.GRAY);
                mini.setStroke(Color.BLACK);
                mini.setStrokeWidth(3);
                Text t = new Text(hiddenField[i][j]);
                t.setTranslateX((double) width / 3);
                t.setTranslateY((double) height / 1.5);
                val[i][j].setTranslateY(i * height);
                val[i][j].setTranslateX(j * width);
                val[i][j].setOnMouseClicked(event -> updateField(ie, je));
                val[i][j].getChildren().addAll(mini, t);
                grid.getChildren().add(val[i][j]);
            }
        }
    }

    private void updateField(int row, int col) {
        revealSurroundings(row, col);
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                text(i, j).setText(hiddenField[i][j]);
            }
        }
    }

    private Text text(int row, int col) {
        Object[] arr = val[row][col].getChildren().toArray();
        for (Object o : arr) {
            if (o instanceof Text) return (Text) o;
        }
        return new Text("Error");
    }
    private Rectangle tile(int row, int col) {
        Object[] arr = val[row][col].getChildren().toArray();
        for (Object o : arr) {
            if (o instanceof Rectangle) return (Rectangle) o;
        }
        return new Rectangle();
    }

    private void placeMines(int numMines) {
        Random rand = new Random();
        for (;numMines != 0;) {
            int row = rand.nextInt(field.length);
            int col = rand.nextInt(field[0].length);
            if (hasMine(row, col)) continue;
            field[row][col] = -1;
            provideWarningsFor(row, col);
            numMines--;
        }
    }

    private void provideWarningsFor(int row, int col) {
        if (inRange(row - 1, col - 1) && !hasMine(row - 1, col - 1)) field[row - 1][col - 1]++;
        if (inRange(row - 1, col) && !hasMine(row - 1, col)) field[row - 1][col]++;
        if (inRange(row - 1, col + 1) && !hasMine(row - 1, col + 1)) field[row - 1][col + 1]++;
        if (inRange(row, col + 1) && !hasMine(row, col + 1)) field[row][col + 1]++;
        if (inRange(row + 1, col + 1) && !hasMine(row + 1, col + 1)) field[row + 1][col + 1]++;
        if (inRange(row + 1, col) && !hasMine(row + 1, col)) field[row + 1][col]++;
        if (inRange(row + 1, col - 1) && !hasMine(row + 1, col - 1)) field[row + 1][col - 1]++;
        if (inRange(row, col - 1) && !hasMine(row, col - 1)) field[row][col - 1]++;
    }

    private void reveal(int row, int col) {
        if (inRange(row, col) && hiddenField[row][col].equals("*")) {
            tile(row, col).setFill(Color.WHITESMOKE);
            hiddenField[row][col] = Integer.toString(field[row][col]);
            text(row, col).setText(Integer.toString(field[row][col]));
            if (field[row][col] == -1) tile(row, col).setFill(Color.LIGHTPINK);
        }
    }

    private void revealSurroundings(int row, int col) {
        reveal(row-1,col-1);
        reveal(row-1,col);
        reveal(row-1,col+1);
        reveal(row+1,col-1);
        reveal(row+1,col);
        reveal(row+1,col+1);
        reveal(row,col-1);
        reveal(row,col+1);
        reveal(row,col);
    }


    // causes a stack overflow exception to be thrown
    private void crawlingReveal(int row, int col) {
        if (!inRange(row, col) || field[row][col] == -1) return;
        for (Point2D p : validAdjacent(row, col)) {
            revealSurroundings((int) p.getX(), (int) p.getY());
            crawlingReveal((int) p.getX(), (int) p.getY());
        }
    }

    private LinkedList<Point2D> validAdjacent(int row, int col) {
        LinkedList<Point2D> l = new LinkedList<>();
        if (inRange(row-1,col)) l.add(new Point2D(row-1, col));
        if (inRange(row,col+1)) l.add(new Point2D(row, col+1));
        if (inRange(row+1,col)) l.add(new Point2D(row+1, col));
        if (inRange(row,col-1)) l.add(new Point2D(row, col-1));
        return l;
    }

    // DEEP COPY EXAMPLE
    int[][] getFieldDeep() {
        int [][] copy = new int[field.length][];
        for(int i = 0; i < field.length; i++)
        {
            copy[i] = new int[field[i].length];
            System.arraycopy(field[i], 0, copy[i], 0, field[i].length);
        }
        return copy;
    }

    // SHALLOW COPY EXAMPLE
    int[][] getFieldShallow() {return field;}

    Pane getGrid() {return grid;}

    private boolean hasMine(int row, int col) {
        return field[row][col] == -1;
    }

    private boolean inRange(int row, int col) {
        return row > -1 && row < field.length && col > -1 && col < field[0].length;
    }
}
