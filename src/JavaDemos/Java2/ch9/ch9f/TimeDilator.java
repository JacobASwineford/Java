package JavaDemos.Java2.ch9.ch9f;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Suppose a spaceship races away from the earth at a constant velocity v for time t
 * according to a clock on a spaceship. Einstein's Special Theory of Relativity
 * tells us how much time (u) will have passed on the earth.
 *
 * t^2 / u^2 = 1 - (v^2 / c^2)
 *
 * This program displays the time elapsed on earth given v and t specified by the user.
 *
 * This version handles errors in user input and applies styles to the text.
 *
 * @author Jacob Swineford
 * @author Albert Einstein
 */
public class TimeDilator extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 450, 125);

        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        final double defaultVelocity = 0.5;
        final double defaultTime = 10;

        String baseStyle = "-fx-font-size: 12 px; -fx-font-weight: bold; ";
        String normalStyle = baseStyle + "-fx-text-fill: #2f4f4f";
        String errorStyle = baseStyle + "-fx-text-fill: #aa0000";

        Label vLabel = new Label("Velocity as a fraction of light speed");
        Text tLabel = new Text("Travel time in years");
        TextField vTextField = new TextField("" + defaultVelocity);
        TextField tTextField = new TextField("" + defaultTime);
        Button button = new Button("Elapsed Time on Earth");
        String initLabelText = getElapsedTime(defaultVelocity, defaultTime);
        Label resultLabel = new Label(initLabelText);
        resultLabel.setStyle(normalStyle);

        // place all nodes into root pane
        root.add(vLabel, 0, 0);
        root.add(vTextField, 1, 0);
        root.add(tLabel, 0, 1);
        root.add(tTextField, 1, 1);
        root.add(button, 0, 2);
        root.add(resultLabel, 1, 2);



        class ButtonHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                String vText = vTextField.getText();
                String tText = tTextField.getText();

                // check for empty text fields
                if(vText.isEmpty() || tText.isEmpty()) { // change this
                    resultLabel.setStyle(errorStyle);
                    resultLabel.setText("MISSING INPUT");
                    return;
                }

                if(!canBeParsed(vText) || !canBeParsed(tText)) {
                    resultLabel.setStyle(errorStyle);
                    resultLabel.setText("NON-NUMERIC INPUT");
                    return;
                }

                double v = Double.parseDouble(vText);
                double t = Double.parseDouble(tText);
                // Check for invalid numbers
                if (t < 0 || v < 0 || v >= 1) {
                    resultLabel.setStyle(errorStyle);
                    resultLabel.setText("NON-NUMERIC INPUT");
                    return;
                }

                resultLabel.setStyle(normalStyle);
                resultLabel.setText(getElapsedTime(v, t));
            }
        }
        button.setOnAction(new ButtonHandler());


        primaryStage.setTitle("Time Dilator");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    /**
     * Returns true if the given string can be parsed as a double.
     */
    private boolean canBeParsed(String str) {
        Scanner in = new Scanner(str);
        if(in.hasNextDouble()) {
            in.nextDouble();
            return !in.hasNext();
        }
        return false;
    }

    /**
     * Calculates the time dilation for an object moving at constant velocity v
     * (given as a fraction of light speed) for time t (in years).
     */
    private static String getElapsedTime(double v, double t) {
        double u = Math.sqrt(t * t / (1 - v * v));
        return String.format("%.2f years", u);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

