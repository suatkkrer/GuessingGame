package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private static Parent root;
    private static Stage stage;

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Controller.root = root;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Controller.stage = stage;
    }

    @FXML
    public void playWithComp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Computer.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Guessing Game");
        stage.show();
        Main.getStage().close();
    }

    @FXML
    public void playOnline() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Online.fxml"));
        Parent root2 = (Parent) fxmlLoader2.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root2));
        stage1.setTitle("Guessing Game");
        stage1.show();
        Main.getStage().close();
        setStage(stage1);
    }
}
