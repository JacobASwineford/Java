package Misc.CustomClasses.Checkers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class Checkers {

    private int[][] board;
    private final int length;
    private ArrayList<Point2D> validMoves;
    private ArrayList<Point2D> validAttacks;
    private Point2D selected;

    private Pane[][] segments;

    private Rectangle[][] bases;
    private Color[][] baseColors;

    private Circle[][] checkers;
    private Color checkerColor1 = Color.BLACK;
    private Color checkerColor2 = Color.WHITE;

    private Pane master;

    Checkers(Scene scene, int length) throws Exception {
        if (length % 2 != 0 || length < 7) throw new Exception();
        this.length = length;
        board = new int[length][length];
        segments = new Pane[length][length];
        bases = new Rectangle[length][length];
        baseColors = new Color[length][length];
        checkers = new Circle[length][length];
        validMoves = new ArrayList<>();
        validAttacks = new ArrayList<>();
        selected = null;
        master = new Pane();
        setButtons(scene);
        setPieces();
        initPane(scene.getWidth(), scene.getHeight());
    }

    private Checkers(double SCENE_WIDTH, double SCENE_HEIGHT, int length) throws Exception {
        if (length % 2 != 0 || length < 7) throw new Exception();
        this.length = length;
        board = new int[length][length];
        segments = new Pane[length][length];
        bases = new Rectangle[length][length];
        baseColors = new Color[length][length];
        checkers = new Circle[length][length];
        validMoves = new ArrayList<>();
        validAttacks = new ArrayList<>();
        selected = null;
        master = new Pane();
        setPieces();
        initPane(SCENE_WIDTH, SCENE_HEIGHT);
    }

    private void setButtons(Scene scene) {
        KeyFrame autoFrame = new KeyFrame(Duration.millis(5), event -> {
            Random rand = new Random();
            ArrayList<Point2D> arr;

            if (turn == 1) arr = blackPieces();
            else arr = whitePieces();
            if (arr.size() == 0) {autoTimeLine.stop(); return;}
            Point2D checkerPoint = arr.get(rand.nextInt(arr.size()));
            int cx = (int) checkerPoint.getX();
            int cy = (int) checkerPoint.getY();


            ArrayList<Point2D> moves = validMoves(cx, cy);
            ArrayList<Point2D> attacks = validAttacks(cx, cy);
            ArrayList<Point2D> actions = new ArrayList<>(moves);
            actions.addAll(attacks);

            // if the selected checker cannot make a move, then find a new checker that can move
            while (actions.size() == 0) {
                checkerPoint = arr.get(rand.nextInt(arr.size()));
                cx = (int) checkerPoint.getX();
                cy = (int) checkerPoint.getY();
                moves = validMoves(cx, cy);
                attacks = validAttacks(cx, cy);
                actions = new ArrayList<>(moves);
                actions.addAll(attacks);
            }

            Point2D endPoint = actions.get(rand.nextInt(actions.size()));
            int ex = (int) endPoint.getX();
            int ey = (int) endPoint.getY();

            if (attacks.contains(endPoint)) attack(cx, cy, ex, ey);
            else move(cx, cy, ex, ey);

            turn = -turn;
        });
        autoTimeLine = new Timeline(autoFrame);
        autoTimeLine.setCycleCount(Timeline.INDEFINITE);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A: toggleAutoPlay(); break;
                case R: reset(); break;
            }
        });
    }

    private void reset() {
        clearBoard();
        setPieces();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 1) {
                    checkers[i][j].setFill(checkerColor1);
                    checkers[i][j].setVisible(true);
                } else if (board[i][j] == 2) {
                    checkers[i][j].setFill(checkerColor2);
                    checkers[i][j].setVisible(true);
                }
            }
        }
    }

    private void clearBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                board[i][j] = 0;
                checkers[i][j].setVisible(false);
            }
        }
    }

    private void initPane(double width, double height) {
        double rw = width / length;
        double rh = height / length;
        double strokeWidth = 3;
        Color baseColor1 = Color.BLUE.darker();
        Color baseColor2 = Color.RED.darker();
        int inverse = 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                double x = j * rw;
                double y = i * rh;
                if (inverse == 1) {
                    if (j % 2 == 0) baseColors[i][j] = baseColor1;
                    else baseColors[i][j] = baseColor2;
                } else {
                    if (j % 2 == 0) baseColors[i][j] = baseColor2;
                    else baseColors[i][j] = baseColor1;
                }
                Rectangle base = stylized(rw, rh, x, y, baseColors[i][j]);
                bases[i][j] = base;

                Circle check;
                x = base.getX() + (rw / 2);
                y = base.getY() + (rh / 2);
                double radius = (rw / 2) - (rw / 4);
                check = checker(radius, x, y);
                check.setStroke(Color.TAN);
                check.setStrokeWidth(strokeWidth);
                checkers[i][j] = check;

                if (board[i][j] == 1) check.setFill(checkerColor1);
                else if (board[i][j] == 2) check.setFill(checkerColor2);
                else check.setVisible(false);

                Pane segment = new Pane();
                segment.getChildren().addAll(base, check);
                segments[i][j] = segment;

                segments[i][j].setOnMousePressed(event -> {
                    int ehx = (int) ((event.getX() / rw) + master.getTranslateX());
                    int yee = (int) ((event.getY() / rh) + master.getTranslateY());
                    select(ehx, yee);
                });

                master.getChildren().add(segment);
            }
            inverse = -inverse;
        }
        //setBlinkerPattern();
    }

    private int autoPlay = -1;
    private Timeline autoTimeLine;
    private int turn = 1; // 1 is black, -1 is white
    private void toggleAutoPlay() {
        autoPlay = -autoPlay;
        if (autoPlay == 1) autoTimeLine.play();
        else autoTimeLine.pause();
    }

    private void setPieces() {
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

    private int blinkerIndex = 0;
    private void setBlinkerPattern() {
        Duration stallTime = Duration.millis(200);
        KeyFrame kf = new KeyFrame(stallTime, event -> {
            if (blinkerIndex == length) blinkerIndex = 0;
            for (int i = 0; i < length; i++) {
                if (isSelected(blinkerIndex, i)) continue;
                blink(blinkerIndex, i, stallTime);
            }
            blinkerIndex++;
        });
        Timeline t = new Timeline(kf);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    private Rectangle stylized(double width, double height, double x, double y, Color color) {
        Rectangle r = new Rectangle();
        r.setWidth(width);
        r.setHeight(height);
        r.setX(x);
        r.setY(y);
        r.setFill(color);
        r.setStrokeWidth(3);
        r.setStroke(Color.BLACK);
        return r;
    }

    private Circle checker(double radius, double x, double y) {
        Circle c = new Circle();
        c.setRadius(radius);
        c.setCenterX(x);
        c.setCenterY(y);
        c.setFill(Color.TRANSPARENT);
        return c;
    }

    private void blink(int x, int y, Duration stallTime) {
        bases[y][x].setFill(Color.LIGHTPINK);
        try {
            KeyFrame stall = new KeyFrame(stallTime, event -> {});
            Timeline t = new Timeline(stall);
            t.setCycleCount(1); t.play();
            t.setOnFinished(event -> bases[y][x].setFill(baseColors[y][x]));
        } catch (Exception e) {bases[y][x].setFill(baseColors[y][x]);}
    }

    private void select(int x, int y) {
        if (validAttacks.contains(new Point2D(x, y))) {
            attack((int) selected.getX(), (int) selected.getY(), x, y);
            clearSelected((int) selected.getX(), (int) selected.getY());
            return;
        }
        if (validMoves.contains(new Point2D(x, y))) {
            move((int) selected.getX(), (int) selected.getY(), x, y);
            clearSelected((int) selected.getX(), (int) selected.getY());
            return;
        }

        if (selected != null) // clear everything on each select, except the first time
        clearSelected((int) selected.getX(), (int) selected.getY());

        selected = new Point2D(x, y);
        ArrayList<Point2D> moves = validMoves(x, y);
        for (Point2D point : moves) {
            bases[(int) point.getY()][(int) point.getX()].setFill(Color.LIGHTGOLDENRODYELLOW);
            validMoves.add(point);
        }

        ArrayList<Point2D> attacks = validAttacks(x, y);
        for (Point2D point : attacks) {
            bases[(int) point.getY()][(int) point.getX()].setFill(Color.ORANGERED);
            validAttacks.add(point);
        }
    }

    private void clearSelected(int x, int y) {
        bases[y][x].setFill(baseColors[y][x]); // tile itself
        for (Point2D point : validMoves) { // clearing valid moves
            x = (int) point.getX();
            y = (int) point.getY();
            bases[y][x].setFill(baseColors[y][x]);
        }
        for (Point2D point : validAttacks) { // clearing valid attacks
            x = (int) point.getX();
            y = (int) point.getY();
            bases[y][x].setFill(baseColors[y][x]);
        }
        validMoves.clear();
        validAttacks.clear();
        selected = null;
    }

    private void move(int xStart, int yStart, int xEnd, int yEnd) {
        board[yEnd][xEnd] = board[yStart][xStart];
        board[yStart][xStart] = 0;
        checkers[yEnd][xEnd].setFill(checkers[yStart][xStart].getFill());
        checkers[yStart][xStart].setVisible(false);
        checkers[yEnd][xEnd].setVisible(true);
        if (board[yEnd][xEnd] == 1 && yEnd == length - 1) {
            board[yEnd][xEnd] = 3;
            checkers[yEnd][xEnd].setFill(Color.PURPLE);
        }
        if (board[yEnd][xEnd] == 2 && yEnd == 0) {
            board[yEnd][xEnd] = 4;
            checkers[yEnd][xEnd].setFill(Color.GRAY);
        }
    }

    private void attack(int xStart, int yStart, int xEnd, int yEnd) {
        int xd = xEnd - xStart;
        int yd = yEnd - yStart;
        int x = 0; int y = 0;
        if (xd == -2 && yd == -2) {x = xStart-1; y = yStart-1;}
        if (xd == 2 && yd == -2) {x = xStart+1; y = yStart-1;}
        if (xd == 2 && yd == 2) {x = xStart+1; y = yStart+1;}
        if (xd == -2 && yd == 2) {x = xStart-1; y = yStart+1;}
        move(xStart, yStart, xEnd, yEnd);
        board[y][x] = 0;
        checkers[y][x].setVisible(false);
    }

    private boolean withinRange(int x, int y) {
        if (y < 0 || y >= length || x < 0 || x >= length) return false;
        return board[y][x] == 0;
    }

    private boolean isEmpty(int x, int y) {return board[y][x] == 0;}

    private boolean isSelected(int x, int y) {
        if (selected == null) return false;
        if (validAttacks.contains(new Point2D(x, y))) return true;
        if (validMoves.contains(new Point2D(x, y))) return true;
        return selected.equals(new Point2D(x, y));
    }

    private ArrayList<Point2D> validMoves(int x, int y) {
        ArrayList<Point2D> arr = new ArrayList<>();
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

    private ArrayList<Point2D> blackPieces() {
        ArrayList<Point2D> arr = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 1 || board[i][j] == 3) arr.add(new Point2D(j, i));
            }
        }
        return arr;
    }

    private ArrayList<Point2D> whitePieces() {
        ArrayList<Point2D> arr = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 2 || board[i][j] == 4) arr.add(new Point2D(j, i));
            }
        }
        return arr;
    }

    private void printBoard() {
        for (int[] arr : board) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    // shallow copy
    int[][] getBoard() {return board;}

    // shallow copy
    Pane getMaster() {return master;}

    public static void main(String[] args) throws Exception {
        Checkers c = new Checkers(500, 500, 8);
        c.printBoard();
    }
}
