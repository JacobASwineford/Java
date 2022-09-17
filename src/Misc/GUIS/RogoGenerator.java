package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class RogoGenerator extends Application {

    TextField[][] val;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new VBox();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        int puzzleWidth = 4;
        int puzzleHeight = 4;
        double width = 50;
        double height = 50;
        val = new TextField[puzzleHeight][puzzleWidth];
        Pane grid = new Pane();
        for (int i = 0; i < puzzleHeight; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                TextField t = new TextField();
                t.setPrefWidth(width);
                t.setPrefHeight(height);
                t.setTranslateX(j * width);
                t.setTranslateY(i * height);
                grid.getChildren().add(t);
                val[i][j] = t;
            }
        }

        // button for print
        Button b = new Button();
        b.setText("Submit");
        b.setTranslateY(puzzleHeight * height);
        b.setTranslateX(puzzleWidth * width / 2);
        b.setOnAction(event -> print());

        grid.getChildren().add(b);
        root.getChildren().add(grid);

        primaryStage.setTitle("Rogo Generator");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void print() {
        System.out.println("new int[][] {");
        for (int i = 0; i < val.length; i++) {
            System.out.print("{");
            for (int j = 0; j < val[i].length; j++) {
                if (j != val[i].length - 1) {
                    System.out.printf("%s, ", val[i][j].getText());
                } else {
                    System.out.printf("%s", val[i][j].getText());
                }

            }
            if (i != val.length - 1) {
                System.out.println("},");
            } else {
                System.out.println("}}");
            }
        }
        System.out.printf("\n max number of moves: %d", (val.length * 2) + ((val[0].length - 2) * 2));
    }

    public static void main(String[] args) {
        launch(args);
    }


}
