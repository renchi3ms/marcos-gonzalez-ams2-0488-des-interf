package com.project;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Controller_Item {

    @FXML
    private ImageView imgGame;

    @FXML
    private HBox itemBox;

    @FXML
    private Label labelGame;

    private String plot;

    @FXML
    void viewItem(MouseEvent event) {
        
    }

    public void setLabelGame(String name) {
        labelGame.setText(name);
    }

    public void setImgGame(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.imgGame.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
            e.printStackTrace();
        }
    }
}
