package Homework.Java1.SwinefordHW2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 *Produces a picture of a cat on a pale blue back-round.
 * @author Jacob Swineford
 */
public class CatPicture extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 600;

        //Defining colors of different parts
        Color backroundColor = Color.rgb(175, 195, 221, 1);
        Color headColor = Color.rgb(253, 214, 1, 1);
        Color earColor = Color.rgb(238, 228, 138, 1);

        // Declaring a new Scene
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, backroundColor);

        // Shape the head and color in
        Ellipse head = new Ellipse((SCENE_WIDTH / 2.0), (SCENE_HEIGHT / 2.0), 250, 220); //center x, center y, radius x, radius y
        head.setFill(headColor);
        head.setStroke(Color.BLACK);
        head.setStrokeWidth(5);

        //Shape the ears and color in
        Polygon earLeft = new Polygon(133, 83, 183, 228, 283, 148); //top, left, right
        earLeft.setFill(earColor);
        earLeft.setStroke(Color.BLACK);
        earLeft.setStrokeWidth(4.5);
        earLeft.setStrokeLineJoin(StrokeLineJoin.ROUND);

        Polygon earRight = new Polygon(SCENE_WIDTH-133, 83, SCENE_WIDTH-183, 228, SCENE_WIDTH-283, 148);
        earRight.setFill(earColor);
        earRight.setStroke(Color.BLACK);
        earRight.setStrokeWidth(4.5);
        earRight.setStrokeLineJoin(StrokeLineJoin.ROUND);

        // Shape the eyes and color in
        Circle leftEye = new Circle(318, 220, 25);
        leftEye.setFill(Color.BLACK);
        Line leftEyeSlit = new Line(318, 195, 318, 295);
        leftEyeSlit.setStroke(headColor);
        leftEyeSlit.setStrokeWidth(4);

        Circle rightEye = new Circle(482, 220, 25);
        rightEye.setFill(Color.BLACK);
        Line rightEyeSlit = new Line(482, 195, 482, 295);
        rightEyeSlit.setStroke(headColor);
        rightEyeSlit.setStrokeWidth(4);

        // Shape the nose and color in
        Polygon nose = new Polygon((SCENE_WIDTH / 2.0), 410, (SCENE_WIDTH / 2.0) - 23, 375,
                (SCENE_WIDTH / 2.0) + 23, 375); // Bottom, left, right
        nose.setFill(Color.BLACK);

        //make the mouth out of arcs
        Arc mouthLeft = new Arc((SCENE_WIDTH / 2) - 26, 405, 27, 27, 180, 180);
        mouthLeft.setType(ArcType.ROUND);
        mouthLeft.setFill(Color.BLACK);

        Arc mouthLeft2 = new Arc(((SCENE_WIDTH) / 2) - 26, 405, 22, 22, 180, 180);
        mouthLeft2.setFill(headColor);

        Arc mouthRight = new Arc((SCENE_WIDTH / 2) + 26, 405, 27, 27, 180, 180);
        mouthRight.setType(ArcType.ROUND);
        mouthRight.setFill(Color.BLACK);

        Arc mouthRight2 = new Arc(((SCENE_WIDTH) / 2) + 26, 405, 22, 22, 180, 180);
        mouthRight2.setFill(headColor);

        // Make the whiskers
        Line whiskerLeft1 = new Line(85, 334, 320, 395);
        Line whiskerLeft2 = new Line(85, 410, 320, 410);
        Line whiskerLeft3 = new Line(85, 486, 320, 425);

        Line whiskerRight1 = new Line(715, 334, 480, 395);
        Line whiskerRight2 = new Line(715, 410, 480, 410);
        Line whiskerRight3 = new Line(715, 486, 480, 425);

        whiskerLeft1.setStrokeWidth(4);
        whiskerLeft2.setStrokeWidth(4);
        whiskerLeft3.setStrokeWidth(4);
        whiskerRight1.setStrokeWidth(4);
        whiskerRight2.setStrokeWidth(4);
        whiskerRight3.setStrokeWidth(4);

        // obtain nodes to put into the pane
        root.getChildren().addAll(earLeft, earRight, head, mouthLeft, mouthRight, mouthRight2,
                mouthLeft2, nose, leftEye, leftEyeSlit, rightEye, rightEyeSlit, whiskerLeft1,
                whiskerLeft2, whiskerLeft3, whiskerRight1, whiskerRight2, whiskerRight3);

        primaryStage.setTitle("Whiskers the Cat");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}