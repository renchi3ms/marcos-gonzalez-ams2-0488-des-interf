package com.project;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class ControllerItemDetail {

    @FXML
    private VBox detailContainer;

    @FXML
    private ImageView imageGame;

    @FXML
    private Label labelDetailName;

    @FXML
    private Label labelDetailPlot;

    @FXML
    private Circle colorCircle;

    public void setName(String name) {
        labelDetailName.setText(name);
    }

    public void setPlot(String plot) {
        labelDetailPlot.setText(plot);
    }

    public void setImage(String imageFile) {
        try {
            String path = "/assets/images/" + imageFile;
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            imageGame.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setColorCircle(String color, Boolean visible) {
        
    }

    public VBox getDetailContainer() {
        return detailContainer;
    }
}
