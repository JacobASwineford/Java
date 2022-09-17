package Misc.CustomClasses.ShutTheBox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Plays the game of Shut the Box.
 *
 * @author Jacob Swineford
 */
public class ShutTheBoxPlayer extends Application {

    private ShutTheBox s = new ShutTheBox();
    private Pane root = new StackPane();
    private Button[] faceUp = new Button[ShutTheBox.ORIGINAL_BOARD_LENGTH]; // OBL = 9
    private final Pane background = new StackPane();
    private Rectangle[] faceDown = new Rectangle[ShutTheBox.ORIGINAL_BOARD_LENGTH];

    private Color bottomBannerColor = Color.SANDYBROWN;
    private int round = 0;
    private int totalScore = 0;

    @Override
    public void start(Stage primaryStage) {

        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 500;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        final int BANNER_HEIGHT = 100;
        Rectangle banner = new Rectangle(SCENE_WIDTH, BANNER_HEIGHT);
        banner.setFill(Color.SANDYBROWN.darker());
        banner.setTranslateY((-SCENE_HEIGHT / 2.0) + BANNER_HEIGHT / 2.0);

        Text bannerText = new Text("Flip the numbers and get the lowest score.");
        Font font = Font.font("Monotype", 50);
        bannerText.setFont(font);
        bannerText.setTranslateY((-SCENE_HEIGHT / 2.0) + BANNER_HEIGHT / 2.0);

        Rectangle bottomBanner = new Rectangle(0, BANNER_HEIGHT,
                SCENE_WIDTH, SCENE_HEIGHT - BANNER_HEIGHT);
        bottomBanner.setFill(bottomBannerColor);
        bottomBanner.setTranslateY(BANNER_HEIGHT / 2.0);

        Font sumFont = Font.font("Monotype", 50);
        Text sumDisplay = new Text();
        sumDisplay.setFont(sumFont);
        sumDisplay.setTranslateY(SCENE_HEIGHT / 4.0);
        sumDisplay.setTranslateX(SCENE_WIDTH / 4.0);

        Text roundDisplay = new Text();
        roundDisplay.setFont(font);
        roundDisplay.setTranslateY(SCENE_HEIGHT / 4.0);
        roundDisplay.setTranslateX(-SCENE_WIDTH / 4.0);

        background.getChildren().addAll(bottomBanner, banner,
                bannerText, sumDisplay, roundDisplay);
        root.getChildren().add(background);

        class ButtonHandler implements EventHandler<ActionEvent> {

            @Override
            public void handle(ActionEvent event) {
                Button button = (Button) event.getSource();
                int n = Integer.parseInt(button.getText());
                if (s.isValidFlip(n)) {
                    s.flip(n);
                }
                if (s.sumIsZero()) {
                    s.roll();
                }
                displayButtons();
                displaySum(sumDisplay);

                if (!s.isPossible()) {
                    addToTotalScore(s.getBoardSum()); // special adding
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("GAME OVER");
                    String bodyMessage1 = "Your powers are weak, old man.";
                    String bodyMessage2 = "Your powers are sorta weak, old man.";
                    String bodyMessage3 = "Your powers are definitely not weak, old man.";
                    alert.setHeaderText("Your score for round " + round + " is: " +
                            s.getBoardSum() + "\nYour overall score is: " + totalScore);
                    if (s.getBoardSum() >= 10) {
                        alert.setContentText(bodyMessage1);
                    } else if(s.getBoardSum() == 0) {
                        alert.setContentText(bodyMessage3);
                    } else {
                        alert.setContentText(bodyMessage2);
                    }
                    alert.showAndWait();

                    s.reset();
                    s.roll();
                    for (Rectangle r : faceDown) {
                        r.setVisible(false);
                    }
                    displayButtons();
                    displaySum(sumDisplay);
                    displayRound(roundDisplay); // special adding
                }
            }
        }

        // pane containing user-interactive buttons
        Pane userPane = new StackPane();
        root.getChildren().add(userPane);

        Rectangle buttonBackground = new Rectangle(0, 0, 900, 100);
        buttonBackground.setTranslateY(-50);
        buttonBackground.setTranslateX(.5);
        buttonBackground.setFill(Color.BLACK);
        userPane.getChildren().add(buttonBackground);
        int x = -400;
        int y = -50;

        Font buttonFont = Font.font("Monotype", FontWeight.BOLD, 50);
        String buttonStyle = "-fx-background-color: SADDLEBROWN;" +
                " -fx-border-color: BLACK;" + " -fx-border-width: 1";
        for (int i = 0; i < ShutTheBox.ORIGINAL_BOARD_LENGTH; i++) {
            faceUp[i] = new Button();
            faceUp[i].setTranslateY(y);
            faceUp[i].setTranslateX(x);
            faceUp[i].setFont(buttonFont);
            faceUp[i].setStyle(buttonStyle);
            faceUp[i].setOnAction(new ButtonHandler());

            // set the face-down buttons graphic
            faceDown[i] = new Rectangle(100, 100);
            faceDown[i].setTranslateX(x);
            faceDown[i].setTranslateY(y + 100);
            faceDown[i].setStroke(Color.BLACK);
            faceDown[i].setStrokeWidth(1);
            faceDown[i].setFill(Color.SADDLEBROWN);
            faceDown[i].setVisible(false);
            root.getChildren().add(faceDown[i]);

            userPane.getChildren().addAll(faceUp[i], faceDown[i]);
            x += 100;
        }

        s.roll();
        displayButtons();
        displaySum(sumDisplay);
        displayRound(roundDisplay);

        primaryStage.setTitle("Shut the box");
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    /**
     * Displays the buttons used in the game of Shut the Box.
     */
    private void displayButtons() {
        for (int i = 0; i < ShutTheBox.ORIGINAL_BOARD_LENGTH; i++) {
            if (!s.getBoard().contains(i + 1)) {
                faceUp[i].setVisible(false);
                faceDown[i].setVisible(true);
            } else {
                faceUp[i].setText(String.valueOf(i + 1));
                faceUp[i].setVisible(true);
            }
        }
    }

    /**
     * displays the sum of the current Shut the Box game.
     */
    private void displaySum(Text sum) {
        sum.setText("Current Sum: " + s.getSum());
    }

    /**
     * displays the current round. each time this method is called,
     * the round increases by 1.
     */
    private void displayRound(Text roundDisplay) {
        round++;
        roundDisplay.setText("Current round: " + round);
    }

    /**
     * Updates the total score.
     */
    private void addToTotalScore(int i) {
        totalScore += i;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
