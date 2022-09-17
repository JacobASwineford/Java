package Misc.CustomClasses.Cipher;

import Misc.CustomClasses.Cipher.CipherDisk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class CipherTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root = new StackPane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 1000;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        CipherDisk cd = new CipherDisk('z', 'a');
        root.getChildren().add(cd.getSpinnableDisk(400, 4, 26));

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
