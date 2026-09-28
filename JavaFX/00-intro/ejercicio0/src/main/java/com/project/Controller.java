package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Button buttonCancell;

    @FXML
    private Button buttonDivision;

    @FXML
    private Button buttonNum0;

    @FXML
    private Button buttonNum1;

    @FXML
    private Button buttonNum2;

    @FXML
    private Button buttonNum3;

    @FXML
    private Button buttonNum4;

    @FXML
    private Button buttonNum5;

    @FXML
    private Button buttonNum6;

    @FXML
    private Button buttonNum7;

    @FXML
    private Button buttonNum8;

    @FXML
    private Button buttonNum9;

    @FXML
    private Button buttonOper;

    @FXML
    private Button buttonPlus;

    @FXML
    private Button buttonRest;

    @FXML
    private Button buttonSum;

    @FXML
    private Text textCounter;

    private Integer counter = 0;
    private Integer firstNumber = 0;
    private String operation = "";
    private boolean newNumber = true;

    @FXML
    void addNumber(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        int number = Integer.parseInt(clicked.getText());
        if (newNumber) {
            counter = number;
        } else {
            counter = counter * 10 + number;
        }
        newNumber = false;
        textCounter.setText(String.valueOf(counter));
    }

    @FXML
    void addOperation(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        operation = clicked.getText();
        firstNumber = counter;
        counter = 0;
        newNumber = true;

    }

    @FXML
    void calculate(ActionEvent event) {
        try {
            int resultado = 0;
            switch (operation) {
            case "+":
                resultado = firstNumber + counter;
                textCounter.setText(String.valueOf(resultado));
                break;

            case "-":
                resultado = firstNumber - counter;
                textCounter.setText(String.valueOf(resultado));
                break;
            
            case "*":
                resultado = firstNumber * counter;
                textCounter.setText(String.valueOf(resultado));
                break;

            case "/":
                resultado = firstNumber / counter;
                textCounter.setText(String.valueOf(resultado));
                break;
        
            default:
                break;
            }
            counter = resultado;
        } catch (Exception e) {
            textCounter.setText(String.valueOf("Error"));
        }

    }

    @FXML
    void clear(ActionEvent event) {
        counter = 0;
        firstNumber = 0;
        operation = "";
        newNumber = true;
        textCounter.setText("0");
    }

}
