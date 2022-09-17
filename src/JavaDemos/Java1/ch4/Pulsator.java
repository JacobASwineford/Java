package JavaDemos.Java1.ch4;

/**
 * @author Jacob Swineford
 */

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Pulsator extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane(); //sets all nodes in the middle of the pane
        final int SCENE_WIDTH = 500;
        final int SCENE_HEIGHT = 450;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);

        Text topText = getPulsatingText("DEEZ"); // PulsatingText is a helper method
        Text bottomtext = getPulsatingText("NUTZ");
        topText.setTranslateY(-125); //Move the top text up
        bottomtext.setTranslateY(125); //Move the top text down

        Rectangle rect = new Rectangle(SCENE_WIDTH - 20, SCENE_HEIGHT - 20);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(5);
        rect.setFill(null);
        makePulsate(rect);

        root.getChildren().addAll(topText, bottomtext, rect);

        primaryStage.setTitle("Empty Scene");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Returns a randomly colored text node with a pulsating effect.
     */

    private Text getPulsatingText(String str) { //access specifier, reference type, method name, return as
        Text text = new Text(str);
        text.setFill(randomColor());

        Random rand = new Random();

        final int SIZE = 100;
        Font font = Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, SIZE); //Factory method; builds the object for you
        text.setFont(font);
        text.setRotate(rand.nextInt(360));

        makePulsate(text);

        return text;
    }
    /**
     * Applies a pulsating effect to a given node.
     */
    private void makePulsate(Node node) {
        // Duration of transition chosen at random in the range 750-1500 milliseconds
        Random rand = new Random();
        final int DURATION = 750 + rand.nextInt(751);
        ScaleTransition scale = new ScaleTransition(Duration.millis(DURATION));

        // set extent to which the node increases in both dimensions
        double d = -1 + 2 * rand.nextDouble(); // random number between -1 and 1
        scale.setToX(d);
        scale.setToY(d);

        ParallelTransition trans = new ParallelTransition(node, scale);
        trans.setCycleCount(Timeline.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();

    }
    /**
     * Returns a random bright opaque color.
     */
    private Color randomColor()  {
        Random rand = new Random();
        double r = rand.nextDouble();
        double g = rand.nextDouble();
        double b = rand.nextDouble();
        Color c = new Color(r, g, b, 1);
        return c.brighter();
    }

    public static void main(String[] args) {
        launch(args);

    }


}

