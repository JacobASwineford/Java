package JavaDemos.Java2.ch9.ch9h;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Displays a random floating-point number between 0 and 1. Clicking
 * the button randomizes the number.
 *
 * @author Jacob Swineford
 */
public class NumberServer extends Application {

    private Label label = new Label();

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding((new Insets(10)));
        root.setHgap(10);
        Scene scene = new Scene(root);

        // Add button and label to root pane.
        Button button = new Button("Serve");
        root.add(button, 0, 0);
        root.add(label, 1, 0);
        setLabel();

        // style hte button and label
        String style = "-fx-font-size:24; -fx-font-weight:bold; -fx-text-fill: #1f2f4f";
        button.setStyle(style);
        label.setStyle(style);

        // lambda expression
        button.setOnAction( event -> setLabel() );

        primaryStage.setTitle("Number Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    /**
     * Randomizes the label wit ha floating point number between 0 and 1.
     */
    private void setLabel() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String str = String.format("%1.8f", rand.nextDouble());
        label.setText(str);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

