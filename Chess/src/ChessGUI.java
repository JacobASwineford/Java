import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Basic test GunGame used to simulate a game of chess.
 *
 * @author Jacob Swineford
 */

public class ChessGUI extends Application {

    private final int SQUARE_SIZE = 120;
    private final ChessGame c = new ChessGame();
    private GridPane root = new GridPane();
    private int sX = 0;
    private int sY = 0;

    private Rectangle[][] moveTiles;
    private Rectangle[][] attackTiles;
    private Rectangle[][] selectTiles; // don't have an implication right now

    private StackPane[][] whitePawns;
    private StackPane[][] whiteBishops;
    private StackPane[][] whiteRooks;
    private StackPane[][] whiteKnights;
    private StackPane[][] whiteKings;
    private StackPane[][] whiteQueens;
    private StackPane[][] blackPawns;
    private StackPane[][] blackBishops;
    private StackPane[][] blackRooks;
    private StackPane[][] blackKnights;
    private StackPane[][] blackKings;
    private StackPane[][] blackQueens;

    private Color glassRed = Color.rgb(130, 0, 0, .7);
    private Color glassBlack = Color.rgb(0, 0, 0, .7);
    private Color glassYellow = Color.rgb(238, 228, 138, .7);
    private Color attackRed = Color.rgb(223, 10, 29, .5);
    private Color selectGreen = Color.rgb(58, 200, 46, .7);

    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        pane.getChildren().add(root);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case T:
                    toggleRunState();
                    break;
            }
        });

        // if the t button is pressed during he simulation, toggle autoPlay
        KeyFrame kF = new KeyFrame(Duration.millis(10), e -> {

            try {
                // pick a random piece
                int x1 = 0;
                int y1 = 0;
                int a = 0;
                int r1 = rand.nextInt(0, c.pieceLocations().size());
                for (Point2D p : c.pieceLocations()) {
                    if (a == r1) {
                        x1 = (int) p.getX();
                        y1 = (int) p.getY();
                    }
                    a++;
                }
                // pick a random position
                int x2 = 0;
                int y2 = 0;
                int i = 0;

                ArrayList<Point2D> arr = c.getMoveCoordinates(x1, y1);
                arr.addAll(c.getAttackCoordinates(x1, y1));
                int r2 = rand.nextInt(arr.size());

                if (arr.size() == 0) {
                    return;
                }
                for (Point2D p : arr) {
                    if (i == r2) {
                        x2 = (int) p.getX();
                        y2 = (int) p.getY();
                    }
                    i++;
                }

                c.move(x1, y1, x2, y2);
                setVisibleFalseFor(x2, y2);
                displayBoard();
            } catch (IllegalArgumentException ev) {
            }
        });
        timeline = new Timeline(kF);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.pause();

        setBoard(c.getBoardState()[0].length, c.getBoardState().length);
        displayBoard();

        primaryStage.setTitle("Chessboard");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Toggles the animation timeline between running and paused states.
     */
    private void toggleRunState() {
        if (timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.pause();
            System.out.println("Paused");
        } else {
            timeline.play();
            System.out.println("Running");
        }
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane blackKing() {
        String kingImage = "images/king.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_BLACK) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane blackQueen() {
        String kingImage = "images/queen.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_BLACK) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane blackKnight() {
        String kingImage = "images/knight.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_BLACK) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane blackBishop() {
        String kingImage = "images/bishop.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_BLACK) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane blackRook() {
        String kingImage = "images/rook.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_BLACK) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane blackPawn() {
        String kingImage = "images/pawn.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_BLACK) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane whiteKing() {
        String kingImage = "images/whiteKing.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_WHITE) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane whiteQueen() {
        String kingImage = "images/whiteQueen.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_WHITE) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }


    /**
     * creates a graphic for a king piece.
     */
    private StackPane whitePawn() {
        String kingImage = "images/whitePawn.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_WHITE) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane whiteBishop() {
        String kingImage = "images/whiteBishop.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_WHITE) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane whiteKnight() {
        String kingImage = "images/whiteKnight.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_WHITE) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * creates a graphic for a king piece.
     */
    private StackPane whiteRook() {
        String kingImage = "images/whiteRook.png";
        ImageView i = new ImageView(kingImage);
        StackPane sP = new StackPane();
        i.setFitHeight(SQUARE_SIZE);
        i.setFitWidth(SQUARE_SIZE);

        sP.getChildren().add(i);

        sP.setOnMouseClicked(event -> {
            if (c.getCurrentTurn() != ChessGame.TURN_WHITE) {
                return;
            }
            deselectAll();
            int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
            int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
            sX = xLocation;
            sY = yLocation;
            sP.setTranslateY(-10);
            highlightMoves(xLocation, yLocation);
        });

        return sP;
    }

    /**
     * Highlights the squares yellow and red to signify where the selected piece
     * can move and attack.
     */
    private void highlightMoves(int x, int y) {
        for (Point2D p : c.getMoveCoordinates(x, y)) {
            int xTemp = (int) p.getX();
            int yTemp = (int) p.getY();
            moveTiles[yTemp][xTemp].setVisible(true);
        }

        for (Point2D p : c.getAttackCoordinates(x, y)) {
            int xTemp = (int) p.getX();
            int yTemp = (int) p.getY();
            attackTiles[yTemp][xTemp].setVisible(true);
        }
    }

    /**
     * resets the board to it's default color scheme.
     */
    private void recolorizeBoard() {
        for (Rectangle[] arr : moveTiles) {
            for (Rectangle r : arr) {
                r.setVisible(false);
            }
        }
        for (Rectangle[] arr : attackTiles) {
            for (Rectangle r : arr) {
                r.setVisible(false);
            }
        }

        for (Rectangle[] arr : selectTiles) {
            for (Rectangle r : arr) {
                r.setVisible(false);
            }
        }
    }

    private void deselectAll() {
        for (Node n : root.getChildren()) {
            n.setTranslateY(0);
        }
        recolorizeBoard();
    }

    /**
     * Sets the board up with the required tiles and event handlers.
     */
    private void setBoard(int x, int y) {
        Rectangle[][] generalTiles = new Rectangle[y][x];
        moveTiles = new Rectangle[y][x];
        attackTiles = new Rectangle[y][x];
        selectTiles = new Rectangle[y][x];

        whitePawns = new StackPane[y][x];
        blackPawns = new StackPane[y][x];
        whiteRooks = new StackPane[y][x];
        blackRooks = new StackPane[y][x];
        whiteKnights = new StackPane[y][x];
        blackKnights = new StackPane[y][x];
        whiteBishops = new StackPane[y][x];
        blackBishops = new StackPane[y][x];
        whiteQueens = new StackPane[y][x];
        blackQueens = new StackPane[y][x];
        whiteKings = new StackPane[y][x];
        blackKings = new StackPane[y][x];

        int counter = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                // board tiles
                Rectangle square = new Rectangle();
                square.setHeight(SQUARE_SIZE);
                square.setWidth(SQUARE_SIZE);
                if (counter % 2 == 0) {
                    square.setFill(glassRed);
                } else {
                    square.setFill(glassBlack);
                }
                generalTiles[i][j] = square;

                generalTiles[i][j].setOnMouseClicked(event -> {
                    deselectAll();
                    int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
                    int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
                    sX = xLocation; // spawning
                    sY = yLocation; // spawning
                    selectTiles[sY][sX].setVisible(true);
                });

                root.add(generalTiles[i][j], j, i);

                // select tiles
                Rectangle select = new Rectangle();
                select.setHeight(SQUARE_SIZE);
                select.setWidth(SQUARE_SIZE);
                select.setFill(selectGreen);
                select.setVisible(false);
                selectTiles[i][j] = select;
                root.add(selectTiles[i][j], j, i);

                // move tiles
                Rectangle move = new Rectangle();
                move.setHeight(SQUARE_SIZE);
                move.setWidth(SQUARE_SIZE);
                move.setFill(glassYellow);
                move.setVisible(false);
                moveTiles[i][j] = move;
                moveTiles[i][j].setOnMouseClicked(event -> {
                    int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
                    int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
                    c.move(sX, sY, xLocation, yLocation);
                    displayBoard();
                    deselectAll();
                });
                root.add(moveTiles[i][j], j, i);

                // pieces
                whitePawns[i][j] = whitePawn();
                whitePawns[i][j].setVisible(false);
                whiteKnights[i][j] = whiteKnight();
                whiteKnights[i][j].setVisible(false);
                whiteRooks[i][j] = whiteRook();
                whiteRooks[i][j].setVisible(false);
                whiteBishops[i][j] = whiteBishop();
                whiteBishops[i][j].setVisible(false);
                whiteQueens[i][j] = whiteQueen();
                whiteQueens[i][j].setVisible(false);
                whiteKings[i][j] = whiteKing();
                whiteKings[i][j].setVisible(false);

                blackPawns[i][j] = blackPawn();
                blackPawns[i][j].setVisible(false);
                blackKnights[i][j] = blackKnight();
                blackKnights[i][j].setVisible(false);
                blackRooks[i][j] = blackRook();
                blackRooks[i][j].setVisible(false);
                blackBishops[i][j] = blackBishop();
                blackBishops[i][j].setVisible(false);
                blackQueens[i][j] = blackQueen();
                blackQueens[i][j].setVisible(false);
                blackKings[i][j] = blackKing();
                blackKings[i][j].setVisible(false);

                root.add(whitePawns[i][j], j , i);
                root.add(blackPawns[i][j], j, i);
                root.add(whiteBishops[i][j], j, i);
                root.add(blackBishops[i][j], j, i);
                root.add(whiteKnights[i][j], j, i);
                root.add(blackKnights[i][j], j, i);
                root.add(whiteRooks[i][j], j, i);
                root.add(blackRooks[i][j], j, i);
                root.add(whiteQueens[i][j], j, i);
                root.add(blackQueens[i][j], j, i);
                root.add(whiteKings[i][j], j, i);
                root.add(blackKings[i][j], j, i);

                // attack tiles
                Rectangle attack = new Rectangle();
                attack.setHeight(SQUARE_SIZE);
                attack.setWidth(SQUARE_SIZE);
                attack.setFill(attackRed);
                attack.setVisible(false);
                attackTiles[i][j] = attack;
                attackTiles[i][j].setOnMouseClicked(event -> {
                    int xLocation = (int) (event.getSceneX() / SQUARE_SIZE);
                    int yLocation = (int) (event.getSceneY() / SQUARE_SIZE);
                    c.move(sX, sY, xLocation, yLocation);
                    setVisibleFalseFor(xLocation, yLocation);
                    displayBoard();
                    deselectAll();
                });
                root.add(attackTiles[i][j], j, i);
                counter++;
                if (x % 2 == 0 && j == x - 1) {
                    counter++;
                }
            }
        }

    }

    private void setVisibleFalseFor(int x, int y) {
        whitePawns[y][x].setVisible(false);
        blackPawns[y][x].setVisible(false);
        whiteRooks[y][x].setVisible(false);
        blackRooks[y][x].setVisible(false);
        whiteKnights[y][x].setVisible(false);
        blackKnights[y][x].setVisible(false);
        whiteBishops[y][x].setVisible(false);
        blackBishops[y][x].setVisible(false);
        whiteKings[y][x].setVisible(false);
        blackKings[y][x].setVisible(false);
        whiteQueens[y][x].setVisible(false);
        blackQueens[y][x].setVisible(false);
    }

    /**
     * Displays the current board according to c.getBoardState;
     */
    private void displayBoard() {
        for  (int i = 0; i < c.getBoardState().length; i++) {
            for (int j = 0; j < c.getBoardState()[i].length; j++) {
                if (c.pieceOf(j, i) == null) {
                    setVisibleFalseFor(j, i);
                    continue;
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.WHITE_PAWN) {
                    whitePawns[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.BLACK_PAWN) {
                    blackPawns[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.WHITE_ROOK) {
                    whiteRooks[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.BLACK_ROOK) {
                    blackRooks[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.WHITE_KNIGHT) {
                    whiteKnights[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.BLACK_KNIGHT) {
                    blackKnights[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.WHITE_BISHOP) {
                    whiteBishops[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.BLACK_BISHOP) {
                    blackBishops[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.WHITE_KING) {
                    whiteKings[i][j].setVisible(true);
                }
                if (c.pieceOf(j, i).getPieceType() == PieceType.BLACK_KING) {
                    blackKings[i][j].setVisible(true);
                }
                if(c.pieceOf(j, i).getPieceType() == PieceType.WHITE_QUEEN) {
                    whiteQueens[i][j].setVisible(true);
                }
                if(c.pieceOf(j, i).getPieceType() == PieceType.BLACK_QUEEN) {
                    blackQueens[i][j].setVisible(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
