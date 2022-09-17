package Misc.TestCrap;

import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import Misc.CustomClasses.BetterMonopoly.Spaces.*;
import Misc.Utilities.Transitions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class test6 extends Application {

    private double dx = 0;
    private double dy = 0;
    private boolean set = false;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 1000;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Rectangle r = new Rectangle(100, 100, Color.BLUE);
        r.setStroke(Color.BLACK);
        r.setX(100);
        r.setY(100);


        r.setOnMouseDragged(event -> {
            if (!set) {
                dx = event.getX() - r.getX();
                dy = event.getY() - r.getY();
                set = true;
            }
            r.setX(event.getX() - dx);
            r.setY(event.getY() - dy);
        });

        r.setOnMouseReleased(event -> set = false);
        Transitions.setEnteredHighlight(r);
//        root.getChildren().add(r);

        Monopoly m = new Monopoly(new Player("Jacob", 2000));
        m.constructBoard(SCENE_WIDTH, SCENE_HEIGHT, 10);

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
