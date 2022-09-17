package JavaDemos.Java2.ch9.ch9h;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;

/**
 * Displays a triangle with a linear gradient and a fade transition. The corners
 * are selected by the user through mouse clicks. The triangle can be dragged with
 * the mouse.
 *
 * @author Jacob Swineford
 */
public class GlowingTriangle extends Application {

    private final Polygon triangle = new Polygon();

    // coordinates of the mouse cursor during drag events
    private double cursorX;
    private double cursorY;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int size = 500;
        Scene scene = new Scene(root, size, size, BEIGE);

        // Add a drop shadow
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(GRAY);
        triangle.setEffect(dropShadow);

        root.setOnMouseClicked(event -> {
            // reset if right button clicked
            if (event.getButton() == MouseButton.SECONDARY) {
                root.getChildren().clear();
                triangle.getPoints().clear();
                return;
            }
            // add point to the polygon at the location of the click
            double x = event.getX();
            double y = event.getY();
            triangle.getPoints().addAll(x, y);

            // If fewer than three points have been added, draw a dot at the
            // location of the click. Otherwise, remove the two dots, remove
            // this event handler, and add the triangle to the scene.
            if (triangle.getPoints().size() < 6) {
                Circle dot = new Circle(x, y, 4);
                dot.setFill(BLACK);
                root.getChildren().add(dot);
            } else {
                root.getChildren().clear();
                triangle.setFill(getLinearGradient(BLACK, BLUE));
                getFadeTransition().play();
                root.getChildren().add(triangle);
                root.setOnMouseClicked(null);
            }
        });

        triangle.setOnMouseDragged(event -> {
            triangle.setTranslateX(event.getSceneX() - cursorX);
            triangle.setTranslateY(event.getSceneY() - cursorY);
        });

        triangle.setOnMousePressed(event -> {
            triangle.setCursor(Cursor.MOVE);
            cursorX = event.getSceneX() - cursorX;
            cursorY = event.getSceneY() - cursorY;
        });

        triangle.setOnMouseReleased(event -> {
            triangle.setCursor(Cursor.DEFAULT);
            cursorX = event.getSceneX() - cursorX;
            cursorY = event.getSceneY() - cursorY;
        });

        primaryStage.setTitle("Glowing Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    /**
     * Returns a linear gradient with a specified starting and ending color.
     */
    private static LinearGradient getLinearGradient(Color start, Color end) {
        Stop[] stops = new Stop[2];
        stops[0] = new Stop(0, start);
        stops[1] = new Stop(1, end);

        // (0, 0) and (1, 1) are scaled coordinates with respect to the bounding
        // box of the shape to which this gradient is applies.
        return new LinearGradient(0, 0, 1, 1,
                true, CycleMethod.REFLECT, stops);
    }


    /**
     * Returns a fade transition configured for the triangle.
     */
    private FadeTransition getFadeTransition() {
        FadeTransition ft = new FadeTransition(Duration.millis(500), triangle);
        ft.setFromValue(1.0);
        ft.setToValue(.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        return ft;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

