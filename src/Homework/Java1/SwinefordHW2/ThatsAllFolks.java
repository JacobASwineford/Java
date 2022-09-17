package Homework.Java1.SwinefordHW2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Produces a picture-still frame of the "That's all folks!" ending from old Looney-tunes cartoons.
 *
 * This project is not finished. The hands are not Defined or not put in, nor
 * is the bow or any of the features of the face or coat.
 * In addition, there are many lines of code that serve as redundant and the
 * names for objects could be better.
 *
 * If I'm ever inclined to come back to this, I will. I don't think so though.
 * I've learned much from this project, and that's enough for me.
 *
 * @author Jacob Swineford
 */
public class ThatsAllFolks extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Colors for the back-round circles
        Color sceneColor = Color.rgb(86, 3, 11, 1);
        Color sceneColorOpaque = Color.rgb(86, 3, 11, .7);
        Color smallRingOne = Color.rgb(96, 4, 13, 1);
        Color smallRingOneOpaque = Color.rgb(94, 4, 13, .7);
        Color smallRingTwo = Color.rgb(107, 8, 13, 1);
        Color smallRingTwoOpaque = Color.rgb(107, 8, 13, .7);
        Color smallRingThree = Color.rgb(148, 19, 21, 1);
        Color smallRingThreeOpaque = Color.rgb(168, 19, 21, .7);
        Color smallRingFour = Color.rgb(226, 85, 20, 1);
        Color smallRingFourOpaque = Color.rgb(226, 85, 20, .7);
        Color smallRingFive = Color.rgb(253, 157, 10, 1);
        Color ring6 = Color.rgb(250, 209, 74, 1);
        Color greenMiddle = Color.rgb(134, 209, 81, 1);
        Color blackish = Color.rgb(1, 1, 1, .3);
        Color blackishish = Color.rgb(1, 1, 1, .2);

        // Colors for Porky
        Color porkyBody = Color.rgb(231, 165, 133, 1);
        Color porkyCoat = Color.rgb(22, 32, 101, 1);
        Color porkyBow = Color.rgb(167, 14, 3, 1);
        Color porkyMouthInside = Color.rgb(147, 25, 21, 1);
        Color porkyMouthTongue = Color.rgb(218, 48, 66, 1);
        Color porkyEyeWhites = Color.rgb(242, 248, 246, 1); //pupils are Color.BLACK

        // Shadow effects for ring 3
        DropShadow dropShadowRing3Right = new DropShadow();
        dropShadowRing3Right.setOffsetX(15);
        dropShadowRing3Right.setOffsetY(0.0);
        dropShadowRing3Right.setColor(blackish);

        DropShadow dropShadowRing3Left = new DropShadow();
        dropShadowRing3Left.setOffsetX(-15.0);
        dropShadowRing3Left.setOffsetY(0);
        dropShadowRing3Left.setColor(blackish);

        DropShadow dropShadowRing3Bottom = new DropShadow();
        dropShadowRing3Bottom.setOffsetX(0);
        dropShadowRing3Bottom.setOffsetY(15);
        dropShadowRing3Bottom.setColor(blackish);

        DropShadow dropShadowRing3Top = new DropShadow();
        dropShadowRing3Top.setOffsetX(0);
        dropShadowRing3Top.setOffsetY(-15);
        dropShadowRing3Top.setColor(blackish);

        // Shadow effects for ring 4
        DropShadow dropShadowRing4Right = new DropShadow();
        dropShadowRing4Right.setOffsetX(15);
        dropShadowRing4Right.setOffsetY(0.0);
        dropShadowRing4Right.setColor(blackish);

        DropShadow dropShadowRing4Left = new DropShadow();
        dropShadowRing4Left.setOffsetX(-15.0);
        dropShadowRing4Left.setOffsetY(0);
        dropShadowRing4Left.setColor(blackish);

        DropShadow dropShadowRing4Bottom = new DropShadow();
        dropShadowRing4Bottom.setOffsetX(0);
        dropShadowRing4Bottom.setOffsetY(15);
        dropShadowRing4Bottom.setColor(blackish);

        DropShadow dropShadowRing4Top = new DropShadow();
        dropShadowRing4Top.setOffsetX(0);
        dropShadowRing4Top.setOffsetY(-15);
        dropShadowRing4Top.setColor(blackish);

        // Shadow effects for ring 5
        DropShadow dropShadowRing5Right = new DropShadow();
        dropShadowRing5Right.setOffsetX(15);
        dropShadowRing5Right.setOffsetY(0);
        dropShadowRing5Right.setColor(blackishish);

        DropShadow dropShadowRing5Left = new DropShadow();
        dropShadowRing5Left.setOffsetX(-15);
        dropShadowRing5Left.setOffsetY(0);
        dropShadowRing5Left.setColor(blackishish);

        DropShadow dropShadowRing5Bottom = new DropShadow();
        dropShadowRing5Bottom.setOffsetX(0);
        dropShadowRing5Bottom.setOffsetY(15);
        dropShadowRing5Bottom.setColor(blackishish);

        DropShadow dropShadowRing5Top = new DropShadow();
        dropShadowRing5Top.setOffsetX(0);
        dropShadowRing5Top.setOffsetY(-15);
        dropShadowRing5Top.setColor(blackishish);

        // Declaring the width, height, and color of the scene
        Pane root = new Pane();
        final double SCENE_WIDTH = 1400.0;
        final double SCENE_HEIGHT = 800.0;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, sceneColor);

        // Defining Light sources
        Light.Distant lightLight = new Light.Distant(90, 90, Color.TAN.brighter());
        Light.Distant lightDark = new Light.Distant(90, 90, Color.TAN.darker());

        Lighting lighting = new Lighting();
        lighting.setLight(lightLight);
        lighting.setSurfaceScale(2.5);

        Lighting lightingDark = new Lighting();
        lightingDark.setLight(lightDark);
        lightingDark.setSurfaceScale(2.5);

        // Making the back-round circles and adding effects
        Circle ring1DefineBig = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 700, sceneColorOpaque);
        Circle ring1DefineSmall = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 690, smallRingOne);
        ring1DefineBig.setEffect(lighting);

        Circle ring2DefineBig = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 585, smallRingOneOpaque);
        Circle ring2DefineSmall = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 570, smallRingTwo);
        ring2DefineBig.setEffect(lighting);

        Circle ring3DefineBig = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 483, smallRingTwoOpaque);
        Circle ring3DefineSmall = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 455, smallRingThree);
        Circle ring3DefineSmallSF = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 455, smallRingThree);
        Circle ring3DefineSmallSF2 = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 455, smallRingThree);
        Circle ring3DefineSmallSF3 = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 455, smallRingThree);
        ring3DefineBig.setEffect(lighting);
        ring3DefineSmall.setEffect(dropShadowRing3Right);
        ring3DefineSmallSF.setEffect(dropShadowRing3Bottom);
        ring3DefineSmallSF2.setEffect(dropShadowRing3Left);
        ring3DefineSmallSF3.setEffect(dropShadowRing3Top);

        Circle ring4DefineBig = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 390, smallRingThreeOpaque);
        Circle ring4DefineSmall = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 370, smallRingFour);
        Circle ring4DefineSmallSF = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 370, smallRingFour);
        Circle ring4DefineSmallSF2 = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 370, smallRingFour);
        Circle ring4DefineSmallSF3 = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 370, smallRingFour);
        ring4DefineBig.setEffect(lighting);
        ring4DefineSmall.setEffect(dropShadowRing4Right);
        ring4DefineSmallSF.setEffect(dropShadowRing4Left);
        ring4DefineSmallSF2.setEffect(dropShadowRing4Bottom);
        ring4DefineSmallSF3.setEffect(dropShadowRing4Top);

        Circle ring5DefineBig = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 320, smallRingFourOpaque);
        Circle ring5DefineSmall = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 300, smallRingFive);
        Circle ring5DefineSmallSF = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 300, smallRingFive);
        Circle ring5DefineSmallSF2 = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 300, smallRingFive);
        Circle ring5DefineSmallSF3 = new Circle(SCENE_WIDTH / 2,SCENE_HEIGHT / 2, 300, smallRingFive);
        ring5DefineBig.setEffect(lighting);
        ring5DefineSmall.setEffect(dropShadowRing5Right);
        ring5DefineSmallSF.setEffect(dropShadowRing5Bottom);
        ring5DefineSmallSF2.setEffect(dropShadowRing5Left);
        ring5DefineSmallSF3.setEffect(dropShadowRing5Top);

        Circle ring6DefineBig = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 265, ring6);
        Circle middleRing = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 245, greenMiddle);

        // Used for subtraction
        Shape ringOfTransparency = Shape.subtract(ring1DefineBig, middleRing);

        // Make the body of porky the pig-cat
        Ellipse head = new Ellipse((SCENE_WIDTH /2) + 20, (SCENE_HEIGHT / 2) - 60, 101, 88);
        head.setFill(Color.BLUE);
        head.setRotate(20);
        head.setStroke(Color.BLACK);
        head.setStrokeWidth(2);

        Ellipse head2 = new Ellipse((SCENE_WIDTH /2) + 25,(SCENE_HEIGHT / 2) + 21, 80, 40);
        head2.setFill(Color.BLUE);
        head2.setRotate(8);
        head2.setStroke(Color.BLACK);
        head2.setStrokeWidth(2);

        Rectangle head3 = new Rectangle((SCENE_WIDTH /2) + 80,(SCENE_HEIGHT / 2) - 26 , 22, 45);
        head3.setFill(Color.BLUE); // Union covers up this shape
        head3.setRotate(31.8);

        Shape newHead = Shape.union(head, head2);
        Shape newHead2 = Shape.union(newHead, head3);
        newHead2.setFill(porkyBody);
        newHead2.setStroke(Color.BLACK);
        newHead2.setStrokeWidth(2);

        Rectangle armRight = new Rectangle((SCENE_WIDTH /2) + 50, (SCENE_HEIGHT / 2) - 50, 150, 70);
        armRight.setFill(porkyCoat); // Union covers up this shape
        armRight.setRotate(-26);

        Ellipse armRight2 = new Ellipse((SCENE_WIDTH /2) + 191.5, (SCENE_HEIGHT / 2) - 47.6, 34.5, 20);
        armRight2.setFill(porkyCoat.brighter());
        armRight2.setRotate(64);

        Shape rightJacketSleeve = Shape.union(armRight, armRight2);
        rightJacketSleeve.setFill(porkyCoat);
        rightJacketSleeve.setStroke(Color.BLACK);
        rightJacketSleeve.setStrokeWidth(2);
        rightJacketSleeve.setTranslateX(-10); //Privy to change
        rightJacketSleeve.setTranslateY(10); //Privy to change

        Rectangle bodyJacketRight = new Rectangle((SCENE_WIDTH / 2) + 35, (SCENE_HEIGHT / 2) + 50, 50, 200);
        Ellipse subCircleCoatRight = new Ellipse((SCENE_WIDTH / 2) + 95, (SCENE_HEIGHT / 2) + 75, 20, 35);
        subCircleCoatRight.setRotate(30);
        Rectangle subRectangle = new Rectangle((SCENE_WIDTH / 2) + 70.35, (SCENE_HEIGHT / 2) + 85, 50, 200);

        Rectangle belly = new Rectangle((SCENE_WIDTH / 2) - 62, (SCENE_HEIGHT / 2) + 25, 110, 300);
        Ellipse bellyDefiner = new Ellipse((SCENE_WIDTH / 2) - 285, (SCENE_HEIGHT / 2) + 100, 260, 400);
        Shape belly2  = Shape.intersect(belly, bellyDefiner);
        Shape bellyTrue = Shape.subtract(belly, ringOfTransparency);
        Shape bellyTrue2 = Shape.subtract(bellyTrue, belly2);
        bellyTrue2.setFill(porkyBody);
        bellyTrue2.setStroke(Color.BLACK);
        bellyTrue2.setStrokeWidth(2);

        Polygon jacketLeft = new Polygon((SCENE_WIDTH / 2) - 25, (SCENE_HEIGHT / 2) + 10,
                (SCENE_WIDTH / 2) - 25, (SCENE_HEIGHT / 2) + 300,
                (SCENE_WIDTH / 2) - 350 ,(SCENE_HEIGHT / 2) +300);
        Shape jacketLeftTrue = Shape.subtract(jacketLeft, ringOfTransparency);
        jacketLeftTrue.setFill(porkyCoat);
        jacketLeftTrue.setStroke(Color.BLACK);
        jacketLeftTrue.setStrokeWidth(2);

        Shape bodyJacketRight2 = Shape.subtract(bodyJacketRight, ringOfTransparency);
        Shape bodyJacketRight3 = Shape.subtract(bodyJacketRight2, subCircleCoatRight);
        Shape bodyJacketRight4 = Shape.subtract(bodyJacketRight3, subRectangle);
        bodyJacketRight4.setFill(porkyCoat);
        bodyJacketRight4.setStroke(Color.BLACK);
        bodyJacketRight4.setStrokeWidth(2);

        Rectangle armLeft = new Rectangle((SCENE_WIDTH /2) - 215.8, (SCENE_HEIGHT / 2) + 31.2, 300, 65);
        armLeft.setRotate(-46);

        Ellipse shoulderCorner = new Ellipse((SCENE_WIDTH /2) - 42, (SCENE_HEIGHT / 2) + 28, 30, 17);
        Rectangle subRectangle2 = new Rectangle((SCENE_WIDTH / 2) - 80, (SCENE_HEIGHT / 2), 100, 20);

        Shape leftJacketSleeve = Shape.subtract(armLeft, ringOfTransparency);
        Shape leftJacketSleeve2 = Shape.subtract(leftJacketSleeve, subRectangle2);
        Shape leftJacketSleeve3 = Shape.union(leftJacketSleeve2, shoulderCorner);
        leftJacketSleeve3.setFill(porkyCoat);
        leftJacketSleeve3.setStroke(Color.BLACK);
        leftJacketSleeve3.setStrokeWidth(2);

        Polygon handRight = new Polygon((SCENE_WIDTH / 2) + 173.2, (SCENE_HEIGHT / 2) - 130,
                (SCENE_WIDTH / 2) + 250, (SCENE_HEIGHT / 2) - 110,
                (SCENE_WIDTH / 2) + 290, (SCENE_HEIGHT / 2) - 50,
                (SCENE_WIDTH / 2) + 173.2, (SCENE_HEIGHT / 2) - 20);
        handRight.setFill(porkyBody);
        handRight.setTranslateX(-5);
        handRight.setTranslateY(0);
        handRight.setStroke(Color.BLACK);

        Arc coatDefiner = new Arc((SCENE_WIDTH /2) + 185, (SCENE_HEIGHT / 2) - 45,
                15, 25, 126, 160);
        coatDefiner.setFill(Color.TRANSPARENT);
        coatDefiner.setStroke(Color.BLACK);
        coatDefiner.setStrokeWidth(2);
        coatDefiner.setType(ArcType.OPEN);
        coatDefiner.setRotate(-20);

        Arc coatDefiner2 = new Arc((SCENE_WIDTH /2) + 185, (SCENE_HEIGHT / 2) - 45,
                35, 45, 126, 160);
        coatDefiner2.setFill(Color.TRANSPARENT);
        coatDefiner2.setStroke(Color.BLACK);
        coatDefiner2.setStrokeWidth(2);
        coatDefiner2.setType(ArcType.CHORD);
        coatDefiner2.setRotate(-20);

        Shape coatDefinerTrue = Shape.subtract(coatDefiner2, coatDefiner);
        coatDefinerTrue.setFill(Color.TRANSPARENT);
        coatDefinerTrue.setStroke(Color.BLACK);

        Ellipse handDefiner = new Ellipse((SCENE_WIDTH / 2) + 235, (SCENE_HEIGHT / 2) - 25, 60, 33);
        handDefiner.setStroke(Color.BLACK);
        handDefiner.setFill(Color.TRANSPARENT);;
        handDefiner.setRotate(-25);

        Shape rightHand = Shape.subtract(handRight, coatDefinerTrue);
        Shape rightHand2 = Shape.subtract(rightHand, handDefiner);
        rightHand2.setFill(porkyBody);
        rightHand2.setStroke(Color.BLACK);

        // Adding the back-round before Porky
        root.getChildren().addAll(ring1DefineBig, ring1DefineSmall, ring2DefineBig,
                ring2DefineSmall, ring3DefineBig, ring3DefineSmall, ring3DefineSmallSF,
                ring3DefineSmallSF2, ring3DefineSmallSF3, ring4DefineBig, ring4DefineSmall,
                ring4DefineSmallSF, ring4DefineSmallSF2, ring4DefineSmallSF3, ring5DefineBig,
                ring5DefineSmall, ring5DefineSmallSF, ring5DefineSmallSF2, ring5DefineSmallSF3,
                ring6DefineBig, middleRing);

        // Adding porky
        root.getChildren().addAll( jacketLeftTrue,bellyTrue2, leftJacketSleeve3, rightJacketSleeve,
                bodyJacketRight4, newHead2, coatDefiner, rightHand2, handDefiner);

        primaryStage.setTitle("That's all folks!");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
