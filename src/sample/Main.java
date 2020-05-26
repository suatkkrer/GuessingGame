package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Parent root;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        Image image = new Image("icon/GuessingIcon.jpeg");
//        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Guessing Game");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Main.root = root;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
