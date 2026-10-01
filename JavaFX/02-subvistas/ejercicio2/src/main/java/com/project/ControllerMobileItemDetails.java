package com.project;

import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ControllerMobileItemDetails {

    @FXML
    private Button buttonBack;

    @FXML
    private Circle colorCircle;

    @FXML
    private HBox colorPanel;

    @FXML
    private ImageView imgView;

    @FXML
    private Label labelName;

    @FXML
    private Label labelText;

    @FXML
    private Label labelTitel;

    @FXML
    private VBox viewItem;

    @FXML
    void viewBack(ActionEvent event) {
        UtilsViews.setViewAnimating("MobileItem");
    }

    public void setName(String name) {
        labelTitel.setText(name);
        labelName.setText(name);
    }

    public void setPlot(String plot) {
        labelText.setText(plot);
    }

    public void setImage(String imageFile) {
        try {
            String path = "/assets/images/" + imageFile;
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            imgView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setColorCircle(String color, Boolean visible) {
        if (color != null && visible) {
            colorCircle.setFill(Color.web(color));
            colorCircle.setVisible(true);
        } else {
            colorCircle.setVisible(false);
        }
    }

}
