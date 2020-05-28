package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OnlineGame implements Initializable {
    Online online = new Online();
    GameOperations operations = new GameOperations();
    private int selectedRoom;
    int count = 0;
    int wrongPlace = 0;
    int result;

    @FXML
    private Label labelName, estimationLabel, enterNumberLabel, warning;
    @FXML
    private TextArea textFieldP1, textFieldP2;
    @FXML
    private Button sendValues, guessOnline;
    @FXML
    private TextField numberOnline2, createNumberField;

    public int getSelectedRoom() {
        return selectedRoom;
    }

    public void setOnline(Online online) {
        this.online = online;
    }

    public void setSelectedRoom(int selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printMethod("Your History");
//        printMethod2("Opponent's History");
        warning.setVisible(false);

    }

    public void myFunction(String text) {
        labelName.setText("Welcome " + text);
    }

    public void printMethod(String text) {
        textFieldP1.appendText(text + "\n");
    }

    public void printMethod2(String text) {
        textFieldP2.appendText(text + "\n");
    }

    public void printInt(int text) {
        textFieldP1.setText(text + "\n");
    }

    public void sendNumber() throws SQLException {

        String number = "if user 1, user 2 number ---- if user 2, user 1 number";
        String estimation = "if user 1 user 1 estimation ---- if user 2 user 2 estimation";

        while (true) {

            String[] splt = number.split("");
            String[] spltX = estimation.split("");

            for (int i = 0; i < 4; i++) {
                if (splt[i].equals(spltX[i])) {
                    count++;
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (splt[i].equals(spltX[j])) {
                        wrongPlace++;
                    }
                }
            }

            if (count == 4) {
                printMethod2("Congrats You Succeed");
                guessOnline.setDisable(true);
                break;
            }

            printMethod2(count + " number(s) are in the correct place ");

            if (wrongPlace != 0) {
                printMethod2((wrongPlace) + " number(s) are in the wrong place");
            }

            count = 0;

            if (wrongPlace != 0) {
                printMethod2("Your estimation is " + numberOnline2.getText());
                wrongPlace = 0;
                break;

            } else {
                printMethod2("Your estimation is " + numberOnline2.getText());
                wrongPlace = 0;
                break;
            }
        }
    }

    public void sendValues() throws SQLException {

        if (createNumberField.getText().length() != 4) {
            warning.setText("Please enter 4 digits number");
            warning.setVisible(true);
        } else {
            operations.sendNumberToDatabase(createNumberField.getText(), getSelectedRoom(), online.username());
            operations.userNumbers(getSelectedRoom());
            System.out.println(operations.Control);
            operations.joinRoom(getSelectedRoom());
            warning.setVisible(false);
            estimationLabel.setVisible(true);
            textFieldP1.setVisible(true);
//            textFieldP2.setVisible(true);
            sendValues.setVisible(false);
            guessOnline.setVisible(true);
            createNumberField.setVisible(false);
            numberOnline2.setVisible(true);
            enterNumberLabel.setVisible(false);

            if (operations.Control == 1){
                guessOnline.setDisable(false);
            } else {
                guessOnline.setDisable(true);
            }
        }
    }

    public void guessOnline() throws SQLException {

        if (numberOnline2.getText().length() != 4) {
            warning.setText("Please enter 4 digits number");
            warning.setVisible(true);
        } else {
            warning.setVisible(false);
            operations.guessNumber(getSelectedRoom(), numberOnline2.getText(), online.username());



            String number = operations.number1;
            String estimation = operations.estimation;

            while (true) {

                String[] splt = number.split("");
                String[] spltX = estimation.split("");

                for (int i = 0; i < 4; i++) {
                    if (splt[i].equals(spltX[i])) {
                        count++;
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == j) {
                            continue;
                        }
                        if (splt[i].equals(spltX[j])) {
                            wrongPlace++;
                        }
                    }
                }

                if (count == 4) {
                    printMethod("Congrats You Succeed");
                    guessOnline.setDisable(true);
                    break;
                }

                printMethod(count + " number(s) are in the correct place ");

                if (wrongPlace != 0) {
                    printMethod((wrongPlace) + " number(s) are in the wrong place");
                }

                count = 0;

                if (wrongPlace != 0) {
                    printMethod("Your estimation is " + numberOnline2.getText());
                    wrongPlace = 0;
                    break;

                } else {
                    printMethod("Your estimation is " + numberOnline2.getText());
                    wrongPlace = 0;
                    break;

                }
            }
        }
    }
}