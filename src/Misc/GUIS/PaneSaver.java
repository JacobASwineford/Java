package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class PaneSaver extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Pane saved = new Pane();
        saved.setStyle("-fx-background-color: rgb(100, 100, 0, 1);");
        saved.setMinHeight(SCENE_HEIGHT);
        saved.setMinWidth(SCENE_WIDTH * (3/4.0));

        root.getChildren().add(saved);

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
