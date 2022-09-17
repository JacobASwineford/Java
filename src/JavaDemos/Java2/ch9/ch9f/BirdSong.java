package JavaDemos.Java2.ch9.ch9f;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Displays a picture of a bird selected by the user abd plays an audio clip of
 * the bird's song.
 *
 * @author Jacob Swineford
 */
public class BirdSong extends Application {

    private static final String[] birds = {
            "American_Goldfinch", "American_Robin", "Black-capped_Chickadee",
            "Canyon_Wren", "Northern_Cardinal", "White-throated_Sparrow",
            "Western_Meadowlark", "Wood_Thrush"
    };

    private final Image[] images = new Image[birds.length];
    private ImageView image = new ImageView(images[0]);

    private final AudioClip[] clips = new AudioClip[birds.length];
    private Button button = new Button("Play song");
    private RadioButton[] radioButtons = new RadioButton[birds.length];
    private VBox vbox = new VBox(8);

    /**
     * Initializes the arrays of images and audio clips.
     */
    public BirdSong() {
        for (int i = 0; i < birds.length; i++) {
            String imageName = "images/" + birds[i] + ".jpg";
            images[i] = new Image(imageName);

            // boil-plate code
            String audioName = "audio/" + birds[i] + ".wav";
            URL resource = getClass().getClassLoader().getResource(audioName);
            clips[i] = new AudioClip(resource.toString());
        }

    }

    @Override
    public void start(Stage primaryStage) {

        //containers
        BorderPane root = new BorderPane(); // top left center right bottom
        root.setPadding(new Insets(10));
        HBox hbox = new HBox(10); // holds VBox and ImageView
        FlowPane flowpane = new FlowPane(); // contains the button

        // arrange button in the scene
        flowpane.getChildren().add(button);
        flowpane.setAlignment(Pos.CENTER);
        flowpane.setPadding(new Insets(10, 0, 10, 0));
        root.setBottom(flowpane);

        hbox.getChildren().addAll(image, vbox);
        root.setCenter(hbox);

        initButton();
        initRadioButtons();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Bird Song");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    /**
     * Initializes the button to play an audio clip and registers an event handler.
     */
    private void initButton() {
        class ButtonHandler implements EventHandler<ActionEvent> {

            /**
             * Find the selected radio button abd play the corresponding
             * clip.
             */
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < birds.length; i++) {
                    if (radioButtons[i].isSelected()) {
                        clips[i].play();
                    }
                }
            }
        }
        button.setOnAction(new ButtonHandler());
    }

    /**
     * Creates all radio buttons abd registers event handlers for them.
     */
    private void initRadioButtons() {

        class RadioButtonHandler implements EventHandler<ActionEvent> {

            int i; // index of radio button in array RadioButton
            public RadioButtonHandler(int i) {
                this.i = i;
            }

            @Override
            public void handle(ActionEvent event) {
                image.setImage(images[i]);

                // turn off all audio clips
                for (AudioClip clip : clips) {
                    clip.stop();
                }
            }
        }

        // arrange the radio buttons in the scene
        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < birds.length; i++) {
            String name = birds[i].replace("_", " ");
            radioButtons[i] = new RadioButton(name);
            radioButtons[i].setOnAction(new RadioButtonHandler(i));
            toggleGroup.getToggles().add(radioButtons[i]); // mutual exclusivity
            vbox.getChildren().add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);
        image.setImage(images[0]);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

