package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Computer implements Initializable {
    Random random = new Random();
    int deneme = 8;
    int count = 0;
    int wrongPlace = 0;

    @FXML
    private TextArea guessHistory;

    @FXML
    private Button sendNumber;

    @FXML
    private TextField guessedNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printMethod("Please Enter Your Estimation 4 Digits");
        printMethod("You have 8 chances");
    }
    public void printMethod(String text){
        guessHistory.appendText(text + "\n");
    }
    public void printInt(int text){
        guessHistory.setText(text + "\n");
    }


    public void sendNumb(){
        String guessNumb = guessedNum.getText();

        while (true) {
            int var = random.nextInt(8999) + 1000;
            int sonuc = var;

            String randomSt = Integer.toString(var);
            String[] splt = randomSt.split("");
            String[] spltX = guessNumb.split("");


            for (int i = 0; i < 4 ; i++) {
                if (splt[i].equals(spltX[i])){
                    count++;
                }
            }

            for (int i = 0; i < 4 ; i++) {
                for (int j = 0; j < 4 ; j++) {
                    if (i == j){
                        continue;
                    }
                    if (splt[i].equals(spltX[j])){
                        wrongPlace++;
                    }
                }
            }
            if (count == 4){
                printMethod("Congrats You Succeed");
                break;
            }
            if (deneme == 0){

                printInt(sonuc);
                printMethod("Was True Number");
                printMethod("Sorry you do not have any chance");
                break;
            }
            printMethod(count + " number(s) are in the correct place ");
            printMethod((wrongPlace) + " number(s) are in the wrong place");

            count = 0;
            deneme--;
            if (wrongPlace != 0) {
                printMethod("You have " + deneme + " chance(s) left.");
                printMethod("Your estimation is " +guessedNum.getText());
                wrongPlace = 0;
                break;
            } else {
                printMethod("\nYou have " + deneme + " chance(s) left.");
                printMethod("Your estimation is " +guessedNum.getText());
                wrongPlace = 0;
                break;
            }

        }
    }
}


































