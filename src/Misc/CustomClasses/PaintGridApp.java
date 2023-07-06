package Misc.CustomClasses;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PaintGridApp extends Application {

    private static final int GRID_SIZE = 10;
    private static final int RECTANGLE_SIZE = 50;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        // Create the grid of rectangles
        Rectangle[][] rectangles = new Rectangle[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rectangle = new Rectangle(RECTANGLE_SIZE, RECTANGLE_SIZE);
                rectangle.setFill(Color.WHITE);

                // Add mouse pressed event handler
                rectangle.setOnMousePressed(event -> {
                    rectangle.setFill(Color.BLACK);
                });

                rectangles[row][col] = rectangle;
                gridPane.add(rectangle, col, row);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Paint Grid");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
