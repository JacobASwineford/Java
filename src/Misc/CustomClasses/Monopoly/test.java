package Misc.CustomClasses.Monopoly;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class test extends Application {

    private static Color[] c = {Color.BLUE, Color.YELLOW, Color.DARKGREEN};
    private static int ci = 0;
    private Rectangle r = new Rectangle(200, 200);

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        final int SCENE_WIDTH = 500;
        final int SCENE_HEIGHT = 300;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        r.setFill(Color.BROWN);
        r.setOnMouseClicked(event ->{
            toggleColor();
            r.setDisable(true);
        });
        root.getChildren().add(r);

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void toggleColor() {
        try {
            r.setFill(c[ci]);
        } catch (IndexOutOfBoundsException e) {
            ci = 0;
            r.setFill(c[ci]);
        }
        ci++;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
