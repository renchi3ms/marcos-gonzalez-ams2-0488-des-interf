package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller1 {

    @FXML
    private Button goBackButton;

    @FXML
    private Text helloText;

    @FXML
    void goBack(ActionEvent event) {
        Controller0 view0 = (Controller0) UtilsViews.getController("View0");
        UtilsViews.setView("View0");
    }

    public void setHello(String name, String age) {
        helloText.setText("Hola " + name + " tienes " + age + " años!");
    }

}
