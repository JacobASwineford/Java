package Misc.CustomClasses.Dumb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TowerDefenseGame extends Application {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private GridPane gameGrid;
    private VBox towerSelection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tower Defense Game");

        // Create game grid
        gameGrid = new GridPane();
        gameGrid.setHgap(10);
        gameGrid.setVgap(10);
        gameGrid.setPadding(new Insets(10));

        // Create tower selection panel
        towerSelection = new VBox();
        towerSelection.setSpacing(10);
        towerSelection.setPadding(new Insets(10));

        // Create buttons for different towers
        Button tower1Button = new Button("Tower 1");
        Button tower2Button = new Button("Tower 2");
        Button tower3Button = new Button("Tower 3");

        // Add button click event handlers
        tower1Button.setOnAction(e -> placeTower("Tower 1"));
        tower2Button.setOnAction(e -> placeTower("Tower 2"));
        tower3Button.setOnAction(e -> placeTower("Tower 3"));

        // Add buttons to the tower selection panel
        towerSelection.getChildren().addAll(tower1Button, tower2Button, tower3Button);

        // Create the main layout
        HBox root = new HBox(gameGrid, towerSelection);

        // Create the scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void placeTower(String towerType) {
        // Handle tower placement logic here
        System.out.println("Placing tower: " + towerType);
    }
}
