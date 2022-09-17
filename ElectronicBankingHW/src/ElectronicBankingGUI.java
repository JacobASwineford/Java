import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
/**
 * @author Jacob Swineford
 */
public class ElectronicBankingGUI extends Application {

    private static Stage prim;
    private static Scene loginScene;
    private static Scene createAccScene;
    @Override
    public void start(Stage primaryStage) {
        prim = primaryStage;
        initLoginScene();
        initCreateAccScene();
        Pane root = new Pane();
        final int SCENE_WIDTH = 500;
        final int SCENE_HEIGHT = 300;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Rec
        primaryStage.setTitle("E-Banking");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void initLoginScene()
    {
        VBox v1 = new VBox();
        Text createAccText = new Text("Don't have an account?\nCreate one here!");
        Button createAccButton = new Button("Create account");
        createAccButton.setOnAction(event -> prim.setScene(createAccScene));
        Text loginTitle = new Text("\nLogin:");
        TextField usernamePrompt = new TextField("username");
        TextField passwordPrompt = new TextField("password");
        Button loginButton = new Button("Login");
        v1.getChildren().addAll(createAccText, createAccButton,
                loginTitle, usernamePrompt, passwordPrompt, loginButton);

        final int LOGIN_WIDTH = 280;
        final int LOGIN_HEIGHT = 220;
        loginScene = new Scene(v1, LOGIN_WIDTH, LOGIN_HEIGHT);
    }

    private static void initCreateAccScene()
    {
        VBox v1 = new VBox();
        Text createAccText = new Text("Enter the following:");
        TextField usernamePrompt = new TextField("username");
        TextField passwordPrompt = new TextField("password");
        Text confirmPasswordText = new Text("Confirm password:");
        TextField confirmPasswordPrompt = new TextField("password");
        Button createAccButton = new Button("Create Account");
        v1.getChildren().addAll(createAccText, usernamePrompt, passwordPrompt,
                confirmPasswordText, confirmPasswordPrompt, createAccButton);

        final int LOGIN_WIDTH = 280;
        final int LOGIN_HEIGHT = 200;
        createAccScene = new Scene(v1, LOGIN_WIDTH, LOGIN_HEIGHT);
    }

}
