package Misc.CustomClasses.TicTacToe;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class TicTacToe {

    private char[][] space;
    private Pane master;
    private StackPane[][] X;
    private Circle[][] O;

    private static final char char1 = 'X';
    private static final char char2 = 'O';
    private int turn = 1;

    class UpdateThread extends Thread {

        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (space[i][j] == char1) X[i][j].setVisible(true);
                        else if (space[i][j] == char2) O[i][j].setVisible(true);
                        else {O[i][j].setVisible(false); X[i][j].setVisible(false);}
                    }
                }
            }
        }
    }

    TicTacToe(double sw, double sh) {
        space = new char[3][3];
        master = new Pane();
        X = new StackPane[3][3];
        O = new Circle[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                space[i][j] = '-';
            }
        }
        initMaster(sw, sh);
        Thread ut = new UpdateThread();
        ut.start();
    }

    TicTacToe(double sw, double sh, Scene scene) {
        space = new char[3][3];
        master = new Pane();
        X = new StackPane[3][3];
        O = new Circle[3][3];
        reset();
        initMaster(sw, sh);
        setButtons(scene);
        Thread ut = new UpdateThread();
        ut.start();
    }

    private void setButtons(Scene scene) {
        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case R: reset();
            }
        });
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                space[i][j] = '-';
            }
        }
    }

    private void initMaster(double sw, double sh) {
        double width = sw / 3;
        double height = sh / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double x = width * j;
                double y = height * i;
                StackPane segment = new StackPane();
                Rectangle tile = tile(width, height);
                X[i][j] = X(width, height);
                O[i][j] = O(width / 2.5);

                segment.setTranslateX(x);
                segment.setTranslateY(y);
                segment.getChildren().addAll(tile, X[i][j], O[i][j]);
                master.setOnMouseClicked(event -> {
                    int ehx = (int) ((event.getX() / width) + master.getTranslateX());
                    int yee = (int) ((event.getY() / height) + master.getTranslateY());
                    makeMove(ehx, yee);
                });

                master.getChildren().add(segment);
            }
        }
    }

    private Rectangle tile(double width, double height) {
        Rectangle tile = new Rectangle();
        tile.setWidth(width);
        tile.setHeight(height);
        tile.setFill(Color.WHITESMOKE);
        tile.setStroke(Color.TAN);
        tile.setStrokeWidth(3);
        return tile;
    }

    private Circle O(double radius) {
        Circle o = new Circle();
        o.setRadius(radius);
        o.setFill(Color.TRANSPARENT);
        o.setStroke(Color.BLACK);
        o.setStrokeWidth(10);
        o.setVisible(false);
        return o;
    }

    private StackPane X(double width, double height) {
        StackPane fullX = new StackPane();
        Rectangle seg1 = new Rectangle();
        Rectangle seg2 = new Rectangle();
        seg1.setWidth(width);
        seg1.setHeight(height / 10);
        seg1.setX(-width / 2);
        seg1.setY(height / 20);
        seg1.setFill(Color.BLACK);
        seg2.setHeight(height);
        seg2.setWidth(width / 10);
        seg2.setX(width / 20);
        seg2.setY(-height / 2);
        seg2.setFill(Color.BLACK);
        fullX.getChildren().addAll(seg1, seg2);
        fullX.setVisible(false);
        fullX.setRotate(45);
        return fullX;
    }

    Pane getMaster() {return master;}

    int getTurn() {return turn;}

    private int makeMove(int x, int y) {
        if (withinRange(x, y)) {
            if (space[y][x] != '-') return -2; // occupied
            if (turn == 1) {space[y][x] = char1; turn = 2; return 1;}
            if (turn == 2) {space[y][x] = char2; turn = 1; return 1;}
        }
        return -1; // not within range
    }

    private int won() {
        if (diagonals(char1) || verticals(char1) || horizontals(char1)) return 1;
        if (diagonals(char2) || verticals(char2) || horizontals(char2)) return 2;
        if (full()) return 0;
        return -1; // -1 for not done
    }

    private boolean diagonals(char c) {
        if (space[0][0] == c && space[1][1] == c && space[2][2] == c) return true;
        return space[0][2] == c && space[1][1] == c && space[2][0] == c;
    }

    private boolean verticals(char c) {
        if (space[0][0] == c && space[1][0] == c && space[2][0] == c) return true;
        if (space[0][1] == c && space[1][1] == c && space[2][1] == c) return true;
        return space[0][2] == c && space[1][2] == c && space[2][2] == c;
    }

    private boolean horizontals(char c) {
        if (space[0][0] == c && space[0][1] == c && space[0][2] == c) return true;
        if (space[1][0] == c && space[1][1] == c && space[1][2] == c) return true;
        return space[2][0] == c && space[2][1] == c && space[2][2] == c;
    }

    private boolean full() {
        for (char[] arr : space) {
            for (char c : arr) {
                if (c == '-') return false;
            }
        }
        return true;
    }

    private boolean withinRange(int x, int y) {
        return y >= 0 && y < 3 && x >= 0 && x < 3;
    }

    private void printSpace() {
        int yi = 1;
        System.out.println("    1   2   3");
        for (char[] arr : space) {
            System.out.print(yi + " | ");
            for (char c : arr) {
                System.out.print(c + " | ");
            }
            System.out.println();
            yi++;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TicTacToe t = new TicTacToe(500, 500);
        int won = -1;
        while(won == -1) {
            t.printSpace();
            System.out.print("Make a move, player " + t.getTurn() + "! (x, y)");
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int move = t.makeMove(x, y);
            if (move == -1) {
                System.out.println("The specified space is not within range! Please try again!\n");
                continue;
            } else if (move == -2) {
                System.out.println("The specified space is preoccupied! Please try again!\n");
                continue;
            }
            won = t.won();
            System.out.println();
        }
        t.printSpace();
        if (won == 1) System.out.println("Congratulations, player 1, you win!");
        if (won == 2) System.out.println("Congratulations, player 2, you win!");
        if (won == 0) System.out.println("It's a tie!");
    }
}
