package JavaDemos.Java1.ch3.ch3b;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *  displays an empty stage.
 *
 *  @author Jacob Swineford
 */
public class EmptyScene extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane(); //pane contains nodes
        Scene scene = new Scene(root, 800, 500); //width and height in pixels

        primaryStage.setTitle("Empty Scene"); //sets the title for the scene
        primaryStage.setScene(scene); //specifies the stage
        primaryStage.show(); //shows the stage (window)

    }

    public static void main(String[] args) {
        launch(args);
    }
}
