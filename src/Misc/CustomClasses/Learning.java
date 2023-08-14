package Misc.CustomClasses;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Learning extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        double SCENE_WIDTH = 900;
        double SCENE_HEIGHT = 800;
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Pane box = new Pane();
        Rectangle bg = new Rectangle(400, 400, Color.PINK);
        box.getChildren().add(bg);

        Pane row = new Pane();
        for (int i = 0; i < 3; i++) {
            Rectangle r = new Rectangle(100, 100);
            r.setFill(Color.LIGHTBLUE);
            r.setLayoutX(100 * i);
            r.setStroke(Color.BLACK);
            r.setStrokeWidth(5);
            row.getChildren().add(r);
        }

        box.setLayoutX((SCENE_WIDTH - 400) / 2);
        box.setLayoutY((SCENE_HEIGHT - 400) / 2);

        double rowWidth = row.getBoundsInParent().getWidth();
        double rowHeight = row.getBoundsInLocal().getHeight();

        row.setLayoutX((400 - rowWidth) / 2);
        row.setLayoutY((400 - rowHeight) / 2);

        double rowX = box.getLayoutX();

        System.out.println(rowWidth + " " + rowHeight + " " + rowX);

        box.getChildren().add(row);
        root.getChildren().add(box);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Learn");
        primaryStage.show();
    }
}
