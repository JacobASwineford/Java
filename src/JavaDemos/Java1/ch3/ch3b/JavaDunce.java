package JavaDemos.Java1.ch3.ch3b;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 *
 *
 * @author Jacob Swineford
 */

public class JavaDunce extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int SCENE_WIDTH = 500;
        final int SCENE_HEIGHT = 450;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.SLATEGRAY);

        Circle head = new Circle(250, 270, 110);
        head.setFill(Color.LIGHTGRAY);

        //color used for the eys, mouth, dunce cap, and text
        Color color = new Color(0.4, 0.1, 0.1, 1);

        Ellipse mouth = new Ellipse(250, 340, 40, 15);
        mouth.setFill(color);
        mouth.setStroke(Color.BLACK); //sets an outline that's black around the object
        mouth.setStrokeWidth(4);

        Circle leftEye = new Circle(210, 250, 12);
        Circle rightEye = new Circle(290, 250, 12);
        leftEye.setFill(color);
        rightEye.setFill(color);

        Polygon dunceCap = new Polygon(150, 220, 350, 220, 250, 10); // left, x y, right, x y, middle, x y
        dunceCap.setFill(color);

        // caption at the bottom of the scene
        Text text = new Text("Public Staic void main");
        text.setX(120);
        text.setY(420);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);
        text.setFont(font);
        text.setFill(color);

        root.getChildren().addAll(head, mouth, rightEye, leftEye, dunceCap, text);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Java Dunce");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
