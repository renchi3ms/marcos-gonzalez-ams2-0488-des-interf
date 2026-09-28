package com.project;

import java.util.Objects;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControllerItem {

    @FXML
    private ImageView imgGame;

    @FXML
    private HBox itemBox;

    @FXML
    private Label labelGame;

    private ControllerDesktop parentController;
    private JSONObject jsonData;

    @FXML
    void viewItem(MouseEvent event) {
        if (parentController != null && jsonData != null) {
            parentController.showItemDetails(jsonData);
        }
    }

    public void setParentController(ControllerDesktop controller) {
        this.parentController = controller;
    }

    public void setJsonData(JSONObject data) {
        this.jsonData = data;
    }

    public void setLabelTitle(String name) {
        labelGame.setText(name);
    }

    public void setImgItem(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.imgGame.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
            e.printStackTrace();
        }
    }
}
