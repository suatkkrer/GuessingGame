package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Online implements Initializable {
    GameOperations gameOperations = new GameOperations();
    String userName = "";
    @FXML
    private TableView<ModelRoom> table;
    @FXML
    private TableColumn<ModelRoom, Integer> colRoom;
    @FXML
    private TableColumn<ModelRoom, String> colEmpty;
    @FXML
    private TableColumn <ModelRoom, Integer> colUser1;
    @FXML
    private TableColumn <ModelRoom, Integer> colUser2;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;

    public TextField getUsernameField() {
        return usernameField;
    }

    public GameOperations getGameOperations() {
        return gameOperations;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    ObservableList<ModelRoom> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = null;
        try {
            connection = GameOperations.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert connection != null;
            ResultSet rs = connection.createStatement().executeQuery("select * from room");

            while (rs.next()){
                observableList.add(new ModelRoom(rs.getInt("roomNumb"),rs.getInt("isEmpty")
                        ,rs.getString( "user1Name"), rs.getString("user2Name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumb"));
        colEmpty.setCellValueFactory(new PropertyValueFactory<>("isEmpty"));
        colUser1.setCellValueFactory(new PropertyValueFactory<>("user1Name"));
        colUser2.setCellValueFactory(new PropertyValueFactory<>("user2Name"));

        table.setItems(observableList);
    }

    @FXML
    public void createRoom() throws SQLException {
//        gameOperations.createRoom();
    }
    public String username(){
        return usernameField.getText();
    }

    @FXML
    public void joinButton() throws IOException, SQLException {
        setUserName(getUsernameField().getText());
        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("OnlineGame.fxml"));
        Parent root3 = (Parent) fxmlLoader3.load();
        OnlineGame onlineGame = fxmlLoader3.getController();
        onlineGame.setOnline(this);
        if (usernameField.getText().length() == 0) {
            usernameLabel.setText("Username field can not be empty");
        } else {
            onlineGame.myFunction(usernameField.getText());
            gameOperations.createRoom();
            try {
                gameOperations.sendValuesToDatabase(usernameField.getText());
            } catch (Exception e) {
                usernameLabel.setText("Please Enter Different Username");
            }
            ModelRoom selectedModel = table.getSelectionModel().getSelectedItem();
            onlineGame.setSelectedRoom(selectedModel.getRoomNumb());
            if (selectedModel.getIsEmpty() == 2){
                usernameLabel.setText("Room is Full Please Select Another Room");
            } else {
                Stage stage3 = new Stage();
                stage3.setScene(new Scene(root3));
                stage3.setTitle("Guessing Game");
                stage3.show();
                Controller.getStage().close();
            }
        }
    }
}
