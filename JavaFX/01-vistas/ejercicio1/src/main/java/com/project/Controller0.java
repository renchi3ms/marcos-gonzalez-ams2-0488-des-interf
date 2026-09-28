package com.project;

import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller0 {

    @FXML
    private TextField ageText;

    @FXML
    private Text errorText;

    @FXML
    private TextField nameText;

    @FXML
    private Button nextButton;

    @FXML
    void checkNextView(ActionEvent event) {

        try {

            Main.name = nameText.getText().trim();
            Integer.parseInt(ageText.getText());
            Main.edat = ageText.getText();

            if (!Main.name.isEmpty() && !Main.edat.isEmpty()) {
                Controller1 view1 = (Controller1) UtilsViews.getController("View1");
                view1.setHello(Main.name, Main.edat);
                UtilsViews.setView("View1");
            } else {
                errorText.setText("El nombre está vacío");
            }

        } catch (Exception e) {
            errorText.setText("Edad incorrecta");
        }

    }

}
