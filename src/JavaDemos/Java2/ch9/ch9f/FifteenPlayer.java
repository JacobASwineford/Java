package JavaDemos.Java2.ch9.ch9f;

import JavaDemos.Java2.ch8.Fifteen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * A GunGame for the game of fifteen.
 * @author Jacob Swineford
 */
public class FifteenPlayer extends Application {

    private Fifteen game = new Fifteen(1);
    private Button[][] buttons = new Button[Fifteen.ROWS][Fifteen.COLS];

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        // the buttons are arranged in a grid pane.
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: BLACK");
        gridPane.setHgap(3);
        gridPane.setVgap(3);
        root.getChildren().add(gridPane);

        // each button displays a tile number. Clicking on a button causes
        // a move to be made in the game by sliding the corresponding tile.
        class ButtonHandler implements EventHandler<ActionEvent> {

            @Override
            public void handle(ActionEvent event) {
                Button button = (Button) event.getSource();
                int tile = Integer.parseInt(button.getText());
                game.slide(tile);
                setButtonText();

                if (game.over()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("GAME OVER");
                    alert.setHeaderText("You have solved the puzzle!");
                    alert.setContentText("Goodbye.");
                    alert.showAndWait();
                    Platform.exit();
                }
            }
        }
        ButtonHandler buttonHandler = new ButtonHandler();

        // create the buttons, style them, and register event handlers
        Font font = Font.font("Monotype", FontWeight.BOLD, 28);
        String buttonStyle = "-fx-border-color: SLATEGRAY; -fx-border-width: 2";
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new Button(); // when the [][] was created, the default values set to null
                buttons[i][j].setPrefSize(80, 80); // ignores scene size,
                buttons[i][j].setStyle(buttonStyle); // stylize button
                gridPane.add(buttons[i][j], j, i); // [][]arrays first select the y and then the x
              // setOnAction sets the action upon an event, and automatically updates the
                // application accordingly until the exit statement is reached
                buttons[i][j].setOnAction(buttonHandler); // sets the events that happen on the buttons to enable buttonHandler
                buttons[i][j].setFont(font);
            }
        }
        setButtonText();

        primaryStage.setTitle("Fifteen Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    /**
     * Sets the text on each button to the corresponding tile number.
     */
    private void setButtonText() {

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (game.tileAt(i, j) == Fifteen.EMPTY_SPACE) {
                    buttons[i][j].setVisible(false);
                } else {
                    buttons[i][j].setText("" + game.tileAt(i, j)); // buttons[i][j].setText(String.valueOf(game.tileAt(i, j)));
                    buttons[i][j].setVisible(true);
                }

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

