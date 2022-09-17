package Misc.GUIS;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Jacob Swineford
 */
public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 900;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        Rectangle r = new Rectangle(100, 100, Color.GREEN);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), r);
        fade.setFromValue(1);
        fade.setToValue(.2);
        fade.setCycleCount(2);
        fade.setAutoReverse(true);

        Rectangle r2 = new Rectangle(100, 100, Color.BLUE);
        r2.setX(100);

        FillTransition fill = new FillTransition(Duration.seconds(2), r2);
        fill.setFromValue(Color.BLUE);
        fill.setToValue(Color.RED);
        fill.setCycleCount(2);
        fill.setAutoReverse(true);

        Rectangle r3 = new Rectangle(100, 100, Color.RED);
        r3.setX(200);

        // based on translation, not setX and setY
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), r3);
        translate.setFromX(0);
        translate.setToX(100);
        translate.setCycleCount(2);
        translate.setAutoReverse(true);

        Rectangle r4 = new Rectangle(100, 100, Color.YELLOW);
        r4.setX(400);

        RotateTransition rotate = new RotateTransition(Duration.seconds(2), r4);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);

        Rectangle r5 = new Rectangle(100, 100, Color.TAN);
        r5.setX(500);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(2), r5);
        scale.setFromX(1);
        scale.setToX(.1);
        scale.setFromY(1);
        scale.setToY(.1);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        Rectangle r6 = new Rectangle(100, 100, Color.GAINSBORO);
        r6.setX(600);
        Circle p = new Circle(650, 50, 100);
        p.setFill(Color.TRANSPARENT);
        p.setStroke(Color.BLACK);
        p.setRotate(50);

        PathTransition path = new PathTransition(Duration.seconds(2), p, r6);
        path.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        path.setCycleCount(2);
        path.setAutoReverse(true);
        path.setOnFinished(event -> {
            r6.setTranslateX(0);
            r6.setTranslateY(0);
            r6.setRotate(0);
        });

        Rectangle r7 = new Rectangle(100, 100, Color.SLATEGRAY);
        r7.setY(100);

        ScaleTransition se1 = new ScaleTransition(Duration.seconds(1));
        se1.setFromX(1);
        se1.setToX(.2);
        se1.setFromY(1);
        se1.setToY(.2);
        se1.setCycleCount(2);
        se1.setAutoReverse(true);

        FillTransition se2 = new FillTransition(Duration.seconds(.5));
        se2.setFromValue(Color.SLATEGRAY);
        se2.setToValue(Color.LIGHTPINK);
        se2.setCycleCount(2);
        se2.setAutoReverse(true);

        RotateTransition se3 = new RotateTransition(Duration.seconds(.5));
        se3.setFromAngle(0);
        se3.setToAngle(360);
        se3.setCycleCount(2);
        se3.setAutoReverse(true);

        SequentialTransition sequence = new SequentialTransition();
        sequence.setNode(r7);
        sequence.getChildren().addAll(se1, se2, se3);
        sequence.setCycleCount(1);
        sequence.setAutoReverse(true);

        Rectangle r8 = new Rectangle(100, 100, Color.DEEPPINK);
        r8.setX(100);
        r8.setY(100);

        ScaleTransition p1 = new ScaleTransition(Duration.seconds(2));
        p1.setFromX(1);
        p1.setToX(.2);
        p1.setFromY(1);
        p1.setToY(.2);
        p1.setCycleCount(2);
        p1.setAutoReverse(true);

        FillTransition p2 = new FillTransition(Duration.seconds(2));
        p2.setFromValue(Color.DEEPPINK);
        p2.setToValue(Color.LIGHTPINK);
        p2.setCycleCount(2);
        p2.setAutoReverse(true);

        RotateTransition p3 = new RotateTransition(Duration.seconds(2));
        p3.setFromAngle(0);
        p3.setToAngle(-360);
        p3.setCycleCount(2);
        p3.setAutoReverse(true);

        ParallelTransition parallel = new ParallelTransition();
        parallel.setNode(r8);
        parallel.getChildren().addAll(p1, p2, p3);
        parallel.setCycleCount(1);

        Line line = new Line(500, 500, 600, 600);
        line.setStroke(Color.BLACK);
        Rectangle box = new Rectangle(30, 30, Color.BLUEVIOLET);
        box.setX(500);
        box.setY(500);

        PathTransition move = new PathTransition(Duration.seconds(2), line, box);
        move.setCycleCount(2);
        move.setAutoReverse(true);


        root.getChildren().addAll(r, r2, r3, r4, r5, p, r6, r7, r8, line, box);

        scene.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY: {
                    fade.play();
                    fill.play();
                    translate.play();
                    rotate.play();
                    scale.play();
                    path.play();
                    sequence.play();
                    parallel.play();
                    move.play();
                    break;
                }
                case SECONDARY: {
                    System.out.println(r3.getTranslateX());
                    System.out.println(r4.getRotate());
                }
            }
        });

        primaryStage.setTitle("Transitions");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
