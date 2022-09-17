package JavaDemos.Java1.ch6.ch6d;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Draws a chessboard of size chosen by the user.
 *
 * @author Jacob Swineford
 */
public class Chessboard extends Application {

    @Override
    public void start(Stage primaryStage) {

        String prompt = "Enter size of board.";
        String str = javax.swing.JOptionPane.showInputDialog(prompt);
        int n = Integer.parseInt(str);

        final double SQUARE_SIZE = 20;
        final int GAP = 10; // space between chessboard and edge

        // length and width of the scene
        final double BOARD_SIZE = (n * SQUARE_SIZE) + (2 * GAP);
        Pane root = new Pane();
        Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE);

        // draw a board and add a drop shadow
        Rectangle board = new Rectangle(GAP, GAP, n * SQUARE_SIZE,
                n * SQUARE_SIZE);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY);
        dropShadow.setOffsetX(10);
        dropShadow.setOffsetY(10);
        board.setEffect(dropShadow);
        board.setFill(Color.BLUE);
        root.getChildren().addAll(board);

        // color the squares of the board
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                // find the location of top-left corner of square at (row, col)
                double x = GAP + (col * SQUARE_SIZE);
                double y = GAP + (row * SQUARE_SIZE);
                Rectangle r = new Rectangle(x, y, SQUARE_SIZE, SQUARE_SIZE);
                root.getChildren().add(r);
                if ((row + col) % 2 == 0) {
                    r.setFill(Color.RED);
                } else {
                    r.setFill(Color.BLACK);
                }
            }
        }
        primaryStage.setTitle("Chessboard");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

