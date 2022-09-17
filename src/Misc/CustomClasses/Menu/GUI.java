package Misc.CustomClasses.Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Menu<Box> m = new Menu<>(4, false, true);
        Menu<Box> m2 = new Menu<>(1, true, true);
        m2.setVisible(false);
        m2.setTranslateX(400);
        Box anchor = new Box(100, 100);
        m.add(anchor);
        m.select(anchor);

        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            double width = rand.nextInt(100) + 50;
            double height = rand.nextInt(100) + 50;
            Box b = new Box(100, 100);
            Box b2 = new Box(width, height);
            m.add(b);
//            m2.add(b2);
        }

        root.getChildren().addAll(m, m2);

        scene.setOnKeyPressed(event -> {
            KeyCode k = event.getCode();
            switch (k) {
                case A: {m.navigate(Direction.Left); break;}
                case D: {m.navigate(Direction.Right); break;}
                case S: {m.navigate(Direction.Down); break;}
                case W: {m.navigate(Direction.Up); break;}
                case I: {m.deselect(m.getSelected()); break;}
                case K: {m.select(m.getFirst()); break;}
                case E: {m.add(new Box(100, 100)); break;}
                case F: {m.remove(m.getLast()); break;}
                case X: {m.executeSelected();}
            }
        });

        primaryStage.setTitle("GridMenu");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

class Box extends Pane implements Item {

    private Rectangle b;

    Box(double width, double height) {
        super();
        Rectangle box = new Rectangle(width, height);
        box.setFill(Color.BLUE);
        box.setStroke(Color.BLACK);
        box.setStrokeWidth(5);
        b = box;
        this.getChildren().add(box);
    }

    @Override
    public void navigatedOn() {
        b.setStroke(Color.RED);
    }

    @Override
    public void navigatedOff() {b.setStroke(Color.BLACK);}

    @Override
    public void execute() {

        class Flash extends Thread {

            @Override
            public void run() {
                try {
                    b.setFill(Color.GREEN);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //
                } finally {
                    b.setFill(Color.BLUE);
                }
            }
        }

        Thread f = new Flash();
        f.start();
    }
}
